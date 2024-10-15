package com.hmvss.api.persistence.repository.location.city;

import com.hmvss.api.persistence.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

}
