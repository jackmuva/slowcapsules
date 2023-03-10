package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByOrderByDatetimeAsc();
    List<Series> findByPenName(String penName);

    @Query(value = "SELECT * FROM Series s WHERE s.tags LIKE %:tag%", nativeQuery = true)
    List<Series> findByTag(@Param("tag") String tag);

    @Query(value = "SELECT * FROM Series s WHERE s.summary LIKE %:keyword%", nativeQuery = true)
    List<Series> findBySummary(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM Series s WHERE s.title LIKE %:keyword%", nativeQuery = true)
    List<Series> findByTitle(@Param("keyword") String keyword);
}
