package com.hmvss.api.services;

import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.persistence.mapper.PersonalDataMapper;
import com.hmvss.api.persistence.model.*;
import com.hmvss.api.persistence.repository.PersonalData.IPersonalDataPagSortRepository;
import com.hmvss.api.persistence.repository.PersonalData.IPersonalDataRepository;
import com.hmvss.api.persistence.repository.city.ICityRepository;
import com.hmvss.api.services.interfaces.IContactService;
import com.hmvss.api.services.interfaces.ILocationService;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import com.hmvss.api.util.Utility;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class PersonalDataService implements IPersonalDataService {

    @Autowired
    IPersonalDataRepository personalDataRepository;

    @Autowired
    IPersonalDataPagSortRepository personalDataPagSortRepository;

    @Autowired
    PersonalDataMapper personalDataMapper;

    @Autowired
    PaginationService paginationService;

    @Autowired
    IContactService contactService;

    @Autowired
    ILocationService locationService;


    @Override
    @Transactional
    public PersonalData register(PersonalDataDTO personalDataDTO) {

        Contact contactSaved =contactService.registerContact(personalDataDTO.getContact());
        Location locationSaved = locationService.registerLocation(personalDataDTO.getLocation());

        PersonalData newPersonalData = personalDataMapper.toPersonalData(personalDataDTO);
        newPersonalData.setRegistryDate(new Date());

        newPersonalData.setLocation(locationSaved);
        newPersonalData.setContact(contactSaved);
        PersonalData personalDataSaved;
        personalDataSaved = personalDataRepository.save(newPersonalData);
        if(personalDataSaved.getId() == null){
            throw new APIException(APIError.VALIDATION_ERROR);
        }
        return personalDataSaved;
    }

    @Override
    public PersonalDataDTO getPersonalDataByDniAndBornDate(String dni, LocalDate bornDate) {
        log.info("dni: {} bornDate:{}", dni, bornDate);
       // log.info("PesonalData: {}", personalDataRepository.findByIdentificationDocumentNumberAndBornDate(dni, bornDate));
        PersonalDataDTO personalDataDTO =  personalDataMapper.toPersonalDataDTO(getPersonalDataEntityByDniAndBornDate(dni, bornDate));
        if(personalDataDTO == null){
            log.error("Data Not Found");
            throw new APIException(APIError.NOT_FOUND);
        }
        return personalDataDTO;
    }

    @Override
    public PersonalData getPersonalDataEntityByDniAndBornDate(String dni, LocalDate bornDate) {
        log.info("dni: {} bornDate:{}", dni, bornDate);
        // log.info("PesonalData: {}", personalDataRepository.findByIdentificationDocumentNumberAndBornDate(dni, bornDate));
        return personalDataRepository.findByIdentificationDocumentNumberAndBornDate(dni, bornDate)
                .orElseThrow(()-> new APIException(APIError.NOT_FOUND));
    }

    @Override
    public PersonalDataDTO update(PersonalDataDTO personalDataDTO) {
        PersonalData personalData = personalDataRepository.findById(personalDataDTO.getId())
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
        // Actualiza los campos necesarios
        PersonalData personalDataUpdated = personalDataMapper.toPersonalData(personalDataDTO);
        //Location
        personalData.setLocation(locationService.updateLocation(personalDataDTO.getLocation()));
        //Contact
        personalData.setContact(contactService.updateContact(personalDataDTO.getContact()));

        personalDataUpdated = personalDataRepository.save(personalDataUpdated);

        return personalDataMapper.toPersonalDataDTO(personalDataUpdated);
    }

    public PersonalData mapToPersonalData(PersonalDataDTO personalDataDTO){
        return personalDataMapper.toPersonalData(personalDataDTO);
    }

    public PersonalDataDTO mapToPersonalDataDTO(PersonalData personalData){
        return personalDataMapper.toPersonalDataDTO(personalData);
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
