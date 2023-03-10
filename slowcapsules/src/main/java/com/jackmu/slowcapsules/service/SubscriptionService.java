package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription saveSubscription(Subscription subscription);
    void deleteSubscription(Long id);
    List<Subscription> fetchSubscriptions();
}
