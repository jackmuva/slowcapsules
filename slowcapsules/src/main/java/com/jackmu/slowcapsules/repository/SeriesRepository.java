package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO: Use page instead of list

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByPublishedIsTrueOrderByDatetimeDesc();
    List<Series> findByPenNameIgnoreCaseAndPublished(String penName, Boolean publish);
    List<Series> findByPenNameIgnoreCase(String penName);
    List<Series> findAllByTagsIsContainingIgnoreCaseAndPublishedIsTrue(String tag);
    List<Series> findAllByPenNameIsContainingIgnoreCaseOrTagsIsContainingIgnoreCaseOrSummaryIsContainingIgnoreCaseOrTitleIsContainingIgnoreCaseAndPublished(
            String penName,
            String tag,
            String summary,
            String title,
            Boolean published);
    List<Series> findBySeriesId(Long id);
}
