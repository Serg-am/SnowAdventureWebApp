package ru.snowadventure.SnowAdventureWebApp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "region_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;

    @NotEmpty(message = "Region should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters")
    @Column(name = "region_name")
    private String regionName;
}
