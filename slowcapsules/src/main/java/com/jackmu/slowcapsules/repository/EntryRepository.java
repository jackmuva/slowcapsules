package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
