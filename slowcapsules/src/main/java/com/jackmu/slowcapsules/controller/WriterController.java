package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Writer;
import com.jackmu.slowcapsules.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: Add exception handling to all endpoints
@RestController
@RequestMapping("api/writer")
public class WriterController {
    @Autowired
    private WriterService writerService;

    //Validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/writer/new -Method POST -Body (@{"email"="email_7";"penName"="maria"}|ConvertTo-Json) -ContentType "application/json"
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public Writer postWriter(@RequestBody Writer writer){
        return writerService.saveWriter(writer);
    }

    //Validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/writer/delete/1 -Method DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWriter(@PathVariable Long id){
        writerService.deleteWriter(id);
        return new ResponseEntity<>("Writer deleted", HttpStatus.OK);
    }

    //Validated
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{penName}")
    public List<Writer> getWriter(@PathVariable String penName){
        return writerService.fetchWriterByPenName(penName);
    }
}
