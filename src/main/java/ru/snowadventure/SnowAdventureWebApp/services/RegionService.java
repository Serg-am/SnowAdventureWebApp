package ru.snowadventure.SnowAdventureWebApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.dao.ResortDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Region;
import ru.snowadventure.SnowAdventureWebApp.models.Resort;

import java.util.Collections;
import java.util.List;

@Service
public class RegionService {
    private final RegionDAO regionDAO;
    final private List<Region> regions;

    public RegionService(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
        this.regions = regionDAO.index();
    }

    public Page<Region> findPaginated(Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Region> list;

        if(regions.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, regions.size());
            list = regions.subList(startItem, toIndex);
        }

        Page<Region> regionPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), regions.size());

        return regionPage;
    }
}
