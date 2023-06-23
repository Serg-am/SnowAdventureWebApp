package ru.snowadventure.SnowAdventureWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.dao.ResortDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Resort;
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

    public PostController(ResortDAO resortDAO, RegionDAO regionDAO, ResortService resortService) {
        this.resortDAO = resortDAO;
        this.regionDAO = regionDAO;
        this.resortService = resortService;
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String listResorts(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        Page<Resort> resortPage = resortService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("resortPage", resortPage);

        int totalPages = resortPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "post";
    }


    /*@GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("resorts", resortDAO.index());
        return "post";
    }*/


}
