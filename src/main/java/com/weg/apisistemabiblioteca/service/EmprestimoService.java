package com.weg.apisistemabiblioteca.service;

import com.weg.apisistemabiblioteca.dao.EmprestimoDAO;
import com.weg.apisistemabiblioteca.model.Emprestimo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoDAO repository;

    public EmprestimoService(EmprestimoDAO repository) {
        this.repository = repository;
    }

    public Emprestimo createLoan(Emprestimo emprestimo) throws SQLException {
        return repository.registerLoan(emprestimo);
    }

    //public Emprestimo
    public Emprestimo searchLoanBysID(int id) throws SQLException {
        List<Emprestimo> emprestimos = repository.listLoans();

        for (Emprestimo em : emprestimos) {
            if (em.getId() == id) {
                repository.searchLoansById(id);
            }
        }
        throw new RuntimeException("ID não existe no sistema!!");
    }

    // ver sobre este melhor
    /*
    public Emprestimo finishLoan(LocalDate dataDevolucao, Emprestimo emprestimo) throws SQLException {
        List<Emprestimo> emprestimos = repository.listLoans();

        for ()
    }
    */

}
