package com.thesevensky.starter.properties.file;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 17:45
 * @Version 1.0
 */
public class FileProperties {
    private ImageFileProperties image = new ImageFileProperties();

    public ImageFileProperties getImage() {
        return image;
    }

    public void setImage(ImageFileProperties image) {
        this.image = image;
    }
}
