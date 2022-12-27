package com.jackmu.slowcapsules.repository;
  
import com.jackmu.slowcapsules.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
  
// Annotation
@Repository
  
// Interface
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}