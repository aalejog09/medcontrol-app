package com.hmvss.api.persistence.repository.specialist;

import com.hmvss.api.persistence.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecialistRepository extends JpaRepository<Specialist, Long> {
}