package ru.snowadventure.SnowAdventureWebApp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @Column(name = "contact_name")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 100 символов")
    private String contactName;

    @Column(name = "contact_email")
    @NotEmpty(message = "Почта не должна быть пустой")
    @Size(min = 2, max = 50, message = "Почто должна быть от 2 до 100 символов")
    private String contactEmail;

    @Column(name = "contact_message")
    @NotEmpty(message = "Сообщение не должно быть пустым")
    @Size(min = 2, max = 1000, message = "Сообщение должно быть от 2 до 1000 символов")
    private String contactMessage;
}
