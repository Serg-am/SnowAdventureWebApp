package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.snowadventure.SnowAdventureWebApp.dao.ContactDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Contact;


import javax.validation.Valid;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final ContactDAO contactDAO;

    @Autowired
    public ContactController(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @GetMapping()
    public String index(@ModelAttribute("contact") Contact contact) {
        return "contact";
    }

    @PostMapping()
    public String create(@ModelAttribute("contact") @Valid Contact contact,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("Message", "Message error!");
            return "contact";
        }
        contactDAO.save(contact);
        return "redirect:/index";
    }
}
