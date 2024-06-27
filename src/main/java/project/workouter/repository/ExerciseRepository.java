package project.workouter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.workouter.model.Exercise;
import project.workouter.model.User;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findById(Long id);

    List<Exercise> findAllByUserId(Long userId);
}