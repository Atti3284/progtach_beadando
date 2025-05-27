package org.mediaapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // <- Ez a lényeg!
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    public User(Long userId, String userName,
                String userPassword, String userEmail) {
        this.id = userId;
        this.username = userName;
        this.password = userPassword;
        this.email = userEmail;
    }
}
