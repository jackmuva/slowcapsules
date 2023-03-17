package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.EntryEmailDTO;
import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    void deleteBySubscriberEmailAndSeriesId(String email, Long seriesId);

    @Modifying
    @Query("UPDATE Subscription SET articleNum = articleNum + 1")
    void incrementArticleNum();

    @Modifying
    @Query(value = "DELETE FROM subscription USING series WHERE article_num >= series.num_entries", nativeQuery = true)
    void deleteFinishedSubscriptions();

    @Query(value = "SELECT entry.entry_text, subscription.subscriber_email, series.title FROM subscription " +
            "LEFT JOIN entry ON subscription.series_id = entry.series_id AND subscription.article_num = entry.order_num" +
            "LEFT JOIN series ON series.series_id = subscription.series_id", nativeQuery = true)
    List<EntryEmailDTO> findEmailsBySendDate(LocalDate date);

    @Modifying
    @Query(value = "UPDATE Subscription SET send_date = send_date + cadence FROM Series WHERE Subscription.series_id = Series.series_id", nativeQuery = true)
    void updateSendDate();
}
