/*
 * Proyecto Final - Programacion III
 * Sistema de Inventario de Tienda de Musica
 */
package profinal.prograiii.jmmss.pkg2;

import java.util.Scanner;

/**
 * @author JMMSS
 */
public class PROFINALPROGRAIIIJMMSS2 {

    public static void main(String[] args) {
        Usuario     usuarios   = new Usuario();
        Instrumento inventario = new Instrumento();
        Scanner leerDatos = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("  SISTEMA DE INVENTARIO DE INSTRUMENTOS  ");
        System.out.println("==========================================");

        // ---- Bloque de autenticacion (maximo 3 intentos) ----
        // Patron identico a Sistema.java: do-while con contador
        int     contador    = 1;
        boolean estadoLogin = false;

        do {
            System.out.println("\n** INICIO DE SESION **");
            System.out.print("Usuario: ");
            String elUsuario = leerDatos.nextLine();
            System.out.print("Clave: ");
            String laClave   = leerDatos.nextLine();

            // 1. Verificar si el usuario existe (metodo en Usuario.java)
            boolean existeUsuario = usuarios.buscarUsuario(elUsuario);

            if (existeUsuario) {
                int indiceUsuario = usuarios.getIndice(elUsuario);
                String laClaveGuardada = usuarios.getClave(indiceUsuario);
                if (laClaveGuardada.equals(laClave)) {
                    estadoLogin = true;
                    System.out.println("\nAcceso correcto! Bienvenido, "
                            + usuarios.getNombre(indiceUsuario) + ".");
                }
            }

            if (!estadoLogin) {
                System.out.println("Error, usuario y/o clave incorrecta. "
                        + "Intento " + contador + " de 3.");
                contador++;
            }

        } while (contador <= 3 && !estadoLogin);

        if (!estadoLogin) {
            System.out.println("\nSe agotaron los 3 intentos. Saliendo del sistema...");
            leerDatos.close();
            return;
        }

        // ---- Menu principal ----
        boolean continuarMenu = true;

        while (continuarMenu) {

            System.out.println("\n==========================================");
            System.out.println("        MENU DE INVENTARIO MUSICAL        ");
            System.out.println("==========================================");
            System.out.println("1- Registrar nuevo instrumento");
            System.out.println("2- Listar instrumentos en stock");
            System.out.println("3- Buscar instrumento por codigo");
            System.out.println("4- Editar instrumento");
            System.out.println("5- Eliminar instrumento");
            System.out.println("6- Acerca de...");
            System.out.println("7- Cerrar sesion y Salir");
            System.out.println("");
            System.out.print("Ingrese la opcion del menu: ");

            int opcionMenu = 0;
            if (leerDatos.hasNextInt()) {
                opcionMenu = leerDatos.nextInt();
                leerDatos.nextLine();
            } else {
                leerDatos.nextLine();
            }

            switch (opcionMenu) {
                case 1:
                    System.out.println("\n--- REGISTRAR INSTRUMENTO ---");
                    System.out.print("Codigo (ej. G004): ");
                    String nuevoCodigo = leerDatos.nextLine();

                    // Verificar codigo duplicado
                    if (inventario.buscarPorCodigo(nuevoCodigo)) {
                        System.out.println("Error: ya existe un instrumento con el codigo: " + nuevoCodigo);
                        break;
                    }

                    System.out.print("Tipo (ej. Guitarra Acustica): ");
                    String nuevoTipo   = leerDatos.nextLine();
                    System.out.print("Marca: ");
                    String nuevaMarca  = leerDatos.nextLine();
                    System.out.print("Modelo: ");
                    String nuevoModelo = leerDatos.nextLine();

                    double nuevoPrecio = 0;
                    System.out.print("Precio: $");
                    if (leerDatos.hasNextDouble()) {
                        nuevoPrecio = leerDatos.nextDouble();
                    }
                    leerDatos.nextLine();

                    int nuevoStock = 0;
                    System.out.print("Cantidad en Stock: ");
                    if (leerDatos.hasNextInt()) {
                        nuevoStock = leerDatos.nextInt();
                    }
                    leerDatos.nextLine();

                    inventario.agregar(nuevoCodigo, nuevoTipo, nuevaMarca,
                                       nuevoModelo, nuevoPrecio, nuevoStock);
                    System.out.println("Instrumento registrado con exito!");
                    break;
                case 2:
                    System.out.println("\n--- LISTADO DE INSTRUMENTOS ---");
                    inventario.listarTodos();
                    break;
                case 3:
                    System.out.println("\n--- BUSCAR INSTRUMENTO ---");
                    System.out.print("Ingrese el codigo del instrumento: ");
                    String codigoBuscar = leerDatos.nextLine();

                    boolean encontrado = inventario.buscarPorCodigo(codigoBuscar);

                    if (encontrado) {
                        int indiceEncontrado = inventario.getIndice(codigoBuscar);
                        System.out.println("Instrumento encontrado:");
                        inventario.mostrarInstrumento(indiceEncontrado);
                    } else {
                        System.out.println("No se encontro ningun instrumento con el codigo: " + codigoBuscar);
                    }
                    break;
                case 4:
                    System.out.println("\n--- EDITAR INSTRUMENTO ---");
                    System.out.print("Ingrese el codigo del instrumento a editar: ");
                    String codigoEditar = leerDatos.nextLine();

                    if (!inventario.buscarPorCodigo(codigoEditar)) {
                        System.out.println("No se encontro ningun instrumento con el codigo: " + codigoEditar);
                        break;
                    }

                    int indiceEditar = inventario.getIndice(codigoEditar);
                    System.out.println("Editando: " + inventario.getMarca(indiceEditar) + " " + inventario.getModelo(indiceEditar));

                    System.out.print("Nuevo Tipo   (Actual: " + inventario.getTipo(indiceEditar)   + "): ");
                    String editTipo   = leerDatos.nextLine();
                    System.out.print("Nueva Marca  (Actual: " + inventario.getMarca(indiceEditar)  + "): ");
                    String editMarca  = leerDatos.nextLine();
                    System.out.print("Nuevo Modelo (Actual: " + inventario.getModelo(indiceEditar) + "): ");
                    String editModelo = leerDatos.nextLine();

                    double editPrecio = inventario.getPrecio(indiceEditar);
                    System.out.printf("Nuevo Precio (Actual: $%.2f): ", editPrecio);
                    if (leerDatos.hasNextDouble()) {
                        editPrecio = leerDatos.nextDouble();
                    }
                    leerDatos.nextLine();

                    int editStock = inventario.getStock(indiceEditar);
                    System.out.print("Nuevo Stock  (Actual: " + editStock + "): ");
                    if (leerDatos.hasNextInt()) {
                        editStock = leerDatos.nextInt();
                    }
                    leerDatos.nextLine();

                    inventario.editar(indiceEditar, editTipo, editMarca,
                                      editModelo, editPrecio, editStock);
                    System.out.println("Instrumento actualizado exitosamente!");
                    break;
                case 5:
                    System.out.println("\n--- ELIMINAR INSTRUMENTO ---");
                    System.out.print("Ingrese el codigo del instrumento a eliminar: ");
                    String codigoEliminar = leerDatos.nextLine();

                    if (!inventario.buscarPorCodigo(codigoEliminar)) {
                        System.out.println("No se encontro el instrumento para eliminar.");
                        break;
                    }

                    int indiceEliminar = inventario.getIndice(codigoEliminar);
                    System.out.println("Se eliminara: "
                            + inventario.getMarca(indiceEliminar) + " "
                            + inventario.getModelo(indiceEliminar));
                    System.out.print("Esta seguro? (s/n): ");
                    String confirmar = leerDatos.nextLine();

                    if (confirmar.equalsIgnoreCase("s")) {
                        inventario.eliminar(indiceEliminar);
                        System.out.println("Instrumento eliminado exitosamente!");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }
                    break;
                case 6:
                    System.out.println("\n--- Acerca de ---");
                    System.out.println("Proyecto Final Programacion III");
                    System.out.println("Sistema de Inventario para Tienda de Instrumentos");
                    System.out.println("Desarrollado por el equipo JMMSS.");
                    System.out.println("Ciclo 02-2026 | UTEC");
                    System.out.println("-----------------");
                    break;
                case 7:
                    System.out.println("\nCerrando sesion y saliendo del sistema... Hasta pronto!");
                    continuarMenu = false;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor, ingrese un numero del 1 al 7.");
            }
        }

        leerDatos.close();
    }
}