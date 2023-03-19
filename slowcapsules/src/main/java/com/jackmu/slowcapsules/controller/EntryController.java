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

    //Validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/entry/new -Method POST -Body (@{"entryText"="abc";"orderNum"="1";"seriesId"="4"}|ConvertTo-Json) -ContentType "application/json"
    @PostMapping("/new")
    public Entry postEntry(@RequestBody Entry entry){
        return entryService.saveEntry(entry);
    }

    //Validated
    // Invoke-WebRequest -Uri http://localhost:8090/api/entry/delete/1 -Method DELETE
    @DeleteMapping("/delete/{id}")
    public void deleteEntry(@PathVariable Long id){
        entryService.deleteEntry(id);
    }

    //Validated
    @GetMapping("/getBySeries/{id}")
    public List<Entry> getBySeriesId(@PathVariable Long id){
        return entryService.fetchEntriesBySeriesId(id);
    }

}
