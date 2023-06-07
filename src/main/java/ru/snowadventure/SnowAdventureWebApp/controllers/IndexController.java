package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String indexI() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @GetMapping("/admin-panel")
    public String adminPanel(){
        return "admin-panel";
    }
}
