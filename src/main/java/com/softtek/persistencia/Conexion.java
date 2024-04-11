package com.softtek.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Atributos
    protected Connection miConexion;

    // Métodos
    public void abriConexion() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        miConexion=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/northwind"
        , "postgres",  "1234");
        System.out.println("Conexión exitosa.");

    }

    public Conexion() {
    }

    public Connection getMiConexion() {
        return miConexion;
    }

    public void setMiConexion(Connection miConexion) {
        this.miConexion = miConexion;
    }


}
