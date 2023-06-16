package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/series/new -Method POST -Body (@{"datetime"="2022-03-01T21:34:55";"numEntries"="4";"title"="ugh";"summary"="fuuuu";"tags"="fthis";"cadence"="7";"penName"="jack"}|ConvertTo-Json) -ContentType "application/json"
    @PostMapping("/new")
    public Series postSeries(@RequestBody Series series){
        return seriesService.saveSeries(series);
    }

    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/series/delete/52 -Method DELETE
    @DeleteMapping("/delete/{seriesId}")
    public void deleteSeries(@PathVariable Long seriesId){
        seriesService.deleteSeries(seriesId);
    }

    //validated
    @GetMapping("/getNewest")
    public List<Series> getNewestSeries(){
        return seriesService.fetchNewest();
    }

    //validated
    @GetMapping("/writer/{writer}")
    public List<Series> getSeriesByWriter(@PathVariable String writer){
        return seriesService.fetchByWriter(writer);
    }

    //validated
    @GetMapping("/tag/{tag}")
    public List<Series> getSeriesByTag(@PathVariable String tag){
        return seriesService.fetchByTag(tag);
    }

    //validated
    @GetMapping("/search/{keyword}")
    public List<Series> searchSeries(@PathVariable String keyword){
        return seriesService.fetchByKeyword(keyword);
    }

}
