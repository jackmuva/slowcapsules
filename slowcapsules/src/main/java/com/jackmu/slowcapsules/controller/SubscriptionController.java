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

    //Validated
    @GetMapping("/getAll")
    public List<Subscription> getSubscriptions(){
        return subscriptionService.fetchSubscriptions();
    }

    //Validated
    @PostMapping(value = "/new")
    public Subscription putSubscription(@RequestBody Subscription subscription){
        return subscriptionService.saveSubscription(subscription);
    }

    //Validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/cancelSubscription/email_1/1 -Method DELETE
    @DeleteMapping("/cancelSubscription/{email}/{seriesId}")
    public void deleteSubscription(@PathVariable String email, @PathVariable Long seriesId){
        subscriptionService.deleteSubscription(email, seriesId);
    }

    //Validated
    @GetMapping("/testEmails")
    public List<Subscription> getEmails(){
        subscriptionService.sendEmails();
        return subscriptionService.fetchSubscriptions();
    }

    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/updateDate -Method PUT
    @PutMapping("/updateDate")
    public List<Subscription> updateDate(){
        subscriptionService.updateSendDate();
        return subscriptionService.fetchSubscriptions();
    }

    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/increment -Method PUT
    @PutMapping("/increment")
    public List<Subscription> increment(){
        subscriptionService.incrementArticleNum();
        return subscriptionService.fetchSubscriptions();
    }
    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/finished -Method DELETE
    @DeleteMapping("/finished")
    public List<Subscription> deleteFinished(){
        subscriptionService.deleteFinishedSeries();
        return subscriptionService.fetchSubscriptions();
    }
}
