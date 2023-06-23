package ru.snowadventure.SnowAdventureWebApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.snowadventure.SnowAdventureWebApp.dao.ResortDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Resort;

import java.util.Collections;
import java.util.List;

@Service
public class ResortService {
    private final ResortDAO resortDAO;
    final private List<Resort> resorts;

    public ResortService(ResortDAO resortDAO) {
        this.resortDAO = resortDAO;
        this.resorts = resortDAO.index();
    }

    public Page<Resort> findPaginated(Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Resort> list;

        if(resorts.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, resorts.size());
            list = resorts.subList(startItem, toIndex);
        }

        Page<Resort> resortPage = new PageImpl<Resort>(list, PageRequest.of(currentPage, pageSize), resorts.size());

        return resortPage;
    }
}
