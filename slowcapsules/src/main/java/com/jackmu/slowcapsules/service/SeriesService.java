package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//TODO: Use page instead of list

public interface SeriesService {
    Series saveSeries(Series series);

    void deleteSeries(Long id);

    Page<Series> fetchNewest(Pageable pageable);

    List<Series> fetchByWriter(String writer);

    List<Series> fetchByTag(String tag);

    List<Series> fetchByKeyword(String keyword, Boolean published);
    List<Series> fetchBySeriesId(Long id);
}
