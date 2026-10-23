package com.beraldo.diary.controller;

import com.beraldo.diary.entity.Snack;
import com.beraldo.diary.repository.SnackRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/snack")
@RequiredArgsConstructor
public class SnackController {

    private final SnackRepository repository;

    @GetMapping("/list")
    public List<Snack> listSnack(){
        return repository.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveSnack(@Valid @RequestBody Snack snack)
    {
        snack.setId(null);
        snack.setDtInsert(null);
        repository.save(snack);
        return "Snack registered";
    }

    @PutMapping("/edit/{id}")
    public Snack updateSnack(@PathVariable Long id, @Valid @RequestBody Snack newSnack)
    {
        Snack snack = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Snack not found: "+id));

        snack.setName(newSnack.getName());
        snack.setType(newSnack.getType());
        snack.setQuantity(newSnack.getQuantity());
        return repository.save(snack);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSnack(@PathVariable Long id)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Snack not found");
        }
        repository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
