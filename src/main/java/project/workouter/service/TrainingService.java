package project.workouter.service;

import project.workouter.model.*;
import project.workouter.repository.ExerciseRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.workouter.repository.TrainingRepository;
import project.workouter.repository.TrainingSetRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serwis dla treningów
 */
@Service
public class TrainingService  {

    private final TrainingRepository trainingRepository;

    private final TrainingSetRepository trainingSetRepository;

    public TrainingService(TrainingRepository trainingRepository, TrainingSetRepository trainingSetRepository){
        this.trainingRepository = trainingRepository;
        this.trainingSetRepository = trainingSetRepository;
    }

    /**
     * Dodaje treening do bazy danych
     */
    @Transactional
    public Training addTraining(Training training) {
        Training savedTraining = trainingRepository.save(training);
        return savedTraining;
    }

    /**
     * Usuwa trening z bazy kaskadowo razem z seriami treningowymi
     */
    public void removeTraining(Long id){
        Training training = trainingRepository.findById(id).orElseThrow(() -> new RuntimeException("Training not found"));
        List<TrainingSet> trainingSets = trainingSetRepository.findAllByTrainingId(id);
        trainingSetRepository.deleteAll(trainingSets);
        trainingRepository.delete(training);
    }

    /**
     * Zamienia obiekt typu Trening na TreningDTO
     */
    public TrainingDTO convertToDTO(Training training, List<TrainingSet> trainingSets) {
        TrainingDTO dto = new TrainingDTO();
        dto.setIdTraining(training.getId());

        dto.setDate(training.getDate());
        List<TrainingSetDTO> trainingSetDTOs = trainingSets.stream()
                .map(this::convertTrainingSetToDTO)
                .collect(Collectors.toList());

        dto.setData(trainingSetDTOs);

        return dto;
    }
    /**
     * Zamienia obiekt typu TreningSET na TreningSetDTO
     */
    private TrainingSetDTO convertTrainingSetToDTO(TrainingSet trainingSet) {
        TrainingSetDTO dto = new TrainingSetDTO();
        dto.setReps(trainingSet.getReps());
        dto.setWeight(trainingSet.getWeight());
        return dto;
    }
}