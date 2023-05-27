package ru.snowadventure.SnowAdventureWebApp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "resort")
public class Resort {
    @Id
    @Column(name = "resort_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resortId;

    @Column(name = "region_id")
    @NotNull(message = "Region ID should not be empty")
    private int regionId;

    @Column(name = "resort_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String resortName;

    @Column(name = "resort_telephone")
    @NotEmpty(message = "Telephone should not be empty")
    @Size(min = 6, max = 15, message = "Telephone should be between 6 and 15 characters")
    private String resortTelephone;

    @Column(name = "resort_web_site")
    @NotEmpty(message = "Web site should not be empty")
    @Size(min = 7, max = 100, message = "Web site be between 7 and 100 characters and http://....")
    private String resortWebSite;

    @Column(name = "weather_region")
    @NotEmpty(message = "Weather Region should not be empty")
    @Size(min = 2, max = 100, message = "Weather region be between 2 and 100 characters")
    private String weatherRegion;

    @Column(name = "resort_description")
    @NotEmpty(message = "Web site should not be empty")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 255 characters")
    private String resortDescription;
}
