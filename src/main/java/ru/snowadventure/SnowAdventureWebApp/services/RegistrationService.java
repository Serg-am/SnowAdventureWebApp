package ru.snowadventure.SnowAdventureWebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;
import ru.snowadventure.SnowAdventureWebApp.repositories.UsersRepository;

@Service
public class RegistrationService {
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(UserWebApp userWebApp){
        userWebApp.setPassword(passwordEncoder.encode(userWebApp.getPassword()));
        userWebApp.setRole("ROLE_USER");
        userWebApp.setActive(false);
        usersRepository.save(userWebApp);
    }
}
