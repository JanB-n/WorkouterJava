package project.workouter.model;

import java.util.List;

/**
 * DTO stworzone by wygodnie zwracać jednocześnie treningi i serie ćwiczeń im odpowiadające
 */
public class TrainingDTO {
    /**
     * Id treningu
     */
    private Long idTraining;
    /**
     * Data treningu
     */
    private String date;
    /**
     * Serie ćwiczeń wykonane na treningu
     */
    private List<TrainingSetDTO> data;

    // Getters and Setters
    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TrainingSetDTO> getData() {
        return data;
    }

    public void setData(List<TrainingSetDTO> data) {
        this.data = data;
    }
}