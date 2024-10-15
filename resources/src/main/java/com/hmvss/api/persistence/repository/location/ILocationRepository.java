package com.hmvss.api.persistence.repository.location;

import com.hmvss.api.persistence.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRepository extends JpaRepository<Location,Long> {
}
