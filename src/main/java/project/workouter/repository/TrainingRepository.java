package project.workouter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.workouter.model.Exercise;
import project.workouter.model.Training;
import project.workouter.model.TrainingSet;
import project.workouter.model.User;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Optional<Training> findById(Long id);

    List<Training> findAllByExerciseId(Long exerciseId);

}