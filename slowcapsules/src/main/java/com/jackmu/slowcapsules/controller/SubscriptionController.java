package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.Subscription;
import com.jackmu.slowcapsules.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //TODO: Add endpoint to check if a user is already subscribed to a series

    //Validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/new -Method POST
    // -Body (@{"articleNum"="3";"sendDate"="2023-03-19";"seriesId"="4";"subscriberEmail"="email_4"}|ConvertTo-Json) -ContentType "application/json"
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

    // The following API endpoints will NOT be accessible to the front end
    //Validated
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/testEmails")
    public List<Subscription> getEmails(){
        subscriptionService.sendEmails();
        return subscriptionService.fetchSubscriptions();
    }

    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/updateDate -Method PUT
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateDate")
    public List<Subscription> updateDate(){
        subscriptionService.updateSendDate();
        return subscriptionService.fetchSubscriptions();
    }

    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/increment -Method PUT
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/increment")
    public List<Subscription> increment(){
        subscriptionService.incrementArticleNum();
        return subscriptionService.fetchSubscriptions();
    }
    //validated
    //Invoke-WebRequest -Uri http://localhost:8090/api/subscription/finished -Method DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/finished")
    public List<Subscription> deleteFinished(){
        subscriptionService.deleteFinishedSeries();
        return subscriptionService.fetchSubscriptions();
    }
}
