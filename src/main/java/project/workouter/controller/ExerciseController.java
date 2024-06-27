package project.workouter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import project.workouter.model.User;
import project.workouter.repository.ExerciseRepository;
import project.workouter.service.ExerciseService;
import project.workouter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.workouter.repository.UserRepository;
import project.workouter.model.Exercise;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Klasa odpowiedzialna za zarządzanie ćwiczeniami
 */

@RestController
@CrossOrigin
class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private UserRepository userRepository;
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    /**
     * Zwraca ćwiczenia stworzone przez użytkownika
     */
    @GetMapping("/exercise")
    List<Exercise> getExercises() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Long id = user.getId();
        return exerciseRepository.findAllByUserId(id);
    }

    /**
     * Zwraca ogólne ćwiczenia (stworzone przez administratora)
     */
    @GetMapping("/adminexercise")
    List<Exercise> getAdminExercises() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "admin";

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Long id = user.getId();
        return exerciseRepository.findAllByUserId(id);
    }

    /**
     * Zwraca pojedyncze ćwiczenie na podstawie jego ID
     */
    @GetMapping("/exercise/{id}")
    Optional<Exercise> getExercise(@PathVariable Integer id) {

        return exerciseRepository.findById(id);
    }

    /**
     * Usuwa ćwiczenie na podstawie jego ID
     */
    @DeleteMapping("/exercise/{id}")
    void deleteExercise(@PathVariable Long id) {
        exerciseService.removeExercise(id);
    }


    /**
     * Dodaje ćwiczenie do bazy, przesyłana jest jego nazwa
     */
    @PostMapping("/exercise")
    ResponseEntity addExercise(@RequestBody Exercise newExercise){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        newExercise.setUser(user);
        exerciseService.addExercise(newExercise);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

