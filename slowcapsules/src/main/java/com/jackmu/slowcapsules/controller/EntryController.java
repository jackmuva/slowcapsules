package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.jwt.JwtTokenProvider;
import com.jackmu.slowcapsules.model.Entry;
import com.jackmu.slowcapsules.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/entry")
public class EntryController {
    @Autowired
    private EntryService entryService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public ResponseEntity<String> postEntry(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Entry entry){
        if(entry.getEmail().equals(userDetails.getUsername())) {
            return new ResponseEntity<>(entryService.saveEntry(entry).toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Do not have permission to that id", HttpStatus.BAD_REQUEST);
    }

    //Validated
    // Invoke-WebRequest -Uri http://localhost:8090/api/entry/delete/1 -Method DELETE
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long id){
        if(entryService.fetchEntriesByEntryId(id).get(0).getEmail().equals(userDetails.getUsername())){
            entryService.deleteEntry(id);
            return new ResponseEntity<>("Series Deleted", HttpStatus.OK);
}
        return new ResponseEntity<>("Do not have permission to that entry", HttpStatus.BAD_REQUEST);
    }

    //Validated
    @GetMapping("/getBySeries/{id}")
    public List<Entry> getBySeriesId(@PathVariable Long id){
        return entryService.fetchEntriesBySeriesId(id);
    }

}
