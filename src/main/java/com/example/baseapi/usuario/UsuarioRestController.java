package com.example.baseapi.usuario;

import com.example.baseapi.usuarioRole.UsuarioRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioRestController {

    private final UsuarioService usuarioService;
    private final UsuarioRoleService usuarioRoleService;
    private final UserDetailsServiceImpl userDetailsService;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioRoleService usuarioRoleService, UserDetailsServiceImpl userDetailsService) {
        this.usuarioService = usuarioService;
        this.usuarioRoleService = usuarioRoleService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public List<UsuarioDTO> getAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUser(@PathVariable int id){

        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/pageable")
    public ResponseEntity<List<UsuarioDTO>> getUser(@RequestParam int page,@RequestParam int size){
        return ResponseEntity.ok((usuarioService.findAll(page,size)));
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> newUser(@RequestBody UsuarioDTO newUser){
        return ResponseEntity.created(URI.create("/")).body(usuarioService.save(newUser));
    }

    @DeleteMapping("/{id}/{idEstatus}")
    public ResponseEntity deleteUser(@PathVariable int id, @PathVariable int idEstatus){
        usuarioService.deleteById(id,idEstatus);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(@RequestBody UsuarioDTO updateUser, @PathVariable int id){
        return ResponseEntity.ok((usuarioService.update(updateUser,id)));
    }


}
