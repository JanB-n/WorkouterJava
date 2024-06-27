package project.workouter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.workouter.model.*;
import project.workouter.repository.ExerciseRepository;
import project.workouter.repository.TrainingRepository;
import project.workouter.repository.TrainingSetRepository;
import project.workouter.repository.UserRepository;
import project.workouter.service.ExerciseService;
import project.workouter.service.TrainingService;
import project.workouter.service.TrainingSetService;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Klasa odpowiedzialna za zarządzanie treningami
 */

@RestController
@CrossOrigin
class TrainingController {

    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingSetRepository trainingSetRepository;
    @Autowired
    private UserRepository userRepository;
    private final TrainingService trainingService;
    private final TrainingSetService trainingSetService;

    public TrainingController(TrainingService trainingService, TrainingSetService trainingSetService){
        this.trainingService = trainingService;
        this.trainingSetService = trainingSetService;
    }

    /**
     * Dodaje trening do ćwiczenia na podstawie ID ćwiczenia
     * Trening posiada aktualną datę sczytywaną po stronie serwera
     */
    @PostMapping("/{id_exercise}/training")
    ResponseEntity addTraining(@PathVariable Long id_exercise, @RequestBody List<TrainingSet> newTrainingSets){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Exercise exercise = exerciseRepository.findById(id_exercise).orElseThrow(() -> new RuntimeException("Exercise not found"));
        Training newTraining = new Training();
        newTraining.setExercise(exercise);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        newTraining.setDate(formattedDate);
        Training training = trainingService.addTraining(newTraining);

        trainingSetService.addSets(newTrainingSets, user, training);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Zwraca wszystkie treningi dla ćwiczenia, ale tylko te które wykonał użytkownik
     */
    @GetMapping("/{id_exercise}/trainings")
    List<TrainingDTO> getTrainingSets(@PathVariable Long id_exercise) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Training> trainings = trainingRepository.findAllByExerciseId(id_exercise);
        Long id = user.getId();
        List<TrainingDTO> trainingDTOs = new ArrayList<>();
        boolean anyTrainings = false;
        for(Training training : trainings){
            List<TrainingSet> l = trainingSetRepository.findAllByUserIdAndTrainingId(id, training.getId());
            if(!l.isEmpty())
            {
                anyTrainings = true;
            }
            trainingDTOs.add(trainingService.convertToDTO(training, trainingSetRepository.findAllByUserIdAndTrainingId(id, training.getId())));

        }
        if(!anyTrainings)
        {
            List<TrainingDTO> trainingDTOs2 = new ArrayList<>();
            return trainingDTOs2;
        }
        return trainingDTOs;
    }

    /**
     * Usuwa trening na podstawie jego ID
     */
    @DeleteMapping("/training/{id}")
    void deleteTraining(@PathVariable Long id) {
        trainingService.removeTraining(id);
    }
}