package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.dao.ResortDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Region;
import ru.snowadventure.SnowAdventureWebApp.models.Resort;
import ru.snowadventure.SnowAdventureWebApp.services.RegionService;
import ru.snowadventure.SnowAdventureWebApp.services.ResortService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping
public class PostController {
    private final ResortDAO resortDAO;
    private final RegionDAO regionDAO;
    private final ResortService resortService;
    private final RegionService regionService;
    private int str = 0;

    public PostController(ResortDAO resortDAO, RegionDAO regionDAO, ResortService resortService, RegionService regionService) {
        this.resortDAO = resortDAO;
        this.regionDAO = regionDAO;
        this.resortService = resortService;
        this.regionService = regionService;
    }

    @GetMapping(value = "/post-region/{id}")
    public String listResorts(@PathVariable("id") int id, Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        str = id;

        Page<Resort> resortPage = resortService.findPaginated(PageRequest.of(currentPage - 1, pageSize), str);


        model.addAttribute("resortPage", resortPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("str", str);


        int totalPages = resortPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "post-resort";
    }

    @GetMapping(value = "/post-resort")
    public String listResorts(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);


        Page<Resort> resortPage = resortService.findPaginated(PageRequest.of(currentPage - 1, pageSize), str);


        model.addAttribute("resortPage", resortPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);


        int totalPages = resortPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "post-resort";
    }

    @GetMapping("/post-region")
    public String index(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);


        Page<Region> regionPage = regionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("regionPage", regionPage);
        model.addAttribute("currentPageRegion", currentPage);
        model.addAttribute("pageSize", pageSize);


        int totalPages = regionPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "post-region";
    }
}
