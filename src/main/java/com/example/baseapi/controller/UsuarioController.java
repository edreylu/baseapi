package com.example.baseapi.controller;

import com.example.baseapi.domain.usuario.UsuarioDTO;
import com.example.baseapi.domain.usuario.UsuarioService;
import com.example.baseapi.domain.usuarioRole.UsuarioRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    @ApiOperation("Get all users")
    @ApiResponse(code = 200, message = "ok")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<UsuarioDTO> getUser(@PathVariable int id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/pageable")
    @ApiOperation("Get a pageable list of users")
    public ResponseEntity<List<UsuarioDTO>> getUser(@RequestParam int page,@RequestParam int size){
        return ResponseEntity.ok(usuarioService.findAll(page,size));
    }

    @PostMapping("/")
    @ApiOperation("Save new user")
    public ResponseEntity<UsuarioDTO> newUser(@RequestBody UsuarioDTO newUser){
        return new ResponseEntity<>(usuarioService.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{idEstatus}")
    @ApiOperation("Delete a user by id")
    public ResponseEntity deleteUser(@PathVariable int id, @PathVariable int idEstatus){
        usuarioService.deleteById(id,idEstatus);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a user")
    ResponseEntity<UsuarioDTO> updateUser(@RequestBody UsuarioDTO updateUser, @PathVariable int id){
        return new ResponseEntity<>(usuarioService.update(updateUser,id), HttpStatus.OK);
    }
}
