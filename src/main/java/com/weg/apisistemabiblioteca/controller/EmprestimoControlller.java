package com.weg.apisistemabiblioteca.controller;

import com.weg.apisistemabiblioteca.model.Emprestimo;
import com.weg.apisistemabiblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoControlller {

    private final EmprestimoService service;

    public EmprestimoControlller(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo registerLoan(
            @RequestBody Emprestimo emprestimo) {
        Emprestimo newLoan = new Emprestimo();

        try{
            newLoan = service.createLoan(emprestimo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newLoan;
    }
}
