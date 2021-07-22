package com.example.baseapi.usuario;

import com.example.baseapi.usuarioRole.UsuarioRoleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioRestController {

    private final UsuarioService usuarioService;
    private final UsuarioRoleService usuarioRoleService;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioRoleService usuarioRoleService) {
        this.usuarioService = usuarioService;
        this.usuarioRoleService = usuarioRoleService;
    }

    @GetMapping("/")
    public List<UsuarioDTO> getAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioDTO getUser(@PathVariable int id){
        return usuarioService.findById(id);
    }

    @GetMapping("/pageable")
    public List<UsuarioDTO> getUser(@RequestParam int page,@RequestParam int size){
        return usuarioService.findAll(page,size);
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> newUser(@RequestBody UsuarioDTO newUser){
        return new ResponseEntity<>(usuarioService.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{idEstatus}")
    public ResponseEntity deleteUser(@PathVariable int id, @PathVariable int idEstatus){
        usuarioService.deleteById(id,idEstatus);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<UsuarioDTO> updateUser(@RequestBody UsuarioDTO updateUser, @PathVariable int id){
        return new ResponseEntity<>(usuarioService.update(updateUser,id), HttpStatus.OK);
    }
}
