package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.snowadventure.SnowAdventureWebApp.models.UserWebApp;
import ru.snowadventure.SnowAdventureWebApp.services.RegistrationService;
import ru.snowadventure.SnowAdventureWebApp.services.UserService;
import ru.snowadventure.SnowAdventureWebApp.util.UserWebAppValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    private final UserWebAppValidator userWebAppValidator;

    private final UserService userService;


    @Autowired
    public AuthController(RegistrationService registrationService, UserWebAppValidator userWebAppValidator, UserService userService) {
        this.registrationService = registrationService;
        this.userWebAppValidator = userWebAppValidator;
        this.userService = userService;
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
    public String performRegistration(@ModelAttribute("userwebapp") @Valid UserWebApp userWebApp, BindingResult bindingResult, Model model){

        userWebAppValidator.validate(userWebApp, bindingResult);

        if(bindingResult.hasErrors())
            return "auth/registration";

        if (!userService.addActivationCodeUser(userWebApp)) {
            model.addAttribute("usernameError", "User exists!");
            return "auth/registration";
        }
        registrationService.register(userWebApp);

        return "redirect:/auth/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
        }

        return "auth/activate";
    }

}
