package wang.sunnly.micro.services.scannable.entity;

import lombok.Data;

/**
 * FastDFSFile
 *
 * @author Sunnly
 * @create 2019/6/28 0028 21:48
 */
@Data

public class FastDFSFile {
    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;

    public FastDFSFile(String fileName, byte[] file_buff, String ext){
        this.name = fileName;
        this.content = file_buff;
        this.ext = ext;
    }
    public FastDFSFile(){}
}
