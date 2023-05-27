package ru.snowadventure.SnowAdventureWebApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.snowadventure.SnowAdventureWebApp.dao.RegionDAO;
import ru.snowadventure.SnowAdventureWebApp.models.Region;

@Component
public class RegionValidator  implements Validator{
    private final RegionDAO regionDAO;

    @Autowired
    public  RegionValidator(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Region.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Region region = (Region) o;

        if (regionDAO.getRegionByFullName(region.getRegionName()).isPresent())
            errors.rejectValue("fullName", "", "Такой регион уже существует");
    }
}
