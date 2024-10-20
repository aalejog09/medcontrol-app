package com.hmvss.api.services;


import com.hmvss.api.dto.personalDataInfo.LocationDTO;
import com.hmvss.api.persistence.mapper.LocationMapper;
import com.hmvss.api.persistence.model.City;
import com.hmvss.api.persistence.model.Location;
import com.hmvss.api.persistence.repository.location.ILocationRepository;
import com.hmvss.api.services.interfaces.ICityService;
import com.hmvss.api.services.interfaces.ILocationService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocationService implements ILocationService {

    @Autowired
    ILocationRepository locationRepository;

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    ICityService IcityService;

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
    }

    @Override
    @Transactional
    public Location registerLocation(LocationDTO locationDTO) {
        City city = IcityService.getCityById(locationDTO.getCity().getId());
        Location location = locationMapper.toLocation(locationDTO);
        location.setCity(city);
        return locationRepository.save(location);
    }

    public Location updateLocation(LocationDTO locationDTO){
        City city = IcityService.getCityById(locationDTO.getCity().getId());
        Location location=locationMapper.toLocation(locationDTO);
        return locationRepository.save(location);
    }


}
