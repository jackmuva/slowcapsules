package com.jackmu.slowcapsules.repository;

import com.jackmu.slowcapsules.model.EntryEmailDTO;
import com.jackmu.slowcapsules.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Modifying
    @Transactional
    void deleteBySubscriberEmailAndSeriesId(String email, Long seriesId);

    @Modifying
    @Transactional
    @Query("UPDATE Subscription SET articleNum = articleNum + 1")
    void incrementArticleNum();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM subscription USING series " +
            "WHERE CAST(subscription.series_id AS BIGINT) = CAST(series.series_id AS BIGINT) " +
            "AND article_num >= series.num_entries", nativeQuery = true)
    void deleteFinishedSubscriptions();

    @Query(value = "SELECT entry.entry_text, subscription.subscriber_email, series.title FROM subscription " +
            "LEFT JOIN entry ON CAST(subscription.series_id AS BIGINT) = CAST(entry.series_id AS BIGINT) " +
            "AND subscription.article_num = entry.order_num " +
            "LEFT JOIN series ON CAST(series.series_id AS BIGINT) = CAST(subscription.series_id AS BIGINT) " +
            "WHERE subscription.send_date = CURRENT_DATE", nativeQuery = true)
    List<EntryEmailDTO> findEmailsBySendDate(LocalDate date);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Subscription SET send_date = (send_date + (Series.cadence * INTERVAL '1 day')) FROM Series " +
            "WHERE CAST(Subscription.series_id AS BIGINT) = CAST(Series.series_id AS BIGINT) AND Subscription.send_date = CURRENT_DATE", nativeQuery = true)
    void updateSendDate();
}
