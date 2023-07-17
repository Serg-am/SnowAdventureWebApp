package ru.snowadventure.SnowAdventureWebApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.snowadventure.SnowAdventureWebApp.models.Contact;


import java.util.List;

@Component
public class ContactDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> index() {
        return jdbcTemplate.query("SELECT * FROM contact", new BeanPropertyRowMapper<>(Contact.class));
    }

    public void save(Contact contact) {
        jdbcTemplate.update("INSERT INTO contact(contact_name, contact_email, contact_message) VALUES (?, ?, ?)",
                contact.getContactName(), contact.getContactEmail(), contact.getContactMessage());
    }
}
