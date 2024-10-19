package com.hmvss.api.web.controller.role;

import com.hmvss.api.dto.user.RoleDTO;
import com.hmvss.api.dto.user.RoleFunctionDTO;
import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.services.interfaces.IRoleFunctionService;
import com.hmvss.api.services.interfaces.IRoleService;
import com.hmvss.api.util.swagger.documentation.SwaggerGenericResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @SwaggerGenericResponses
    @PostMapping("/register")
    public ResponseEntity<Role> registerRole(@Valid @RequestBody RoleDTO roleDTO) {
        Role role = roleService.registerRole(roleDTO);
        return ResponseEntity.ok(role);
    }
}
