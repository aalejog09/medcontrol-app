package com.hmvss.api.web.controller.role;

import com.hmvss.api.dto.user.AddFunctionDTO;
import com.hmvss.api.dto.user.FunctionDTO;
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

import java.util.List;

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
    @SwaggerGenericResponses
    @PostMapping("/add-function")
    public ResponseEntity<RoleDTO> addToRole(@Valid @RequestBody AddFunctionDTO addFunctionDTO ) {
        RoleDTO roleDTO= roleService.assignFunctionToRole(addFunctionDTO);
        return ResponseEntity.ok(roleDTO);
    }

    @SwaggerGenericResponses
    @PostMapping("/remove-function")
    public ResponseEntity<RoleDTO> removeFromRol(@Valid @RequestBody AddFunctionDTO addFunctionDTO ) {
        RoleDTO roleDTO= roleService.removeFunctionFromRole(addFunctionDTO);
        return ResponseEntity.ok(roleDTO);
    }

    @GetMapping("/getByRole/{roleName}")
    public ResponseEntity<List<FunctionDTO>> getFunctionsByRolename(@PathVariable String roleName) {
        List<FunctionDTO> functions = roleService.getFunctionsByRolename(roleName);
        return ResponseEntity.ok(functions);
    }

}
