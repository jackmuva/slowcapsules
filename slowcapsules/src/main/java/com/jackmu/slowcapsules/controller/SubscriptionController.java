package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Subscription;
import com.jackmu.slowcapsules.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    //This works!
    @GetMapping("/getAll")
    public List<Subscription> getSubscriptions(){
        return subscriptionService.fetchSubscriptions();
    }

    @PostMapping("/new")
    public Subscription putSubscription(@RequestBody Subscription subscription){
        return subscriptionService.saveSubscription(subscription);
    }

    @DeleteMapping("/cancelSubscription/{email}/{seriesId}")
    public void deleteSubscription(@PathVariable String email, @PathVariable Long seriesId){
        subscriptionService.deleteSubscription(email, seriesId);
    }

    //TODO: Trouble converting to DTO
    @GetMapping("/testEmails")
    public List<Subscription> getEmails(){
        subscriptionService.sendEmails();
        return subscriptionService.fetchSubscriptions();
    }

    //validated
    @PutMapping("/updateDate")
    public List<Subscription> updateDate(){
        subscriptionService.updateSendDate();
        return subscriptionService.fetchSubscriptions();
    }

    //validated
    @PutMapping("/increment")
    public List<Subscription> increment(){
        subscriptionService.incrementArticleNum();
        return subscriptionService.fetchSubscriptions();
    }
    //validated
    @DeleteMapping("/finished")
    public List<Subscription> deleteFinished(){
        subscriptionService.deleteFinishedSeries();
        return subscriptionService.fetchSubscriptions();
    }
}
