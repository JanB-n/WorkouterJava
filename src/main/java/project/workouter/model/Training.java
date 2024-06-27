package project.workouter.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Reprezentuje trening w bazie danych
 */
@Entity(name = "training")
@Table(name = "Training", schema = "workouter")
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID treningu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID ćwiczenia reprezentowane przez model ćwiczenia
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exercise")
    private Exercise exercise;

    /**
     * Data treningu
     */
    private String date;

    public Training() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

}