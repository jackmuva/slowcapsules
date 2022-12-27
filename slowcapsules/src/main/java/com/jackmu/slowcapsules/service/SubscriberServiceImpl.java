package com.jackmu.slowcapsules.service;
  
import com.jackmu.slowcapsules.model.Subscriber;
import com.jackmu.slowcapsules.repository.SubscriberRepository;
// Importing required classes
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
  
// Annotation
@Service
  
// Class
public class SubscriberServiceImpl implements SubscriberService {
  
    @Autowired
    private SubscriberRepository subscriberRepository;
  
    // Save operation
    @Override
    public Subscriber saveSubscriber(Subscriber subscriber)
    {
        return subscriberRepository.save(subscriber);
    }
  
    // Read operation
    @Override public List<Subscriber> fetchSubscriberList()
    {
        return (List<Subscriber>) subscriberRepository.findAll();
    }
  
    // Delete operation
    @Override
    public void deleteSubscriberById(Long subscriberId)
    {
        subscriberRepository.deleteById(subscriberId);
    }
}
