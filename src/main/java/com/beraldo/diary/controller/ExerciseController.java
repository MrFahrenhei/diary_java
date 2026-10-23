package com.beraldo.diary.controller;

import com.beraldo.diary.entity.Exercise;
import com.beraldo.diary.repository.ExerciseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseRepository repository;

    @GetMapping("/list")
    public List<Exercise> listExercise(){
        return repository.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveExercise(@Valid @RequestBody Exercise exercise)
    {
        exercise.setId(null);
        exercise.setDtInsert(null);
        repository.save(exercise);
        return "Exercise registered";
    }

    @PutMapping("/edit/{id}")
    public Exercise updateExercise(@PathVariable Long id, @Valid @RequestBody Exercise newExercise)
    {
        Exercise exercise = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Exercise not found "+id));

        exercise.setName(newExercise.getName());
        exercise.setSerie(newExercise.getSerie());
        exercise.setRepetition(newExercise.getRepetition());
        exercise.setCarga(newExercise.getCarga());
        exercise.setDate(newExercise.getDate());
        return repository.save(exercise);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found");
        }
        repository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
