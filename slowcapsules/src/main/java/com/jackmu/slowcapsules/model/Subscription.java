package com.jackmu.slowcapsules.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "subscriber_id")
    //may need a join annotation
    private Long subscriberId;
    @Column(name = "series_id")
    private Long seriesId;
    @Column(name = "article_num")
    private int articleNum;


}
