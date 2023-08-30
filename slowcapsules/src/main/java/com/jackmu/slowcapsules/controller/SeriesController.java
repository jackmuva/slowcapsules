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

    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/series/new -Method POST -Body (@{"datetime"="2022-03-01T21:34:55";"numEntries"="4";"title"="ugh";"summary"="fuuuu";"tags"="fthis";"cadence"="7";"penName"="jack"}|ConvertTo-Json) -ContentType "application/json"
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public ResponseEntity<String> postSeries(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Series series){
        if(series.getEmail().equals(userDetails.getUsername())){
            return new ResponseEntity<>(seriesService.saveSeries(series).toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Do not have permission to that id", HttpStatus.BAD_REQUEST);
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
