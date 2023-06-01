package ru.snowadventure.SnowAdventureWebApp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Userwebapp")
public class UserWebApp {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Имеил не должен быть пустым")
    @Size(min = 5, max = 100, message = "Имеил должен быть от 5 до 100 символов")
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

}
