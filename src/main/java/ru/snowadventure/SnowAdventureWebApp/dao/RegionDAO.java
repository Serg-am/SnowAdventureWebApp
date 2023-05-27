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
public class RegionDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Region> index() {
        return jdbcTemplate.query("SELECT * FROM region", new BeanPropertyRowMapper<>(Region.class));
    }

    public Region show(int id) {
        return jdbcTemplate.query("SELECT * FROM Region WHERE region_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Region.class))
                .stream().findAny().orElse(null);
    }

    public void save(Region region) {
        jdbcTemplate.update("INSERT INTO Region(region_name) VALUES(?)", region.getRegionName());
    }

    public void update(int id, Region updatedRegion) {
        jdbcTemplate.update("UPDATE Region SET region_name=? WHERE region_id=?", updatedRegion.getRegionName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Region WHERE region_id=?", id);
    }

    public Optional<Region> getRegionByFullName(String regionName) {
        return jdbcTemplate.query("SELECT * FROM Region WHERE region_name=?", new Object[]{regionName},
                new BeanPropertyRowMapper<>(Region.class)).stream().findAny();
    }

    public List<Resort> getResortsByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Resort WHERE region_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Resort.class));
    }
}