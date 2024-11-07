package com.hmvss.api.web.controller.specialist;

import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.dto.specialist.SpecialistDTO;
import com.hmvss.api.persistence.model.Specialist;
import com.hmvss.api.services.interfaces.ISpecialistService;
import com.hmvss.api.util.swagger.documentation.SwaggerGenericResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/specialist")
public class SpecialistController {

    @Autowired
    ISpecialistService specialistService;

    @SwaggerGenericResponses
    @PostMapping("/register")
    public ResponseEntity<SpecialistDTO> registerSpecialist(@RequestBody @Validated SpecialistDTO specialistDTO) {
        SpecialistDTO savedSpecialist = specialistService.register(specialistDTO);
        return ResponseEntity.ok(savedSpecialist);
    }

    @SwaggerGenericResponses
    @PutMapping("/update")
    public ResponseEntity<SpecialistDTO> update(@RequestBody @Validated SpecialistDTO specialistDTO) {
        SpecialistDTO updatedSpecialist = specialistService.update(specialistDTO);
        return ResponseEntity.ok(updatedSpecialist);
    }

    @SwaggerGenericResponses
    @GetMapping("/getByMedicalCollegeCode/{medicalCollegeCode}")
    public ResponseEntity<SpecialistDTO> getByMedicalCollegeCode(@Valid @PathVariable String medicalCollegeCode) {
        SpecialistDTO updatedSpecialist = specialistService.getByMedicalCollegeCode(medicalCollegeCode);
        return ResponseEntity.ok(updatedSpecialist);
    }

    @SwaggerGenericResponses
    @GetMapping("/getByMppsCode/{mppsCode}")
    public ResponseEntity<SpecialistDTO> getByMppsCode(@Valid @PathVariable String mppsCode) {
        SpecialistDTO updatedSpecialist = specialistService.getByMppsCode(mppsCode);
        return ResponseEntity.ok(updatedSpecialist);
    }


}
