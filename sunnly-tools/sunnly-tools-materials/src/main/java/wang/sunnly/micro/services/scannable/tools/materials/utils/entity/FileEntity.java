package wang.sunnly.micro.services.scannable.tools.materials.utils.entity;

import lombok.Data;

/**
 * FileEntity
 *
 * @author Sunnly
 * @create 2019/7/21 0021 21:04
 */
@Data
public class FileEntity {

    private String id;
    private String name;
    private long size;
    private String ext;
    private String baseUri;
    private String path;

    public String getUrl() {

        return String.format("%s%s%s", this.baseUri, baseUri.endsWith("/") ? "" : "/", this.path);
    }

}
