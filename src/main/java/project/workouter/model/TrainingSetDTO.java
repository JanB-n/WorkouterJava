package project.workouter.model;

/**
 * DTO stworzone żeby w prosty sposób reprezentować serię treningową
 */
public class TrainingSetDTO {
    private Double reps;
    private Double weight;

    // Getters and Setters
    public Double getReps() {
        return reps;
    }

    public void setReps(Double reps) {
        this.reps = reps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
