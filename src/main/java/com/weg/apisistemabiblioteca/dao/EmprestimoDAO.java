package com.weg.apisistemabiblioteca.dao;

import com.weg.apisistemabiblioteca.database.Conexao;
import com.weg.apisistemabiblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoDAO {

    public Emprestimo registerLoan(Emprestimo emprestimo) throws SQLException {
        String query = "INSERT INTO emprestimo(livro_id,usuario_id,data_emprestimo,data_devolucao) VALUES(?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, emprestimo.getLivroId());
            stmt.setInt(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                emprestimo.setId(1);
            }
        }
        return emprestimo;
    }

    public List<Emprestimo> listLoans() throws SQLException{
        String query = """
                SELECT id
                      ,livro_id
                      ,usuario_id
                      ,data_emprestimo
                      ,data_devolucao
                FROM emprestimo
                """;
        List<Emprestimo> emprestimos = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                int livroId = rs.getInt("livro_id");
                int usuarioId = rs.getInt("usuario_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();

                if(rs.getDate("data_devolucao") == null){
                    emprestimos.add(new Emprestimo(id, livroId, usuarioId, dataEmprestimo));
                }else{
                    LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();
                    emprestimos.add(new Emprestimo(id, livroId, usuarioId, dataEmprestimo, dataDevolucao));
                }
            }
        }
        return emprestimos;
    }

    public Emprestimo searchLoansById(int id) throws SQLException {
        String query = """
                SELECT id
                      ,livro_id
                      ,usuario_id
                      ,data_emprestimo
                      ,data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;
        int newUser = 0;
        int livroId = 0;
        int usuarioId = 0;
        LocalDate dataEmpre = null;
        LocalDate dataDevo = null;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                newUser = rs.getInt("id");
                livroId = rs.getInt("livro_id");
                usuarioId = rs.getInt("usuario_id");
                dataEmpre = rs.getDate("data_emprestimo").toLocalDate();
                if(rs.getDate("data_devolucao") != null){
                    dataDevo = rs.getDate("data_devolucao").toLocalDate();
                }
            }
        }
        return new Emprestimo(newUser,livroId,usuarioId,dataEmpre,dataDevo);
    }


    public void updateLoans(Emprestimo emprestimo) throws SQLException {
        String query = "UPDATE emprestimo SET data_emprestimo = ?,data_devolucao = ? WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(2, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(3, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteLoans(int id) throws SQLException {
        String query = "DELETE FROM emprestimo WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
