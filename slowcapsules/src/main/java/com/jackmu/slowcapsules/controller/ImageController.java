package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Image;
import com.jackmu.slowcapsules.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageController {
    private ImageService imageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/save", consumes = { "multipart/form-data" })
    public void postImage(@ModelAttribute Image image){
        imageService.saveImage(image);
    }

    //TODO: scheduled service that deletes unused images

    @GetMapping("/get/{imageId}")
    public List<Image> getImageById(@PathVariable Long imageId){
        return imageService.fetchImageByImageId(imageId);
    }
}
