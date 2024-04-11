package com.softtek.persistencia;

import com.softtek.modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccesoEmpleado extends Conexion {

    private Connection conexion;

    public AccesoEmpleado() {
        Conexion con = new Conexion();
        try {
            con.abriConexion();
            conexion = con.getMiConexion();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
    }

    public void nuevoEmpleado() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Datos del nuevo empleado:");
            System.out.print("ID: ");
            int idEmpleado = scanner.nextInt();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellidos = scanner.nextLine();

            int jefe;
            do {
                System.out.print("ID del jefe (1 o 2): ");
                jefe = scanner.nextInt();
                if (jefe != 1 && jefe != 2) {
                    System.out.println("El ID es incorrecto");
                }
            } while (jefe != 1 && jefe != 2);




            String sql = "INSERT INTO employees (employee_id, first_name, last_name, reports_to) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, idEmpleado);
                statement.setString(2, nombre);
                statement.setString(3, apellidos);
                statement.setInt(4, jefe);
                statement.executeUpdate();
                System.out.println("insertado correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }



    public void actualizarEmpleado() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("ID del empleado que desea actualizar: ");
            int idEmpleado = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nombre del empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido del empleado: ");
            String apellidos = scanner.nextLine();

            String sql = "UPDATE employees SET first_name = ?, last_name = ? WHERE employee_id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, apellidos);
                statement.setInt(3, idEmpleado);
                int filasActualizadas = statement.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("actualizado correctamente.");
                } else {
                    System.out.println("No se encontró ningún empleado");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public void eliminarEmpleado() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("ID del empleado que desea eliminar: ");
            int idEmpleado = scanner.nextInt();

            String sql = "DELETE FROM employees WHERE employee_id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, idEmpleado);
                int filasEliminadas = statement.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún empleado");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try {
            try (PreparedStatement statement = conexion.prepareStatement(sql);
                 ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int idEmpleado = result.getInt("employee_id");
                    String nombre = result.getString("first_name");
                    String apellidos = result.getString("last_name");
                    int jefe = result.getInt("reports_to");
                    Empleado empleado = new Empleado(idEmpleado, nombre, apellidos, jefe);
                    empleados.add(empleado);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los empleados: " + e.getMessage());
        }
        return empleados;
    }
}
