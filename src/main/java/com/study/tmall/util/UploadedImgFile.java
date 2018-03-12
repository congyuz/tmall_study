package com.study.tmall.util;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImgFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
