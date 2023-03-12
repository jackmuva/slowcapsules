package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    void deleteBySubscriberEmailAndSeriesId(String email, Long seriesId);

    @Modifying
    @Query("UPDATE Subscription SET articleNum = articleNum + 1")
    void incrementArticleNum();

}
