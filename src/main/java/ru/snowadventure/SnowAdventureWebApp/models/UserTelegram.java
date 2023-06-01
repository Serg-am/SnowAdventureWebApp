package ru.snowadventure.SnowAdventureWebApp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_data_table")
public class UserTelegram {
    @Id
    @Column(name = "chat_id")
    private int resortId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;
}
