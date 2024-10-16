package com.hmvss.api.web.controller.user;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.services.interfaces.IUserService;
import com.hmvss.api.util.swagger.documentation.SwaggerGenericResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService userService;

    @SwaggerGenericResponses
    @GetMapping("/all/pageables")
    public ResponseEntity<PaginationDTO>  getAllPageables(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "4")  int elements,
                                                          @RequestHeader("Authorization") String authorizationHeader) {
        PaginationDTO response = this.userService.getAllUserListPageables(page,elements);
        return ResponseEntity.ok(response);
    }

    @SwaggerGenericResponses
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

}
