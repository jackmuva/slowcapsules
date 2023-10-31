package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO: Use page instead of list

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByPublishedIsTrueOrderByDatetimeDesc();
    List<Series> findByPenNameIgnoreCaseAndPublished(String penName, Boolean publish);
    List<Series> findByPenNameIgnoreCase(String penName);
    List<Series> findAllByTagsIsContainingIgnoreCaseAndPublishedIsTrue(String tag);
    @Query(value = "SELECT * FROM Series WHERE (POSITION(LOWER(:penName) in LOWER(pen_name)) > 0 " +
            "OR POSITION(LOWER(:tag) in LOWER(tags)) > 0 OR POSITION(LOWER(:summary) in LOWER(summary)) > 0 " +
            "OR POSITION(LOWER(:title) in LOWER(title)) > 0) AND published = :published",
    nativeQuery = true)
    List<Series> findAllByKeyword(
            @Param("penName") String penName,
            @Param("tag") String tag,
            @Param("summary") String summary,
            @Param("title") String title,
            @Param("published") Boolean published
    );
    List<Series> findBySeriesId(Long id);
}
