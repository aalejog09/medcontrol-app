package com.hmvss.api.persistence.repository.contact;

import com.hmvss.api.persistence.model.Contact;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface IContactPagSortRepository extends ListPagingAndSortingRepository<Contact, Integer> {


}
