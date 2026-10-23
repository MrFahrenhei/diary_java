package com.beraldo.diary.controller;

import com.beraldo.diary.entity.Sleep;
import com.beraldo.diary.repository.SleepRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sleep")
@RequiredArgsConstructor
public class SleepController {

    private final SleepRepository repository;

    @GetMapping("/list")
    public List<Sleep> listSleep(){
        return repository.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveSleep(@Valid @RequestBody Sleep sleep)
    {
        sleep.setId(null);
        sleep.setDtInsert(null);
        repository.save(sleep);
        return "Sleep registered";
    }

    @PutMapping("/edit/{id}")
    public Sleep updateSleep(@PathVariable Long id, @Valid @RequestBody Sleep newSleep)
    {
        Sleep sleep = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Sleep not found "+id));

        sleep.setTimeSleep(newSleep.getTimeSleep());
        sleep.setSleepQuality(newSleep.getSleepQuality());

        return repository.save(sleep);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSleep(@PathVariable Long id)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sleep not found");
        }
        repository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

}
