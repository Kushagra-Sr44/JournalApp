package com.webapplication.journalapp.service;
import com.webapplication.journalapp.entity.JournalEntry;
import com.webapplication.journalapp.entity.User;
import com.webapplication.journalapp.repository.JournalEntryRepository;
import com.webapplication.journalapp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryRepository userEntryRepository;
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public ResponseEntity<?> getAllJournalEntryOfUser(String userName){
        User user=userEntryRepository.findByUserName(userName);
        if(user!=null){
            return new ResponseEntity<>(user.getJournalList(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?>createJournalEntryOfUser(JournalEntry journalEntry,String userName){
        User user=userEntryRepository.findByUserName(userName);
        if(user!=null){
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepository.save(journalEntry);
            user.getJournalList().add(saved);
            userEntryRepository.save(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    public  void deleteJournalEntry(String userName,ObjectId id)
    {
       User user= userEntryRepository.findByUserName(userName);
       if(user!=null){
           user.getJournalList().removeIf(x-> x.getId().equals(id));
           userEntryRepository.save(user);
       }
       journalEntryRepository.deleteById(id);
    }

    public Optional<JournalEntry> getById(ObjectId id,String userName) {
        List<JournalEntry> list =userEntryRepository.findByUserName(userName).getJournalList();
        for(JournalEntry jr:list){
            if(jr.getId().equals(id)){
                return Optional.of(jr);
            }
        }
        return Optional.empty();
    }

    public ResponseEntity<?> setJournalEntryOfUser(String userName,ObjectId id,JournalEntry journalEntry){
        User user = userEntryRepository.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if (old == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (journalEntry == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (journalEntry.getTitle() != null) old.setTitle(journalEntry.getTitle());
        if (journalEntry.getContent() != null) old.setContent(journalEntry.getContent());
        old.setDate(LocalDateTime.now());
        journalEntryRepository.save(old);
        return new ResponseEntity<>(old,HttpStatus.OK);

    }

    public void deleteAll() {
        journalEntryRepository.deleteAll();
    }

    public ResponseEntity<JournalEntry> setJournal(ObjectId id, JournalEntry jr) {
        JournalEntry upjr = journalEntryRepository.findById(id).orElse(null);
        if (upjr == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (jr == null) return new ResponseEntity<>(HttpStatus.OK);
        else {
            if (jr.getContent() != null) upjr.setContent(jr.getContent());
            upjr.setTitle(jr.getTitle());
            upjr.setDate(LocalDateTime.now());
            journalEntryRepository.save(upjr);
            return new ResponseEntity<>(upjr, HttpStatus.OK);
        }
//   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
// controller --> service-->repository
