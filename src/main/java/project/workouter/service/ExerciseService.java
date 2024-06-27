package project.workouter.service;

import project.workouter.model.Exercise;
import project.workouter.model.Training;
import project.workouter.model.TrainingSet;
import project.workouter.repository.ExerciseRepository;
import project.workouter.repository.TrainingRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Serwis dla ćwiczeń
 */
@Service
public class ExerciseService  {

    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;

    private final TrainingService trainingService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ExerciseService(ExerciseRepository exerciseRepository, TrainingRepository trainingRepository, TrainingService trainingService){
        this.exerciseRepository = exerciseRepository;
        this.trainingRepository = trainingRepository;
        this.trainingService = trainingService;
    }

    /**
     * Zapisuje ćwiczenie w bazie danych
     */
    @Transactional
    public void addExercise(Exercise exercise){
        exerciseRepository.save(exercise);
    }

    /**
     * Usuwa ćwiczenie z bazy danych oraz kaskadowo treningi i serie treningowe
     */
    public void removeExercise(Long id){
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        List<Training> trainings = trainingRepository.findAllByExerciseId(id);
        for(Training t : trainings)
        {

            trainingService.removeTraining(t.getId());
        }
        exerciseRepository.delete(exercise);
    }
//    public void removeTraining(Long id){
//        Training training = trainingRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
//        List<TrainingSet> trainingSets = trainingSetRepository.findAllByTrainingId(id);
//        trainingSetRepository.deleteAll(trainingSets);
//        trainingRepository.delete(training);
//    }
}