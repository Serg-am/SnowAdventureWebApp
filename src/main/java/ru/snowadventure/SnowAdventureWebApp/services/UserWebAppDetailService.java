package ru.snowadventure.SnowAdventureWebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;
import ru.snowadventure.SnowAdventureWebApp.repositories.UsersRepository;
import ru.snowadventure.SnowAdventureWebApp.security.UserWebAppDetails;

import java.util.Optional;

@Service
public class UserWebAppDetailService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserWebAppDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserWebApp> userWebAppOptional = usersRepository.findByUsername(username);

        //TODO Переделать костыль
        if(userWebAppOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        return new UserWebAppDetails(userWebAppOptional.get());
    }
}
