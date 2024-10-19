package com.hmvss.api.web.controller.personaldata;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.persistence.model.PersonalData;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import com.hmvss.api.util.swagger.documentation.SwaggerGenericResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/personaldata")
public class PersonalDataController {
    @Autowired
    IPersonalDataService personalDataService;

/*    @SwaggerGenericResponses
    @GetMapping("/all/pageables")
    public ResponseEntity<PaginationDTO>  getAllPageables(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "4")  int elements,
                                                          @RequestHeader("Authorization") String authorizationHeader) {
        PaginationDTO response = this.personalDataService.getAllUserListPageables(page,elements);
        return ResponseEntity.ok(response);
    }*/

    @SwaggerGenericResponses
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody PersonalDataDTO personalDataDTO) {
        PersonalData personalDataSaved = personalDataService.register(personalDataDTO);
        return ResponseEntity.ok(personalDataSaved);
    }

    @SwaggerGenericResponses
    @GetMapping("/get/{dni}/{bornDate}")
    public ResponseEntity<PersonalDataDTO> findPersonalDataByDniAndBornDate(
            @PathVariable String dni,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate bornDate) {
        log.info("dni: {} bornDate:{}", dni, bornDate);
        PersonalDataDTO personalDataSaved = personalDataService.getPersonalDataByDniAndBornDate(dni, bornDate);
        return ResponseEntity.ok(personalDataSaved);
    }

    @SwaggerGenericResponses
    @PutMapping("/update")
    public ResponseEntity<PersonalDataDTO> updateUser(@RequestBody PersonalDataDTO personalDataDTO) {
            PersonalDataDTO personalDataUpdated = personalDataService.update(personalDataDTO);
            return ResponseEntity.ok(personalDataUpdated);
    }

    @SwaggerGenericResponses
    @GetMapping("/all/pageables")
    public ResponseEntity<PaginationDTO>  getAllPageables(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "4")  int elements,
                                                          @RequestHeader("Authorization") String authorizationHeader) {
        PaginationDTO response = this.personalDataService.getAllPersonalDataListPageables(page,elements);
        return ResponseEntity.ok(response);
    }

}
