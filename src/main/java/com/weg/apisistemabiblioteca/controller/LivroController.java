package com.weg.apisistemabiblioteca.controller;

import com.weg.apisistemabiblioteca.model.Livro;
import com.weg.apisistemabiblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livro")

public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public Livro createBook(
            @RequestBody Livro livro) {
        Livro newUser = new Livro();
        try {
            newUser = service.createBook(livro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    @GetMapping
    public List<Livro> listBook() {
        List<Livro> book = new ArrayList<>();
        try{
             book = service.listBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @GetMapping("/{id}")
    public Livro getBookById(@PathVariable int id){
        Livro bookById = new Livro();

        try{
            bookById = service.searchBooksById(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return bookById;
    }

    @PutMapping("/{id}")
    public Livro updateBook(@PathVariable int id, @RequestBody Livro livro){
        Livro newBook = new Livro();

        try{
            newBook = service.updateBooks(id, livro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newBook;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id){
        try{
            service.deleteBooks(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

