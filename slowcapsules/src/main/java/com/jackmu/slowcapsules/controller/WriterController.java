package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.jwt.JwtTokenProvider;
import com.jackmu.slowcapsules.model.Writer;
import com.jackmu.slowcapsules.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/writer")
public class WriterController {
    @Autowired
    private WriterService writerService;

    private static final Logger LOGGER = Logger.getLogger(WriterController.class.getName());

    @PostMapping("/new")
    public Writer postWriter(@RequestBody Writer writer){
        return writerService.saveWriter(writer);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWriter(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id){
        LOGGER.info("User username is: " + userDetails.getUsername());
        if(writerService.fetchWriterByWriterId(id).isEmpty()){
            LOGGER.info("No writer with that id");
            return new ResponseEntity<>("No writer with that id", HttpStatus.BAD_REQUEST);
        }
        else if(!userDetails.getUsername().equals(writerService.fetchWriterByWriterId(id).get(0).getEmail())){
            LOGGER.info("Do not have permission to that id");
            return new ResponseEntity<>("Do not have permission to that id", HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Writer deleted");
        writerService.deleteWriter(id);
        return new ResponseEntity<>("Writer deleted", HttpStatus.OK);
    }

    @GetMapping("/get/{penName}")
    public List<Writer> getWriter(@PathVariable String penName){
        return writerService.fetchWriterByPenName(penName);
    }
}
