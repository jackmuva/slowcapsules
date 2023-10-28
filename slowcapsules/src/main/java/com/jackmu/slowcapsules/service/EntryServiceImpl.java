package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EntryServiceImpl implements EntryService{
    @Autowired
    private EntryRepository entryRepository;

    public Entry saveEntry(Entry entry){
        return entryRepository.save(entry);
    }

    public Entry updateEntry(Entry entry){
        if(!Objects.equals(entry.getOrderNum(), fetchEntriesByEntryId(entry.getEntryId()).get(0).getOrderNum())){
            changeOtherEntryOrders(entry);
        }

        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id){
        entryRepository.deleteByEntryId(id);
    }

    public List<Entry> fetchEntriesBySeriesId(Long id){
        return entryRepository.findAllBySeriesId(id);
    }
    public List<Entry> fetchEntriesByEntryId(Long id){
        return entryRepository.findByEntryId(id);
    }

    protected void changeOtherEntryOrders(Entry entry){
        Integer oldOrderNum = fetchEntriesByEntryId(entry.getEntryId()).get(0).getOrderNum();
        Integer newOrderNum = entry.getOrderNum();

        if(oldOrderNum > newOrderNum){
            pushBackOtherEntryOrders(entry, newOrderNum, oldOrderNum);
        } else {
            moveUpOtherEntryOrders(entry, newOrderNum, oldOrderNum);
        }
    }

    protected void pushBackOtherEntryOrders(Entry entry, Integer newOrderNum, Integer oldOrderNum){
        List<Entry> allEntries = fetchEntriesBySeriesId(entry.getEntryId());

        for(Entry ent:allEntries){
            if(ent.getOrderNum() >= newOrderNum && ent.getOrderNum() <= oldOrderNum){
                ent.setOrderNum(ent.getOrderNum() + 1);
                entryRepository.save(ent);
            }
        }
    }

    protected void moveUpOtherEntryOrders(Entry entry, Integer newOrderNum, Integer oldOrderNum){
        List<Entry> allEntries = fetchEntriesBySeriesId(entry.getEntryId());

        for(Entry ent:allEntries){
            if(ent.getOrderNum() <= newOrderNum && ent.getOrderNum() >= oldOrderNum){
                ent.setOrderNum(ent.getOrderNum() - 1);
                entryRepository.save(ent);
            }
        }
    }
}
