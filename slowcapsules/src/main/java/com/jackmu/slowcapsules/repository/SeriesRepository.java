package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO: Use page instead of list

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByOrderByDatetimeDesc();
    List<Series> findByPenNameIgnoreCaseAndPublishedIsTrue(String penName);
    List<Series> findAllByTagsIsContainingIgnoreCaseAndPublishedIsTrue(String tag);
    List<Series> findAllByPenNameIsContainingIgnoreCaseOrTagsIsContainingIgnoreCaseOrSummaryIsContainingIgnoreCaseOrTitleIsContainingIgnoreCaseAndPublishedIsTrue(
            String penName,
            String tag,
            String summary,
            String title);
    List<Series> findBySeriesId(Long id);
}
