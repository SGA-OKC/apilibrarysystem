package com.weg.apisistemabiblioteca.service;

import com.weg.apisistemabiblioteca.dao.UsuarioDAO;
import com.weg.apisistemabiblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;

    public UsuarioService(UsuarioDAO repository) {
        this.repository = repository;
    }

    public Usuario createUsers(Usuario usuario) throws SQLException {
        return repository.saveUser(usuario);
    }

    public List<Usuario> listUsers() throws SQLException {
        return repository.listUsers();
    }

    public Usuario deleteUsers(int id) throws SQLException {
        List<Usuario> usuarios = repository.listUsers();

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                repository.delectUsers(id);
            }
        }
        throw new RuntimeException("ID do usuario não existe!!");
    }

    public Usuario updateUsers(int id, Usuario usuario) throws SQLException {
        List<Usuario> usuarios = repository.listUsers();
        usuario.setId(id);

        for (Usuario usu : usuarios) {
            if (usu.getId() == usuario.getId()) {
                repository.updateUsers(usuario);
            }
        }
        throw new RuntimeException("ID do usuario não existe!!");
    }

    // OBS !
    public Usuario searchUsersByID(int id) throws SQLException {
        List<Usuario> usuarios = repository.listUsers();

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return repository.searchUsersById(id);
            }
        }
        throw new RuntimeException("ID do usuario não existe!!");
    }
}
