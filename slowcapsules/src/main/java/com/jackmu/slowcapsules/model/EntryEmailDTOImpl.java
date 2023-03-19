package com.jackmu.slowcapsules.model;

public class EntryEmailDTOImpl implements EntryEmailDTO{
    private String entryText;
    private String subscriberEmail;
    private String title;

    @Override
    public String getEntryText() {
        return entryText;
    }
    @Override
    public String getSubscriberEmail() {
        return subscriberEmail;
    }
    @Override
    public String getTitle() {
        return title;
    }
}
