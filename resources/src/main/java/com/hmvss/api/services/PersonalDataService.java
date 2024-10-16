package com.hmvss.api.services;

import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.personalDataInfo.ContactDTO;
import com.hmvss.api.dto.personalDataInfo.LocationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.persistence.mapper.ContactMapper;
import com.hmvss.api.persistence.mapper.LocationMapper;
import com.hmvss.api.persistence.mapper.PersonalDataMapper;
import com.hmvss.api.persistence.model.*;
import com.hmvss.api.persistence.repository.PersonalData.IPersonalDataPagSortRepository;
import com.hmvss.api.persistence.repository.PersonalData.IPersonalDataRepository;
import com.hmvss.api.persistence.repository.contact.IContactRepository;
import com.hmvss.api.persistence.repository.location.ILocationRepository;
import com.hmvss.api.persistence.repository.location.city.ICityRepository;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
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

        personalDataSaved = personalDataRepository.save(newPersonalData);
        if(personalDataSaved.getId() == null){
            throw new APIException(APIError.VALIDATION_ERROR);
        }

        return personalDataMapper.toPersonalDataDTO(personalDataSaved);
    }

    @Override
    public PersonalDataDTO getPersonalDataByDniAndBornDate(String dni, LocalDate bornDate) {
        log.info("dni: {} bornDate:{}", dni, bornDate);
       // log.info("PesonalData: {}", personalDataRepository.findByIdentificationDocumentNumberAndBornDate(dni, bornDate));
        PersonalDataDTO userFound =  personalDataMapper.toPersonalDataDTO(personalDataRepository.findByIdentificationDocumentNumberAndBornDate(dni, bornDate));
        if(userFound == null){
            log.error("Data Not Found");
            throw new APIException(APIError.NOT_FOUND);
        }
        return userFound;
    }

    @Override
    public PersonalDataDTO update(PersonalDataDTO personalDataDTO) {
        PersonalData personalData = personalDataRepository.findById(personalDataDTO.getId())
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
        // Actualiza los campos necesarios
        personalData.setNames(personalDataDTO.getNames());
        personalData.setLastnames(personalDataDTO.getLastnames());
        personalData.setSex(personalDataDTO.getSex());
        personalData.setBornDate(personalDataDTO.getBornDate());
        personalData.setCivilState(personalDataDTO.getCivilState());
        personalData.setIdentificationDocumentNumber(personalDataDTO.getIdentificationDocumentNumber());
        personalData.setProfession(personalDataDTO.getProfession());
        personalData.setEducationLevel(personalDataDTO.getEducationLevel());
        personalData.setOccupation(personalDataDTO.getOccupation());
        personalData.setNationality(personalDataDTO.getNationality());
        //Location
        personalData.setLocation(updateLocationFromPersonalDataDTO(personalDataDTO.getLocation()));
        //Contact
        personalData.setContact(updateContactFromPersonalDataDTO(personalDataDTO.getContact()));

        PersonalData personalDataUpdated = personalDataRepository.save(personalData);

        return personalDataMapper.toPersonalDataDTO(personalDataUpdated);
    }

    private Location updateLocationFromPersonalDataDTO(LocationDTO locationDTO){

        Location location = getLocationById(locationDTO.getId());
        location.setCity(getCityById(locationDTO.getCity().getId()));
        location.setHousing(locationDTO.getHousing());
        location.setAdditionalInfo(locationDTO.getAdditionalInfo());
        return locationRepository.save(location);
    }

    private Location getLocationById(Long locationId){
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
    }

    private City getCityById(Long cityId){
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
    }

    private Contact updateContactFromPersonalDataDTO(ContactDTO contactDTO){
        Contact contact = getContactById(contactDTO.getId());

        contact.setEmail(contactDTO.getEmail());
        contact.setAdditionalEmail(contactDTO.getAdditionalEmail());
        contact.setPrincipalPhone(contactDTO.getPrincipalPhone());
        contact.setAdditionalPhone(contactDTO.getAdditionalPhone());
        return contactRepository.save(contact);
    }

    private Contact getContactById(Long contactId){
        return  contactRepository.findById(contactId)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
    }


    @Override
    public PaginationDTO getAllPersonalDataListPageables(int page, int elements) {
        page--;
        if (elements <= 0 || page < 0) {
            throw new APIException(APIError.VALIDATION_ERROR);
        }
        Page<PersonalData> ListPageable = getAllPageable(page, elements);
        if(ListPageable.getContent().isEmpty()){
            throw new APIException(APIError.NOT_FOUND);
        }
        return getPersonalDataListPageable(ListPageable, personalDataMapper.toPersonalDataDTOList(ListPageable.getContent()));
    }

    private PaginationDTO getPersonalDataListPageable(Page<?> listPageable, List<?> personalDataDTOList) {
        return paginationService.getPageableData(listPageable, personalDataDTOList);
    }


    private Page<PersonalData> getAllPageable(int page, int elements) throws APIException {
        Pageable pageRequest = PageRequest.of(page, elements);
        Page<PersonalData> personalDataList = null;
        try {
            personalDataList = personalDataPagSortRepository.findAll(pageRequest);
        } catch (Exception e) {
            throw new APIException(APIError.VALIDATION_ERROR);
        }
        return personalDataList;
    }

}
