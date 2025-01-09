package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){

     try {
         User user = userService.findByUserName(userName);
         journalEntry.setDate(LocalDateTime.now());
         JournalEntry saved = journalEntryRepository.save(journalEntry);
         user.getJournalEntries().add(saved);

         userService.saveUser(user);
     }catch (Exception e){
         log.error("error",e);
         throw new RuntimeException("an error occured while saving entry", e);
     }
    }

    public void saveEntry(JournalEntry journalEntry){

       journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        }catch (Exception e){
          log.error("error",e);
            throw new RuntimeException("an error occured while deleting the entry", e);
        }
        return removed;

    }

}





//controller---->service---->repository