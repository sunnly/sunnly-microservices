package wang.sunnly.micro.services.scannable.centre.materials.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wang.sunnly.micro.services.scannable.centre.materials.utils.FileSecurityUtil;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.tools.materials.properties.SunnlySettingProperties;
import wang.sunnly.micro.services.scannable.tools.materials.properties.SunnlyUploadProperties;
import wang.sunnly.micro.services.scannable.tools.fastdfs.service.StorageService;
import wang.sunnly.micro.services.scannable.tools.materials.utils.entity.FileEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * UploadController
 *
 * @author Sunnly
 * @create 2019/7/22 14:19
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UploadController {

    @Autowired
    SunnlyUploadProperties sunnlyUploadProperties;

    @Autowired
    StorageService storageService;

    /**
     * 上传文件
     */
    @PostMapping("upload")
    public ObjectRestResponse upload(@RequestParam("myfile") MultipartFile file) {

        SunnlySettingProperties fileSetting = sunnlyUploadProperties.getSettings().get("fileSetting");
        if (fileSetting != null) {
            InputStream inputStream = null;
            try {
                inputStream = fileSetting.isSecurity()
                        ? FileSecurityUtil.encryptFile(file, fileSetting.getSecretKey())
                        : file.getInputStream();
                try {
                    String ext = file.getOriginalFilename().substring(
                            file.getOriginalFilename().lastIndexOf(".") + 1);
                    String upload = storageService.upload(IOUtils.toByteArray(inputStream), ext);
                    FileEntity fileEntity = new FileEntity();
                    fileEntity.setExt(ext);
                    fileEntity.setName(file.getOriginalFilename());
                    fileEntity.setSize(file.getSize());
                    fileEntity.setBaseUri(fileSetting.getBaseUrl());
                    fileEntity.setPath(upload);
                    return new ObjectRestResponse().data(fileEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            //没配置，默认不加密，保存到本地

        }
        return new ObjectRestResponse().data("success");
    }

    @GetMapping("/delete")
    public ObjectRestResponse delete(@RequestParam("fileId") String fileId) {
        int delete = storageService.delete(fileId);
        return new ObjectRestResponse().data(delete);
    }

    @GetMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam("fileId") String fileId) {

        response.setContentType("application/force-download");// 设置强制下载不打开
        //下载后文件的名字如果不处理，会发生问题
        String fileNameEncode = null;
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            fileNameEncode = java.net.URLEncoder.encode(fileId.substring(fileId.lastIndexOf("/") + 1), "UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileNameEncode);// 设置下载的文件名
//            disposition-type是以什么方式下载，如attachment为以附件方式下载
//            disposition-parm为默认保存时的文件名
            //判断是否加密存储
            SunnlySettingProperties fileSetting = sunnlyUploadProperties.getSettings().get("fileSetting");
            byte[] download = storageService.download(fileId);

            if (fileSetting != null) {
                inputStream = fileSetting.isSecurity()
                        ? FileSecurityUtil.decryptFile(new ByteArrayInputStream(download), fileSetting.getSecretKey())
                        : new ByteArrayInputStream(download);
                os = response.getOutputStream();
                IOUtils.copy(inputStream, os);
                os.flush();
            } else {
                //没配置，默认不加密，保存到本地
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @PostMapping("/img")
    public ObjectRestResponse uploadImage(@RequestParam("file") MultipartFile file) {

        return new ObjectRestResponse().data(null);
    }
}
