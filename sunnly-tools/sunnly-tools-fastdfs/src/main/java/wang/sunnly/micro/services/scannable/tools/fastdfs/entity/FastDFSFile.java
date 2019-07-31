package wang.sunnly.micro.services.scannable.tools.fastdfs.entity;

import lombok.Data;

/**
 * FastDFSFile
 *
 * @author Sunnly
 * @since 2019/6/28 0028 21:48
 */
@Data

public class FastDFSFile {
    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;

    public FastDFSFile(String fileName, byte[] fileBuff, String ext) {
        this.name = fileName;
        this.content = fileBuff;
        this.ext = ext;
    }

    public FastDFSFile() {
    }
}
