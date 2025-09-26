package com.weg.apisistemabiblioteca.dao;

import com.weg.apisistemabiblioteca.database.Conexao;
import com.weg.apisistemabiblioteca.model.Usuario;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO {

    public Usuario saveUser(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario(nome,email) VALUES(?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        }
        return usuario;
    }

    public List<Usuario> listUsers() throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,email
                FROM usuario
                """;
        List<Usuario> usuarios = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Usuario usuario = new Usuario(id,nome,email);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario searchUsersById(int id) throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,email
                FROM usuario
                WHERE id = ?
                """;

        int newUsers = 0;
        String nome = "";
        String email = "";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newUsers = rs.getInt("id");
                nome = rs.getString("nome");
                email = rs.getString("email");
            }
        }
        return new Usuario(newUsers,nome,email);
    }

    public void updateUsers(Usuario usuario) throws SQLException {
        String query = "UPDATE usuario SET nome = ?, email = ? WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void delectUsers(int id) throws SQLException {
        String query = "DELETE FROM usuario WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
