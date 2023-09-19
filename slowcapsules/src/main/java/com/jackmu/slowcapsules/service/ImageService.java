package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Image;

import java.util.List;

public interface ImageService {
    Image saveImage(Image image);
    void deleteImage(Long imageId);
    List<Image> fetchImageByImageId(Long imageId);
}
