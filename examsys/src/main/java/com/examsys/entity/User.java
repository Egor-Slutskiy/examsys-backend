package com.examsys.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Data
public class User {

    private @Id@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String login;
    private String password;
    private String name;
    private String role;

    public User(Long id, String login, String password, String name, String role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User(String login, String password, String name, String role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

