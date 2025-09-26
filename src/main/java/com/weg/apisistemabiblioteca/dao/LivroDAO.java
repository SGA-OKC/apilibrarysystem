package com.weg.apisistemabiblioteca.dao;

import com.weg.apisistemabiblioteca.database.Conexao;
import com.weg.apisistemabiblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroDAO {

    public Livro saveBook(Livro livro)throws SQLException {
        String query = """
                INSERT INTO
                livro
                (titulo,autor,ano_publicacao)
                VALUES 
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                livro.setId(rs.getInt(1));
            }
        }
        return livro;
    }

    public List<Livro> listBooks() throws SQLException {
        String query = """
                SELECT id
                      ,titulo
                      ,autor
                      ,ano_publicacao
                FROM livro
                """;
        List<Livro> livros = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano_publicacao");

                Livro livro = new Livro(id,titulo,autor,anoPublicacao);
                livros.add(livro);
            }
        }
        return livros;
    }

    public Livro searchBooksById(int id) throws SQLException {
        String query = """
                SELECT id
                      ,titulo
                      ,autor
                      ,ano_publicacao
                FROM livro
                WHERE id = ?
                """;
        int newId = 0;
        String titulo = "";
        String autor = "";
        int anoPubli = 0;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newId = rs.getInt("id");
                titulo = rs.getString("titulo");
                autor = rs.getString("autor");
                anoPubli = rs.getInt("ano_publicacao");
            }
        }
        return new Livro(newId,titulo,autor,anoPubli);
    }

    public void updateBooks(Livro livro) throws SQLException{
        String query = "UPDATE livro SET titulo = ?, autor = ? WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getId());
            stmt.executeUpdate();
        }
    }

    public void delectBooks(int id) throws SQLException{
        String query = "DELETE FROM livro WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
