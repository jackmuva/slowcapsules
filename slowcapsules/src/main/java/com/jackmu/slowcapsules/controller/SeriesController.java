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

    @PostMapping("/new")
    public Series postSeries(@RequestBody Series series){
        return seriesService.saveSeries(series);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSeries(@PathVariable Long seriesId){
        seriesService.deleteSeries(seriesId);
    }

    @GetMapping("/getNewest")
    public List<Series> getNewestSeries(){
        return seriesService.fetchNewest();
    }

    @GetMapping("/writer/{writer}")
    public List<Series> getSeriesByWriter(@PathVariable String writer){
        return seriesService.fetchByWriter(writer);
    }

    @GetMapping("/tag/{tag}")
    public List<Series> getSeriesByTag(@PathVariable String tag){
        return seriesService.fetchByTag(tag);
    }

    @GetMapping("/search/{keyword}")
    public List<Series> searchSeries(@PathVariable String keyword){
        return seriesService.fetchByKeyword(keyword);
    }

}
