package com.hmvss.api.persistence.repository.PersonalData;

import com.hmvss.api.persistence.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonalDataRepository extends JpaRepository<PersonalData,Long> {
}
