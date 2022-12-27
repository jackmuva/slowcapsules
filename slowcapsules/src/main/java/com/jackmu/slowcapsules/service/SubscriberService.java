package com.jackmu.slowcapsules.service;
  
import com.jackmu.slowcapsules.model.Subscriber;
import java.util.List;
  
// Interface
public interface SubscriberService {
  
    // Save operation
    Subscriber saveSubscriber(Subscriber subscriber);
  
    // Read operation
    List<Subscriber> fetchSubscriberList();
  
    // Delete operation
    void deleteSubscriberById(Long id);
}