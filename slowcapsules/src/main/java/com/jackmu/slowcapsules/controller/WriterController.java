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

    @PostMapping("/new")
    public Writer postWriter(@RequestBody Writer writer){
        return writerService.saveWriter(writer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWriter(@PathVariable Long id){
        writerService.deleteWriter(id);
    }

    @GetMapping("/get/{penName}")
    public List<Writer> getWriter(@PathVariable String penName){
        return writerService.fetchWriterByPenName(penName);
    }
}
