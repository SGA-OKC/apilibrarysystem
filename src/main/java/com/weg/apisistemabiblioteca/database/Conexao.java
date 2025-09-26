package com.weg.apisistemabiblioteca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static final String URL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
    public static final String USUARIO = "root";
    public static final String SENHA = "mysqlPW";

    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL,USUARIO,SENHA);
    }
}
