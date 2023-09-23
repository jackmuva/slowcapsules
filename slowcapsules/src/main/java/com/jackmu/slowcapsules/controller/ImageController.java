package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.DownloadedImage;
import com.jackmu.slowcapsules.model.Image;
import com.jackmu.slowcapsules.model.UploadedImage;
import com.jackmu.slowcapsules.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageController {
    private ImageService imageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);
    private static final String s3Url = "file:///C://Users/jackm/Documents/slowcapsules/S3/";

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/save", consumes = { "multipart/form-data" })
    public ResponseEntity<DownloadedImage> postImage(@ModelAttribute UploadedImage image) throws Exception{
        LOGGER.info(image.toString());
        LOGGER.info(image.getImage().toString());
        image.getImage().transferTo(new File("./"));

        Image rawImage = new Image();
//        rawImage.setImage(image.getImage().getBytes());
        Image savedImage = imageService.saveImage(rawImage);
        DownloadedImage downloadedImage = new DownloadedImage(1, Collections.singletonMap("url",
                        savedImage.getImageUrl()));
        return new ResponseEntity<>(downloadedImage, HttpStatus.OK);
    }

    //TODO: scheduled service that deletes unused images

    @GetMapping("/get/{imageId}")
    public Image getImageById(@PathVariable Long imageId){
        return null;
//        return imageService.fetchImageByImageId(imageId).get(0).getImage();
    }
}
