package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Image;
import com.jackmu.slowcapsules.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);

    }

    @Override
    public List<Image> fetchImageByImageId(Long imageId) {
        return imageRepository.findAllByImageId(imageId);
    }
}
