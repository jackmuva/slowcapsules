package com.jackmu.slowcapsules.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
  
// Importing required classes


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Subscriber")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscriber_id")
    private Long subscriberId;
    @Column(name = "email")
    private String email;

    //may need a property like subscriptions with a one to many
    //List<Subscriptions> userSubscriptions
}