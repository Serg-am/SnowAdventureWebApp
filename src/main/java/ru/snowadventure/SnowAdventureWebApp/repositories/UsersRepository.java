package ru.snowadventure.SnowAdventureWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserWebApp, Integer> {
    UserWebApp findByUsername(String username);

    UserWebApp findByActivationCode(String code);

}
