package com.weg.apisistemabiblioteca.controller;

import com.weg.apisistemabiblioteca.model.Usuario;
import com.weg.apisistemabiblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario createUsers(
            @RequestBody Usuario usuario) {
        Usuario newUsers = new Usuario();
        try {
            newUsers = service.createUsers(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUsers;
    }

    @GetMapping
    public List<Usuario> listUsers() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            usuarios = service.listUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario getUsersById(@PathVariable int id) {
        Usuario newUserById = new Usuario();

        try {
            newUserById = service.searchUsersByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUserById;
    }

    @PutMapping("/{id}")
    public Usuario updateUserById(@PathVariable int id, @PathVariable Usuario usuario) {
        Usuario newUpdateUser = new Usuario();

        try {
            newUpdateUser = service.updateUsers(id,usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUpdateUser;
    }

    @DeleteMapping("/{id}")
    public Usuario deleteUser(@PathVariable int id){
        Usuario deleteUser = new Usuario();

        try {
            deleteUser = service.deleteUsers(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return deleteUser;
    }
}
