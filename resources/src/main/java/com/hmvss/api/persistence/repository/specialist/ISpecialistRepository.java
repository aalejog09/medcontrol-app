package com.hmvss.api.persistence.repository.specialist;

import com.hmvss.api.persistence.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISpecialistRepository extends JpaRepository<Specialist, Long> {

    Optional <Specialist> findByMedicalCollegeCode(String medicalCollegeCode);

    Optional <Specialist> findByMppsCode(String mppsCode);
}