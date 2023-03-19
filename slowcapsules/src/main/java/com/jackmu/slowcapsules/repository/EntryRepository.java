package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.model.Series;
import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO: Decide on whether/how to use join columns
@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Modifying
    @Transactional
    void deleteByEntryId(Long id);

//    @Query(value = "SELECT entries FROM Entry entries LEFT JOIN Series series ON series.series_id = entries.series_id WHERE entries.series_id = ?1",
//            nativeQuery = true)
    List<Entry> findAllBySeriesId(Long seriesId);

}
