package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Region;
import ru.snowadventure.SnowAdventureWebApp.util.RegionValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/regions")
public class RegionsController {
    private final RegionDAO regionDAO;
    private final RegionValidator regionValidator;

    @Autowired
    public RegionsController(RegionDAO regionDAO, RegionValidator regionValidator) {
        this.regionDAO = regionDAO;
        this.regionValidator = regionValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("regions", regionDAO.index());
        return "regions/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("region", regionDAO.show(id));
        model.addAttribute("resorts", regionDAO.getResortsByPersonId(id));

        return "regions/show";
    }

    @GetMapping("/new")
    public String newRegion(@ModelAttribute("region") Region region) {
        return "regions/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("region") @Valid Region region,
                         BindingResult bindingResult) {
        regionValidator.validate(region, bindingResult);

        if (bindingResult.hasErrors())
            return "regions/new";

        regionDAO.save(region);
        return "redirect:/regions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("region", regionDAO.show(id));
        return "regions/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("region") @Valid Region region, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "regions/edit";

        regionDAO.update(id, region);
        return "redirect:/regions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        regionDAO.delete(id);
        return "redirect:/regions";
    }
}