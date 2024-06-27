package project.workouter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.workouter.model.Exercise;
import project.workouter.model.Training;
import project.workouter.model.TrainingSet;
import project.workouter.model.User;

@Repository
public interface TrainingSetRepository extends JpaRepository<TrainingSet, Integer> {
    Optional<TrainingSet> findById(Long id);

    @Query("SELECT ts FROM trainingset ts WHERE " +
            "(ts.user.id = :userId) AND " +
            "(ts.training.id = :trainingId)")
    List<TrainingSet> findAllByUserIdAndTrainingId(@Param("userId") Long userId, @Param("trainingId") Long trainingId);
    List<TrainingSet> findAllByTrainingId(Long id);
}