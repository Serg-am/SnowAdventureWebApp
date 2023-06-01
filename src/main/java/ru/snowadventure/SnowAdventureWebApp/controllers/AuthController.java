package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;
import ru.snowadventure.SnowAdventureWebApp.services.RegistrationService;
import ru.snowadventure.SnowAdventureWebApp.util.UserWebAppValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    private final UserWebAppValidator userWebAppValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserWebAppValidator userWebAppValidator) {
        this.registrationService = registrationService;
        this.userWebAppValidator = userWebAppValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userwebapp") UserWebApp userWebApp) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userwebapp") @Valid UserWebApp userWebApp, BindingResult bindingResult){

        userWebAppValidator.validate(userWebApp, bindingResult);

        if(bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(userWebApp);

        return "redirect:/auth/login";
    }
}
