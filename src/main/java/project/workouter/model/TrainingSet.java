package project.workouter.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

/**
 * Klasa reprezentuje serie treningowe w bazie danych
 */
@Entity (name="trainingset")
@Table (name="TrainingSet", schema="workouter")
public class TrainingSet implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID serii
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Id treningu
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_training")
    private Training training;
    /**
     * ID użytkownika
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user")
    private User user;
    /**
     * Ilość powtórzeń wykonana w serii
     */
    private Double reps;
    /**
     * ciężar z jakim powtórzenia zostały wykonane
     */
    private Double weight;

    public TrainingSet() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Training getTraining() {
        return this.training;
    }
    public void setTraining(Training training) {
        this.training = training;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getReps() {
        return this.reps;
    }
    public void setReps(Double reps) {
        this.reps = reps;
    }

    public Double getWeight() {
        return this.weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

}