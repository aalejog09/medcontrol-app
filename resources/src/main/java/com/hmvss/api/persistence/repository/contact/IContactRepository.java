package com.hmvss.api.persistence.repository.contact;

import com.hmvss.api.persistence.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends JpaRepository<Contact,Long> {
}
