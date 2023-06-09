package ru.snowadventure.SnowAdventureWebApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.dao.ResortDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Resort;

import java.util.Collections;
import java.util.List;

@Service
public class ResortService {
    private final RegionDAO regionDAO;
    private List<Resort> resorts;

    public ResortService(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    public Page<Resort> findPaginated(Pageable pageable, int id){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        this.resorts = regionDAO.getResortsByPersonId(id);
        List<Resort> list;

        if(resorts.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, resorts.size());
            list = resorts.subList(startItem, toIndex);
        }

        Page<Resort> resortPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), resorts.size());

        return resortPage;
    }
}
