package com.dextra.gymapp.controller;

import com.dextra.gymapp.dto.ExerciseDTO;
import com.dextra.gymapp.model.Exercise;
import com.dextra.gymapp.model.User;
import com.dextra.gymapp.repository.UserRepository;
import com.dextra.gymapp.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ExerciseController {

    private ExerciseService exerciseService;

    private UserRepository userRepository;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, UserRepository userRepository) {
        this.exerciseService = exerciseService;
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}/exercises")
    public List<ExerciseDTO> getExercisesByUserIdAndDate(
            @PathVariable(value = "userId") final Long userId,
            @RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") String date) {
        Optional<User> user = this.userRepository.findById(userId);
        if(user.isPresent()) {
            List<ExerciseDTO> exercises = this.exerciseService.findByUserAndExerciseIdAndDate(user.get(), date);
            return exercises;
        }
        return Collections.emptyList();
    }

    @DeleteMapping("/users/{userId}/exercises/{exerciseId}")
    public void deleteExerciseByUserIdAndDate(
            @PathVariable(value = "userId") final Long userId,
            @PathVariable(value = "exerciseId") final Long exerciseId,
            @RequestParam(value="date") @DateTimeFormat(pattern="yyyy-MM-dd") String date) {
        this.exerciseService.deleteByUserIdAndExerciseIdAndDate(userId, exerciseId, date);
    }

    @PostMapping("/users/{userId}/exercises")
    public Exercise saveExerciseByUserIdAndDate(@RequestBody Exercise exercise) {
        return this.exerciseService.saveExercise(exercise);
    }
}
