package com.hmvss.api.web.controller.user;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.user.RegisterUserDTO;
import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.model.User;
import com.hmvss.api.services.interfaces.IUserService;
import com.hmvss.api.util.swagger.documentation.SwaggerGenericResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService userService;

    @SwaggerGenericResponses
    @GetMapping("/all/pageables")
    public ResponseEntity<PaginationDTO>  getAllPageables(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "4")  int elements) {
        PaginationDTO response = this.userService.getAllUserListPageables(page,elements);
        return ResponseEntity.ok(response);
    }

    @SwaggerGenericResponses
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Validated @RequestBody RegisterUserDTO registerUserDTO) {
        UserDTO savedUser = userService.registerUser(registerUserDTO.getPersonalDataDTO(), registerUserDTO.getRoleId());
        return ResponseEntity.ok(savedUser);
    }

    @SwaggerGenericResponses
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Validated @RequestBody UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        log.info("user in log:{}",jwt.getClaims());

        UserDTO savedUser = userService.updateUserPersonalData(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @SwaggerGenericResponses
    @GetMapping("/get/user-info/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@Valid @PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

}
