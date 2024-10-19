package com.hmvss.api.services;


import com.hmvss.api.dto.personalDataInfo.ContactDTO;
import com.hmvss.api.dto.personalDataInfo.LocationDTO;
import com.hmvss.api.persistence.mapper.ContactMapper;
import com.hmvss.api.persistence.model.Contact;
import com.hmvss.api.persistence.model.Location;
import com.hmvss.api.persistence.repository.contact.IContactRepository;
import com.hmvss.api.services.interfaces.IContactService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService implements IContactService {

    @Autowired
    IContactRepository contactRepository;

    @Autowired
    ContactMapper contactMapper;

    @Override
    public Contact getContactDataByEmail(String email) {
        Contact contact = contactRepository.findByEmail(email).orElseThrow(() -> new  APIException(APIError.NOT_FOUND));
        log.info("Contact : {}",contact);
        return contact;
    }

    @Override
    @Transactional
    public Contact registerContact(ContactDTO contactDTO) {
       // Contact newContact = getContactDataByEmail(contactDTO.getEmail());
        Contact  newContact = contactMapper.toContact(contactDTO);
        newContact = contactRepository.save(newContact);

        return newContact;
    }

    @Override
    public Contact updateLocation(ContactDTO contactDTO){
        Contact contact=contactMapper.toContact(contactDTO);
        return contactRepository.save(contact);
    }
}
