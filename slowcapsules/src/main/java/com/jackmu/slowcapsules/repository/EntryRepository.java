package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    void deleteByEntryId(Long id);

    List<Entry> findAllBySeriesId(Long id);

}
