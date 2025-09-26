package com.weg.apisistemabiblioteca.service;

import com.weg.apisistemabiblioteca.dao.LivroDAO;
import com.weg.apisistemabiblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroDAO repository;


    public LivroService(LivroDAO repository) {
        this.repository = repository;
    }

    public Livro createBook(Livro livro) throws SQLException {
        return repository.saveBook(livro);
    }

    public List<Livro> listBooks() throws SQLException {
        return repository.listBooks();
    }

    public void deleteBooks(int id) throws SQLException {
        List<Livro> livros = repository.listBooks();

        for (Livro livro : livros) {
            if (livro.getId() == id) {
                repository.delectBooks(id);
                return;
            }
        }
        throw  new RuntimeException("ID do livro não existe!!");
    }

    // OBS !
    public Livro searchBooksById(int id) throws SQLException {
        List<Livro> livros = repository.listBooks();

        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return repository.searchBooksById(id);
            }
        }
        throw new RuntimeException("ID do livro não existe!!");
    }

    public Livro updateBooks(int id, Livro livro) throws SQLException {
        List<Livro> livros = repository.listBooks();
        livro.setId(id);

        for (Livro li : livros) {
            if (li.getId() == livro.getId()) {
                repository.updateBooks(livro);
            }
        }
        throw new RuntimeException("ID do livro não existe!!");
    }

}


