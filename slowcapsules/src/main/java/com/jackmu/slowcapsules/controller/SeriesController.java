package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public ResponseEntity<String> postSeries(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Series series){
        if(series.getEmail().equals(userDetails.getUsername())){
            return new ResponseEntity<>(seriesService.saveSeries(series).toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Do not have permission to that id", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{seriesId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteSeries(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long seriesId){
        if(seriesService.fetchBySeriesId(seriesId).get(0).getEmail().equals(userDetails.getUsername())){
            seriesService.deleteSeries(seriesId);
            return new ResponseEntity<>("Series Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Do not have permission to that series", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getNewest")
    public List<Series> getNewestSeries(){
        return seriesService.fetchNewest();
    }

    @GetMapping("/writer/{writer}")
    public List<Series> getPublishedSeriesByWriter(@PathVariable String writer){
        return seriesService.fetchByWriter(writer);
    }

    @GetMapping("/tag/{tag}")
    public List<Series> getSeriesByTag(@PathVariable String tag){
        return seriesService.fetchByTag(tag);
    }

    @GetMapping("/search/{keyword}")
    public List<Series> searchPublishedSeries(@PathVariable String keyword){
        return seriesService.fetchByKeyword(keyword, Boolean.TRUE);
    }

    @GetMapping("/{id}")
    public List<Series> getSeriesById(@PathVariable Long id){
        return seriesService.fetchBySeriesId(id);
    }

}
