package ru.snowadventure.SnowAdventureWebApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.snowadventure.SnowAdventureWebApp.models.Region;
import ru.snowadventure.SnowAdventureWebApp.models.Resort;

import java.util.List;
import java.util.Optional;

@Component
public class ResortDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ResortDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Resort> index() {
        return jdbcTemplate.query("SELECT * FROM Resort", new BeanPropertyRowMapper<>(Resort.class));
    }

    public Resort show(int id) {
        return jdbcTemplate.query("SELECT * FROM Resort WHERE resort_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Resort.class))
                .stream().findAny().orElse(null);
    }

    public void save(Resort resort) {
        jdbcTemplate.update("INSERT INTO Resort(region_id, resort_name, resort_telephone, resort_web_site, weather_region, resort_description) VALUES(?, ?, ?, ?, ?, ?)",
                resort.getRegionId(), resort.getResortName(), resort.getResortTelephone(), resort.getResortWebSite(), resort.getWeatherRegion(), resort.getResortDescription());
    }

    public void update(int id, Resort updatedResort) {
        jdbcTemplate.update("UPDATE Resort SET region_id=?, resort_name=?, resort_telephone=?, resort_web_site=?, weather_region=?, resort_description=? WHERE resort_id=?",
                updatedResort.getRegionId(), updatedResort.getResortName(), updatedResort.getResortTelephone(), updatedResort.getResortWebSite(), updatedResort.getWeatherRegion(), updatedResort.getResortDescription(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Resort WHERE resort_id=?", id);
    }

    public Optional<Region> getResortOwner(int id) {
        return jdbcTemplate.query("SELECT Region.* FROM Resort JOIN Region ON Resort.region_id = Region.region_id " +
                        "WHERE Resort.resort_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Region.class))
                .stream().findAny();
    }
}