/*
 * Proyecto Final - Programacion III
 * Sistema de Inventario de Tienda de Musica
 */
package profinal.prograiii.jmmss.pkg2;

import java.util.Scanner;

/**
 * Niveles de acceso:
 *   1 -> Usuario    : listar y buscar instrumentos
 *   2 -> Asistente  : nivel 1 + registrar y editar instrumentos
 *   3 -> Administrador: nivel 2 + eliminar instrumentos + gestion de usuarios
 *
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

        int     contador      = 1;
        boolean estadoLogin   = false;
        int     nivelUsuario  = 0;
        String  nombreUsuario = "";

        do {
            System.out.println("\n** INICIO DE SESION **");
            System.out.print("Usuario: ");
            String elUsuario = leerDatos.nextLine();
            System.out.print("Clave: ");
            String laClave   = leerDatos.nextLine();

            boolean existeUsuario = usuarios.buscarUsuario(elUsuario);

            if (existeUsuario) {
                int indiceUsuario = usuarios.getIndice(elUsuario);
                String laClaveGuardada = usuarios.getClave(indiceUsuario);
                if (laClaveGuardada.equals(laClave)) {
                    estadoLogin   = true;
                    nivelUsuario  = usuarios.getNivel(indiceUsuario);
                    nombreUsuario = usuarios.getNombre(indiceUsuario);
                    System.out.println("\nAcceso correcto! Bienvenido, " + nombreUsuario
                            + " [" + usuarios.getNombreNivel(nivelUsuario) + "].");
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

        boolean continuarMenu = true;

        while (continuarMenu) {

            System.out.println("\n==========================================");
            System.out.println("        MENU DE INVENTARIO MUSICAL        ");
            System.out.println("  Sesion: " + nombreUsuario + " [Nivel " + nivelUsuario + " - " + usuarios.getNombreNivel(nivelUsuario) + "]");
            System.out.println("==========================================");

            System.out.println("1- Listar instrumentos en stock");
            System.out.println("2- Buscar instrumento por codigo");

            if (nivelUsuario >= 2) {
                System.out.println("3- Registrar nuevo instrumento");
                System.out.println("4- Editar instrumento");
            }

            if (nivelUsuario >= 3) {
                System.out.println("5- Eliminar instrumento");
                System.out.println("6- Gestion de usuarios");
            }

            System.out.println("7- Acerca de...");
            System.out.println("8- Cerrar sesion y Salir");
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
                    System.out.println("\n--- LISTADO DE INSTRUMENTOS ---");
                    inventario.listarTodos();
                    break;
                case 2:
                    System.out.println("\n--- BUSCAR INSTRUMENTO ---");
                    System.out.print("Ingrese el codigo del instrumento: ");
                    String codigoBuscar = leerDatos.nextLine();

                    boolean instEncontrado = inventario.buscarPorCodigo(codigoBuscar);

                    if (instEncontrado) {
                        int indiceInst = inventario.getIndice(codigoBuscar);
                        System.out.println("Instrumento encontrado:");
                        inventario.mostrarInstrumento(indiceInst);
                    } else {
                        System.out.println("No se encontro ningun instrumento con el codigo: " + codigoBuscar);
                    }
                    break;
                case 3:
                    if (nivelUsuario < 2) {
                        System.out.println("\nAcceso denegado. Se requiere nivel Asistente o superior.");
                        break;
                    }
                    System.out.println("\n--- REGISTRAR INSTRUMENTO ---");
                    System.out.print("Codigo (ej. G004): ");
                    String nuevoCodigo = leerDatos.nextLine();

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
                case 4:
                    if (nivelUsuario < 2) {
                        System.out.println("\nAcceso denegado. Se requiere nivel Asistente o superior.");
                        break;
                    }
                    System.out.println("\n--- EDITAR INSTRUMENTO ---");
                    System.out.print("Ingrese el codigo del instrumento a editar: ");
                    String codigoEditar = leerDatos.nextLine();

                    if (!inventario.buscarPorCodigo(codigoEditar)) {
                        System.out.println("No se encontro ningun instrumento con el codigo: " + codigoEditar);
                        break;
                    }

                    int indiceEditar = inventario.getIndice(codigoEditar);
                    System.out.println("Editando: " + inventario.getMarca(indiceEditar)
                            + " " + inventario.getModelo(indiceEditar));

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
                    if (nivelUsuario < 3) {
                        System.out.println("\nAcceso denegado. Se requiere nivel Administrador.");
                        break;
                    }
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
                    String confirmarElim = leerDatos.nextLine();

                    if (confirmarElim.equalsIgnoreCase("s")) {
                        inventario.eliminar(indiceEliminar);
                        System.out.println("Instrumento eliminado exitosamente!");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }
                    break;
                case 6:
                    if (nivelUsuario < 3) {
                        System.out.println("\nAcceso denegado. Se requiere nivel Administrador.");
                        break;
                    }

                    boolean continuarUsuarios = true;
                    while (continuarUsuarios) {

                        System.out.println("\n--- GESTION DE USUARIOS ---");
                        System.out.println("1- Listar usuarios");
                        System.out.println("2- Buscar usuario por correo");
                        System.out.println("3- Registrar nuevo usuario");
                        System.out.println("4- Editar usuario");
                        System.out.println("5- Eliminar usuario");
                        System.out.println("6- Volver al menu principal");
                        System.out.println("");
                        System.out.print("Ingrese la opcion: ");

                        int opcionUser = 0;
                        if (leerDatos.hasNextInt()) {
                            opcionUser = leerDatos.nextInt();
                            leerDatos.nextLine();
                        } else {
                            leerDatos.nextLine();
                        }

                        switch (opcionUser) {
                            case 1:
                                System.out.println("\n--- LISTADO DE USUARIOS ---");
                                usuarios.listarTodos();
                                break;
                            case 2:
                                System.out.println("\n--- BUSCAR USUARIO ---");
                                System.out.print("Ingrese el correo del usuario: ");
                                String correoBuscar = leerDatos.nextLine();

                                boolean userEncontrado = usuarios.buscarUsuario(correoBuscar);

                                if (userEncontrado) {
                                    int indiceUser = usuarios.getIndice(correoBuscar);
                                    System.out.println("Usuario encontrado:");
                                    usuarios.mostrarUsuario(indiceUser);
                                } else {
                                    System.out.println("No se encontro ningun usuario con el correo: " + correoBuscar);
                                }
                                break;
                            case 3:
                                System.out.println("\n--- REGISTRAR USUARIO ---");
                                System.out.print("Correo (ej. nuevo@tienda): ");
                                String nuevoCorreo = leerDatos.nextLine();

                                if (usuarios.buscarUsuario(nuevoCorreo)) {
                                    System.out.println("Error: ya existe un usuario con el correo: " + nuevoCorreo);
                                    break;
                                }

                                System.out.print("Nombre: ");
                                String nuevoNombre    = leerDatos.nextLine();
                                System.out.print("Apellido: ");
                                String nuevoApellido  = leerDatos.nextLine();
                                System.out.print("Clave: ");
                                String nuevaClave     = leerDatos.nextLine();

                                int nuevoNivel = 1;
                                System.out.println("Nivel de acceso:");
                                System.out.println("  1 - Usuario");
                                System.out.println("  2 - Asistente");
                                System.out.println("  3 - Administrador");
                                System.out.print("Seleccione el nivel: ");
                                if (leerDatos.hasNextInt()) {
                                    nuevoNivel = leerDatos.nextInt();
                                    if (nuevoNivel < 1 || nuevoNivel > 3) {
                                        nuevoNivel = 1;
                                    }
                                }
                                leerDatos.nextLine();

                                usuarios.agregar(nuevoCorreo, nuevoNombre, nuevoApellido,
                                                 nuevaClave, nuevoNivel);
                                System.out.println("Usuario registrado con exito!");
                                break;
                            case 4:
                                System.out.println("\n--- EDITAR USUARIO ---");
                                System.out.print("Ingrese el correo del usuario a editar: ");
                                String correoEditar = leerDatos.nextLine();

                                if (!usuarios.buscarUsuario(correoEditar)) {
                                    System.out.println("No se encontro ningun usuario con el correo: " + correoEditar);
                                    break;
                                }

                                int indiceEditarUser = usuarios.getIndice(correoEditar);
                                System.out.println("Editando: "
                                        + usuarios.getNombre(indiceEditarUser) + " "
                                        + usuarios.getApellido(indiceEditarUser));

                                System.out.print("Nuevo Nombre    (Actual: " + usuarios.getNombre(indiceEditarUser)   + "): ");
                                String editNombre   = leerDatos.nextLine();
                                System.out.print("Nuevo Apellido  (Actual: " + usuarios.getApellido(indiceEditarUser) + "): ");
                                String editApellido = leerDatos.nextLine();
                                System.out.print("Nueva Clave     (dejar en blanco para mantener actual): ");
                                String editClave    = leerDatos.nextLine();
                                if (editClave.isEmpty()) {
                                    editClave = usuarios.getClave(indiceEditarUser);
                                }

                                int editNivel = usuarios.getNivel(indiceEditarUser);
                                System.out.println("Nuevo Nivel     (Actual: " + editNivel
                                        + " - " + usuarios.getNombreNivel(editNivel) + "):");
                                System.out.println("  1 - Usuario  |  2 - Asistente  |  3 - Administrador");
                                System.out.print("Seleccione el nivel: ");
                                if (leerDatos.hasNextInt()) {
                                    int temp = leerDatos.nextInt();
                                    if (temp >= 1 && temp <= 3) {
                                        editNivel = temp;
                                    }
                                }
                                leerDatos.nextLine();

                                usuarios.editar(indiceEditarUser, editNombre, editApellido,
                                                editClave, editNivel);
                                System.out.println("Usuario actualizado exitosamente!");
                                break;
                            case 5:
                                System.out.println("\n--- ELIMINAR USUARIO ---");
                                System.out.print("Ingrese el correo del usuario a eliminar: ");
                                String correoEliminar = leerDatos.nextLine();

                                if (!usuarios.buscarUsuario(correoEliminar)) {
                                    System.out.println("No se encontro el usuario para eliminar.");
                                    break;
                                }

                                int indiceEliminarUser = usuarios.getIndice(correoEliminar);
                                System.out.println("Se eliminara: "
                                        + usuarios.getNombre(indiceEliminarUser) + " "
                                        + usuarios.getApellido(indiceEliminarUser)
                                        + " [" + usuarios.getNombreNivel(usuarios.getNivel(indiceEliminarUser)) + "]");
                                System.out.print("Esta seguro? (s/n): ");
                                String confirmarUser = leerDatos.nextLine();

                                if (confirmarUser.equalsIgnoreCase("s")) {
                                    usuarios.eliminar(indiceEliminarUser);
                                    System.out.println("Usuario eliminado exitosamente!");
                                } else {
                                    System.out.println("Eliminacion cancelada.");
                                }
                                break;

                            case 6:
                                continuarUsuarios = false;
                                break;

                            default:
                                System.out.println("\nOpcion no valida. Ingrese un numero del 1 al 6.");
                        }
                    }
                    break;
                case 7:
                    System.out.println("\n--- Acerca de ---");
                    System.out.println("Proyecto Final Programacion III");
                    System.out.println("Sistema de Inventario para Tienda de Instrumentos");
                    System.out.println("Desarrollado por el equipo JMMSS.");
                    System.out.println("Ciclo 02-2026 | UTEC");
                    System.out.println("-----------------");
                    break;
                case 8:
                    System.out.println("\nCerrando sesion y saliendo del sistema... Hasta pronto!");
                    continuarMenu = false;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor, ingrese una opcion del menu.");
            }
        }

        leerDatos.close();
    }
}