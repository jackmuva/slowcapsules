package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Subscription;
import com.jackmu.slowcapsules.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription saveSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }
    @Override
    public void deleteSubscription(Long id){
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<Subscription> fetchSubscriptions(){
        return (List<Subscription>) subscriptionRepository.findAll();
    }
}
