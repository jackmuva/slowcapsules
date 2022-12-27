package com.jackmu.slowcapsules.controller;
  
import com.jackmu.slowcapsules.model.Subscriber;
import com.jackmu.slowcapsules.service.SubscriberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
  
// Annotation
@RestController
// Class
public class SubscriberController {
  
    @Autowired private SubscriberService subscriberService;
  
    // Save operation
    @PostMapping("/subscribers/save")
    public Subscriber savesubscriber(@RequestBody Subscriber subscriber)
    {
        return subscriberService.saveSubscriber(subscriber);
    }
  
    // Read operation
    @GetMapping("/subscribers/list")
    public List<Subscriber> fetchsubscriberList()
    {
        return subscriberService.fetchSubscriberList();
    }
  
    // Delete operation
    @DeleteMapping("/subscribers/delete/{id}")
    public String deletesubscriberById(@PathVariable("id") Long subscriberId)
    {
        subscriberService.deleteSubscriberById(subscriberId);
        return "Deleted Successfully";
    }
}