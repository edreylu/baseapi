package com.example.baseapi.controller;

import com.example.baseapi.domain.role.RoleDTO;
import com.example.baseapi.domain.role.RolesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    private final RolesService rolesService;

    public RoleController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/")
    @ApiOperation("Get all roles")
    @ApiResponse(code = 200, message = "ok")
    public ResponseEntity<List<RoleDTO>> getAll(){
        return ResponseEntity.ok(rolesService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a role by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<RoleDTO> getRole(@PathVariable int id){
        return ResponseEntity.ok(rolesService.findById(id));
    }

    @PostMapping("/")
    @ApiOperation("Save new role")
    public ResponseEntity<RoleDTO> newRole(@RequestBody RoleDTO newUser){
        return new ResponseEntity<>(rolesService.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a role by id")
    public ResponseEntity deleteRole(@PathVariable int id){
        rolesService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a role")
    ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO updateUser, @PathVariable int id){
        return new ResponseEntity<>(rolesService.update(updateUser,id), HttpStatus.OK);
    }
}
