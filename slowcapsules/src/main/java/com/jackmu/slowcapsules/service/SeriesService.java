package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.domain.Page;

import java.util.List;

//TODO: Use page instead of list

public interface SeriesService {
    Series saveSeries(Series series);

    void deleteSeries(Long id);

    List<Series> fetchNewest();

    List<Series> fetchByWriter(String writer);

    List<Series> fetchByTag(String tag);

    List<Series> fetchByKeyword(String keyword);
}
