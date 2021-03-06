package wang.sunnly.micro.services.scannable.tools.fastdfs.service.impl;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.tools.fastdfs.service.StorageService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * FastDFSStorageService
 *
 * @author Sunnly
 * @since 2019/6/28 0028 22:39
 */
@Service
public class FastDFSStorageServiceImpl implements StorageService, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(FastDFSStorageServiceImpl.class);

    private TrackerClient trackerClient;

    @Value("${storage.fastdfs.tracker_server}")
    private String trackerServer;

    @Override
    public String upload(byte[] data, String extName) {
        return upload(data, extName, null);
    }

    @Override
    public String upload(byte[] data, String extName, NameValuePair[] metaList) {

        StorageServer storageServer = null;
        StorageClient1 storageClient1 = null;
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                logger.error("getConnection return null");
            }
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            String fileid = storageClient1.upload_file1(data, extName, metaList);
            logger.debug("uploaded file <{}>", fileid);
            return fileid;
        } catch (Exception ex) {
            logger.error("Upload fail", ex);
            return null;
        } finally {
            if (storageServer != null) {
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            storageClient1 = null;
        }
    }

    @Override
    public byte[] download(String fileId) {
        StorageServer storageServer = null;
        StorageClient1 storageClient = null;
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                logger.error("getConnection return null");
            }
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient1(trackerServer, storageServer);
            return storageClient.download_file1(fileId);
        } catch (Exception ex) {
            logger.error("Upload fail", ex);
            return null;
        } finally {
            if (storageServer != null) {
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            storageClient = null;
        }
    }

    @Override
    public int delete(String fileId) {
//        System.out.println("deleting ....");
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = null;
        int index = fileId.indexOf('/');
        String groupName = fileId.substring(0, index);
        try {
            trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                logger.error("getConnection return null");
            }
            storageServer = trackerClient.getStoreStorage(trackerServer, groupName);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            int result = storageClient1.delete_file1(fileId);
            return result;
        } catch (Exception ex) {
            logger.error("Delete fail", ex);
            return 1;
        } finally {
            if (storageServer != null) {
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            storageClient1 = null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        File confFile = File.createTempFile("fastdfs", ".conf");
        PrintWriter confWriter = new PrintWriter(new FileWriter(confFile));
        confWriter.println("tracker_server=" + trackerServer);
        confWriter.close();
        ClientGlobal.init(confFile.getAbsolutePath());
        confFile.delete();
        TrackerGroup trackerGroup = ClientGlobal.g_tracker_group;
        trackerClient = new TrackerClient(trackerGroup);

        logger.info("Init FastDFS with tracker_server : {}", trackerServer);
    }
}