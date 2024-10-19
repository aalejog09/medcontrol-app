package com.hmvss.api.services;

import com.hmvss.api.persistence.model.City;
import com.hmvss.api.persistence.repository.location.city.ICityRepository;
import com.hmvss.api.services.interfaces.ICityService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityService implements ICityService {

    @Autowired
    ICityRepository cityRepository;


    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id) .orElseThrow(() -> new APIException(APIError.CITY_NOT_FOUND));
    }
}
