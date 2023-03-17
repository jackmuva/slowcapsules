package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.EntryEmailDTO;
import com.jackmu.slowcapsules.model.Subscription;
import com.jackmu.slowcapsules.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


//TODO: Create CRON job to send emails and delete subscription (May need to implement an entry order to entry)
@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Subscription saveSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }
    @Override
    public void deleteSubscription(String email, Long seriesId) {
        subscriptionRepository.deleteBySubscriberEmailAndSeriesId(email, seriesId);
    }

    @Override
    public List<Subscription> fetchSubscriptions(){
        return subscriptionRepository.findAll();
    }

    @Override
    @Scheduled(cron = "0 6 * * *")
    public void sendEmails(){
        List<EntryEmailDTO> readyEmails = subscriptionRepository.findEmailsBySendDate(LocalDate.now());
        emailService.setLocalMode(true);
        emailService.sendEmails(readyEmails);
    }

    @Override
    @Scheduled(cron = "0 10 * * *")
    public void updateSendDate(){
        subscriptionRepository.updateSendDate();
    }

    @Override
    @Scheduled(cron = "0 11 * * *")
    public void deleteFinishedSeries(){
        subscriptionRepository.deleteFinishedSubscriptions();
    }

    @Override
    @Scheduled(cron = "0 13 * * *")
    public void incrementArticleNum(){
        subscriptionRepository.incrementArticleNum();
    }
}
