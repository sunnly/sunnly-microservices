package wang.sunnly.micro.services.scannable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wang.sunnly.micro.services.scannable.service.StorageService;
import wang.sunnly.micro.services.scannable.upload.security.utils.AESUtil;
import wang.sunnly.micro.services.scannable.upload.utils.entity.FileEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UploadController
 *
 * @author Sunnly
 * @create 2019/6/28 0028 22:05
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UploadController {
    @Value("${fastdfs.base.url}")
    private String FASTDFS_BASE_URL;

    @Autowired
    private StorageService storageService;

    /**
     * 文件上传
     *
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        // wangEditor 上传
        if (file != null) {
            List<String> fileNames = new ArrayList<>();
//            fileNames.add(writeFile(file));
            result.put("errno", 0);
            result.put("data", fileNames);
            result.put("baseUrl", FASTDFS_BASE_URL);
        }
        return result;
    }

    /**
     * 将文件写入指定目录
     *
     * @param multipartFile
     * @return 返回文件完整路径
     */
    private FileEntity writeFile(MultipartFile multipartFile) {
        // 获取文件后缀
        String oName = multipartFile.getOriginalFilename();
        long size = multipartFile.getSize();
        String extName = oName.substring(oName.lastIndexOf(".") + 1);
        try {
            //加密

            String uri = storageService.upload(multipartFile.getBytes(), extName);
            FileEntity fileEntity = new FileEntity();
            fileEntity.setExt(extName);
            fileEntity.setName(oName);
            fileEntity.setSize(size);
            fileEntity.setUri(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
