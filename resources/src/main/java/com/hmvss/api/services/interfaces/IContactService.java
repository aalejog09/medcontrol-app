package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.personalDataInfo.ContactDTO;
import com.hmvss.api.persistence.model.Contact;

public interface IContactService {


    Contact getContactDataByEmail(String email);

    Contact updateContact(ContactDTO contactDTO);

    Contact registerContact(ContactDTO contactDTO);
}
