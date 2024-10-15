package com.hmvss.api.services;

import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.persistence.mapper.ContactMapper;
import com.hmvss.api.persistence.mapper.LocationMapper;
import com.hmvss.api.persistence.mapper.PersonalDataMapper;
import com.hmvss.api.persistence.model.City;
import com.hmvss.api.persistence.model.Contact;
import com.hmvss.api.persistence.model.Location;
import com.hmvss.api.persistence.model.PersonalData;
import com.hmvss.api.persistence.repository.PersonalData.IPersonalDataPagSortRepository;
import com.hmvss.api.persistence.repository.PersonalData.IPersonalDataRepository;
import com.hmvss.api.persistence.repository.contact.IContactRepository;
import com.hmvss.api.persistence.repository.location.ILocationRepository;
import com.hmvss.api.persistence.repository.location.city.ICityRepository;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class PersonalDataService implements IPersonalDataService {

    @Autowired
    IPersonalDataRepository personalDataRepository;

    @Autowired
    ILocationRepository locationRepository;

    @Autowired
    IContactRepository contactRepository;

    @Autowired
    ICityRepository cityRepository;

    @Autowired
    IPersonalDataPagSortRepository personalDataPagSortRepository;

    @Autowired
    PersonalDataMapper personalDataMapper;

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    ContactMapper contactMapper;

    @Autowired
    PaginationService paginationService;


    @Override
    public PersonalDataDTO register(PersonalDataDTO personalDataDTO) {

        City city = cityRepository.findById(personalDataDTO.getLocation().getCity().getId())
                .orElseThrow(() -> new RuntimeException("City not found"));
        Location newLocation = locationMapper.toLocation(personalDataDTO.getLocation());
        newLocation.setCity(city);
        Location locationSaved = locationRepository.save(newLocation);
        Contact newContact = contactMapper.toContact(personalDataDTO.getContact());
        Contact contactSaved = contactRepository.save(newContact);

        PersonalData newPersonalData = personalDataMapper.toPersonalData(personalDataDTO);
        newPersonalData.setRegistryDate(new Date());
        newPersonalData.setLocation(locationSaved);
        newPersonalData.setContact(contactSaved);
        PersonalData personalDataSaved = null;
        try {
            personalDataSaved = personalDataRepository.save(newPersonalData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return personalDataMapper.toPersonalDataDTO(personalDataSaved);
    }

}
