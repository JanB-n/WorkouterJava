package project.workouter.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import project.workouter.model.Exercise;
import project.workouter.model.Training;
import project.workouter.model.TrainingSet;
import project.workouter.model.User;
import project.workouter.repository.TrainingRepository;
import project.workouter.repository.TrainingSetRepository;

import java.util.List;
/**
 * Serwis dla serii ćwiczeń
 */
@Service
public class TrainingSetService  {

    private final TrainingSetRepository trainingSetRepository;

    public TrainingSetService(TrainingSetRepository trainingSetRepository){
        this.trainingSetRepository = trainingSetRepository;
    }


    @Transactional
    public void addTrainingSet(TrainingSet trainingSet){
        trainingSetRepository.save(trainingSet);
    }

    /**
     * dodaje serie po kolei do bazy danych
     */
    @Transactional
    public void addSets(List<TrainingSet> trainingSets, User user, Training training){
        for(TrainingSet ts : trainingSets) {
            ts.setUser(user);
            ts.setTraining(training);
            trainingSetRepository.save(ts);
        }
    }

    public void removeTrainingSet(TrainingSet trainingSet){
        trainingSetRepository.delete(trainingSet);
    }
}