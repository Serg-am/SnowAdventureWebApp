package ru.snowadventure.SnowAdventureWebApp.util;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;
import ru.snowadventure.SnowAdventureWebApp.services.UserWebAppDetailService;

@Component
public class UserWebAppValidator implements Validator {

    private final UserWebAppDetailService userWebAppDetailService;

    public UserWebAppValidator(UserWebAppDetailService userWebAppDetailService) {
        this.userWebAppDetailService = userWebAppDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserWebApp.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserWebApp userWebApp = (UserWebApp) target;
        //TODO Переделать костыль
        try{
            userWebAppDetailService.loadUserByUsername(userWebApp.getUsername());
        } catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("username", "", "Такое имя пользователя уже существует");
    }
}
