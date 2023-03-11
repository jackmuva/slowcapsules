package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/entry")
public class EntryController {
    @Autowired
    private EntryService entryService;

    @PostMapping("/new")
    public Entry postEntry(@RequestBody Entry entry){
        return entryService.saveEntry(entry);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntry(@PathVariable Long id){
        entryService.deleteEntry(id);
    }

    @GetMapping("/getBySeries/{id}")
    public List<Entry> getBySeriesId(@PathVariable Long id){
        return entryService.fetchEntriesBySeriesId(id);
    }

}
