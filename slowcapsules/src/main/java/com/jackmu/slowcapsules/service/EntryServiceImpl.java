package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService{
    @Autowired
    private EntryRepository entryRepository;

    public Entry saveEntry(Entry entry){
        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id){
        entryRepository.deleteByEntryId(id);
    }

    public List<Entry> fetchEntriesBySeriesId(Long id){
        return entryRepository.findAllBySeriesId(id);
    }
}
