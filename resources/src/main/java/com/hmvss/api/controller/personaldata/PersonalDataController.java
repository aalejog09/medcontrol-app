package com.hmvss.api.controller.personaldata;


import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import com.hmvss.api.util.swagger.documentation.SwaggerGenericResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<PersonalDataDTO> registerUser(@RequestBody PersonalDataDTO personalDataDTO) {
        PersonalDataDTO personalDataSaved = personalDataService.register(personalDataDTO);
        return ResponseEntity.ok(personalDataSaved);
    }

}
