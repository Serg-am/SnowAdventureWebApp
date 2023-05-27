package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.snowadventure.SnowAdventureWebApp.dao.ResortDAO;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Region;
import ru.snowadventure.SnowAdventureWebApp.models.Resort;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/resorts")
public class ResortsController {
    private final ResortDAO resortDAO;
    private final RegionDAO regionDAO;

    @Autowired
    public ResortsController(ResortDAO resortDAO, RegionDAO regionDAO) {
        this.resortDAO = resortDAO;
        this.regionDAO = regionDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("resorts", resortDAO.index());
        return "resorts/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("region") Region region) {
        model.addAttribute("resort", resortDAO.show(id));

        Optional<Region> resortOwner = resortDAO.getResortOwner(id);

        if (resortOwner.isPresent())
            model.addAttribute("owner", resortOwner.get());
        else
            model.addAttribute("regions", regionDAO.index());

        return "resorts/show";
    }

    @GetMapping("/new")
    public String newResort(@ModelAttribute("resort") Resort resort) {
        return "resorts/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("resort") @Valid Resort resort,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "resorts/new";

        resortDAO.save(resort);
        return "redirect:/resorts";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("resort", resortDAO.show(id));
        return "resorts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("resort") @Valid Resort resort, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "resorts/edit";

        resortDAO.update(id, resort);
        return "redirect:/resorts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        resortDAO.delete(id);
        return "redirect:/resorts";
    }
}