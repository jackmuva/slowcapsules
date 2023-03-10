package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService{
    @Autowired
    private SeriesRepository seriesRepository;

    public Series saveSeries(Series series){
        return seriesRepository.save(series);
    }

    public void deleteSeries(Long id){
        seriesRepository.deleteById(id);
    }

    public List<Series> fetchNewest(){
        return seriesRepository.findByOrderByDatetimeAsc();
    }

    public List<Series> fetchByWriter(String penName){
        return seriesRepository.findByPenName(penName);
    }

    public List<Series> fetchByTag(String tag){
        return seriesRepository.findByTag(tag);
    }

    public List<Series> fetchBySummary(String keyword){
        return seriesRepository.findBySummary(keyword);
    }

    public List<Series> fetchByTitle(String keyword){
        return seriesRepository.findByTitle(keyword);
    }

}
