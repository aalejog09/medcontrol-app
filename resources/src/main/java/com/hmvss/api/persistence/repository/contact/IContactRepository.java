package com.hmvss.api.persistence.repository.contact;


import com.hmvss.api.persistence.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContactRepository extends JpaRepository<Contact,Long> {

    Optional<Contact> findByEmail(String email);


}
