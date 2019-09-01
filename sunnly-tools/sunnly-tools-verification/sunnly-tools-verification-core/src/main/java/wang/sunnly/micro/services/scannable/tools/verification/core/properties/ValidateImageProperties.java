package wang.sunnly.micro.services.scannable.tools.verification.core.properties;

/**
 * @author Sunnly
 * @ClassName ValidateImageProperties
 * @Date 2019/6/10 0010 20:33
 **/
public class ValidateImageProperties extends ValidateProperties {

    private int width = 67;

    private int height = 33;

    private int length = 4;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
