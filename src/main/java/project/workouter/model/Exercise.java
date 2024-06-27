package project.workouter.model;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Reprezentuje ćwiczenie w bazie danych
 */
@Entity (name="exercise")
@Table (name="Exercise", schema="workouter")
public class Exercise implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Id ćwiczenia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nazwa ćwiczenia
     */
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Exercise() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
