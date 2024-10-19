package com.hmvss.api.web.controller.user;

import com.hmvss.api.dto.user.RoleFunctionDTO;
import com.hmvss.api.services.interfaces.IRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role-function")
public class RoleFunctionController {

    @Autowired
    private IRoleFunctionService roleFunctionService;

    @PostMapping("/assign")
    public ResponseEntity<RoleFunctionDTO> assignFunctionToRole(@RequestParam Long roleId, @RequestParam Long functionId) {
        RoleFunctionDTO roleFunctionDTO = roleFunctionService.assignFunctionToRole(roleId, functionId);
        return ResponseEntity.ok(roleFunctionDTO);
    }
}