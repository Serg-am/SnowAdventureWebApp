package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.dao.ResortDAO;

@Controller
@RequestMapping
public class PostController {
    private final ResortDAO resortDAO;
    private final RegionDAO regionDAO;

    public PostController(ResortDAO resortDAO, RegionDAO regionDAO) {
        this.resortDAO = resortDAO;
        this.regionDAO = regionDAO;
    }

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("resorts", resortDAO.index());
        return "post";
    }
}
