package com.hmvss.api.web.controller.function;

import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.persistence.model.Function;
import com.hmvss.api.services.interfaces.IFunctionService;
import com.hmvss.api.util.swagger.documentation.SwaggerGenericResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/function")
public class FunctionController {

    @Autowired
    private IFunctionService functionService;

    @SwaggerGenericResponses
    @PostMapping("/register")
    public ResponseEntity<Function> register(@Valid @RequestBody FunctionDTO functionDTO) {
        Function function = functionService.registerFunction(functionDTO);
        return ResponseEntity.ok(function);
    }

    @SwaggerGenericResponses
    @GetMapping("/get/all")
    public ResponseEntity<List<Function>> getAll() {
        List<Function> function = functionService.getAllFuntcionList();
        return ResponseEntity.ok(function);
    }
}
