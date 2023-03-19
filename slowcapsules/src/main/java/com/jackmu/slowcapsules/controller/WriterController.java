package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Writer;
import com.jackmu.slowcapsules.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/writer")
public class WriterController {
    @Autowired
    private WriterService writerService;

    //Validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/writer/new -Method POST -Body (@{"email"="email_7";"penName"="maria"}|ConvertTo-Json) -ContentType "application/json"
    @PostMapping("/new")
    public Writer postWriter(@RequestBody Writer writer){
        return writerService.saveWriter(writer);
    }

    //Validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/writer/delete/1 -Method DELETE
    @DeleteMapping("/delete/{id}")
    public void deleteWriter(@PathVariable Long id){
        writerService.deleteWriter(id);
    }

    //Validated
    @GetMapping("/get/{penName}")
    public List<Writer> getWriter(@PathVariable String penName){
        return writerService.fetchWriterByPenName(penName);
    }
}
