package com.webapplication.journalapp.controller;

import com.webapplication.journalapp.entity.JournalEntry;
import com.webapplication.journalapp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllJournalEntryOfUser() {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        return journalEntryService.getAllJournalEntryOfUser(username);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String id) {
      Authentication auth= SecurityContextHolder.getContext().getAuthentication();
      String username=auth.getName();
      Optional<JournalEntry> journalEntry = journalEntryService.getById(new ObjectId(id),username);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createJournalEntryOfUser(@RequestBody JournalEntry journalEntry) {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        return journalEntryService.createJournalEntryOfUser(journalEntry,username);

    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> setJournal(@PathVariable String id, @RequestBody JournalEntry jr) {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        if (!ObjectId.isValid(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return journalEntryService.setJournal(new ObjectId(id), jr);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable String id ) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        if (!ObjectId.isValid(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        journalEntryService.deleteJournalEntry(username, new ObjectId(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}



