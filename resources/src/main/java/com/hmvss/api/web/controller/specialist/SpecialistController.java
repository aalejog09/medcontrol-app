package com.hmvss.api.web.controller.specialist;

import com.hmvss.api.dto.specialist.SpecialistDTO;
import com.hmvss.api.persistence.model.Specialist;
import com.hmvss.api.services.interfaces.ISpecialistService;
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

    @PostMapping("/register")
    public ResponseEntity<SpecialistDTO> registerSpecialist(@RequestBody @Validated SpecialistDTO specialistDTO) {
        SpecialistDTO savedSpecialist = specialistService.register(specialistDTO);
        return ResponseEntity.ok(savedSpecialist);
    }


}
