package project.workouter.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Reprezentuje użytkownika w bazie danych
 */
@Entity (name="user")
@Table (name="User", schema="workouter")

//public class User implements Serializable {
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;
    /**
     * ID użytkownika
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nazwa użytkownika
     */
    private String username;
    /**
     * Hasło użytkownika
     */
    private String password;

    public User() {
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //public String getUsername() {
    //    return this.username;
    //}


    public void setUsername(String username) {
        this.username = username;
    }

   //public String getPassword() {
    //    return this.password;
    //}

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }


}