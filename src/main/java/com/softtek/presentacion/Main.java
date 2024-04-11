package com.softtek.presentacion;

import com.softtek.modelo.Empleado;
import com.softtek.persistencia.AccesoEmpleado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccesoEmpleado accesoEmpleado = new AccesoEmpleado();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione la acción que desea realizar:");
        System.out.println("1. Insertar un nuevo empleado");
        System.out.println("2. Actualizar un empleado existente");
        System.out.println("3. Eliminar un empleado");
        System.out.println("4. Mostrar todos los empleados");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                accesoEmpleado.nuevoEmpleado();
                break;
            case 2:
                accesoEmpleado.actualizarEmpleado();
                break;
            case 3:
                accesoEmpleado.eliminarEmpleado();
                break;
            case 4:
                mostrarEmpleados(accesoEmpleado);
                break;
            default:
                System.out.println("Opción no válida");
        }

        scanner.close();
    }

    private static void mostrarEmpleados(AccesoEmpleado accesoEmpleado) {
        System.out.println("Lista de empleados:");
        for (Empleado empleado : accesoEmpleado.obtenerTodosEmpleados()) {
            System.out.println(empleado);
        }
    }
}
