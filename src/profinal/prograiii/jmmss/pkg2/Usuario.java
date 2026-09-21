/*
 * Proyecto Final - Programacion III
 * Sistema de Inventario de Tienda de Musica
 */
package profinal.prograiii.jmmss.pkg2;

import java.util.ArrayList;

/**
 * @author JMMSS
 */
public class Usuario {
    private ArrayList correos   = new ArrayList();
    private ArrayList nombres   = new ArrayList();
    private ArrayList apellidos = new ArrayList();
    private ArrayList claves    = new ArrayList();
    private ArrayList niveles   = new ArrayList();

    public Usuario() {
        // Usuarios administradores
        agregar("admin@tienda",   "Administrador", "General",  "admin123",  3);
        agregar("gerente@tienda", "Carlos",        "Martinez", "gerente01", 3);

        // Usuarios asistentes
        agregar("asistente01@tienda", "Maria", "Lopez",   "asist01", 2);
        agregar("asistente02@tienda", "Pedro", "Ramirez", "asist02", 2);

        // Clientes generados en bucle (igual que Usuarios.java)
        String i;
        int elNivel = 1;
        for (int contador = 1; contador <= 20; contador++) {
            i = "" + contador;
            if (contador < 10) {
                i = "0" + contador;
            }
            agregar("cliente" + i + "@tienda",
                    "Cliente " + i,
                    "Apellido " + i,
                    "clave" + i,
                    elNivel);
            elNivel++;
            if (elNivel == 4) {
                elNivel = 1;
            }
        }
    }

    // --- Metodo AGREGAR usuario ---
    /**
     * Agrega un nuevo usuario al sistema.
     */
    public void agregar(String correo, String nombre, String apellido, String clave, int nivel) {
        correos.add(correo);
        nombres.add(nombre);
        apellidos.add(apellido);
        claves.add(clave);
        niveles.add(nivel);
    }

    // --- Metodo BUSCAR usuario ---
    /**
     * Verifica si un correo existe en el sistema.
     * Retorna true si existe, false si no.
     */
    public boolean buscarUsuario(String elCorreo) {
        return correos.contains(elCorreo);
    }

    // --- Metodo GET INDICE ---
    /**
     * Retorna la posicion del usuario en los ArrayLists.
     */
    public int getIndice(String elCorreo) {
        return correos.indexOf(elCorreo);
    }

    // --- Getters por indice ---
    public String getClave(int i)    { return claves.get(i).toString().trim(); }
    public String getNombre(int i)   { return nombres.get(i).toString().trim(); }
    public String getApellido(int i) { return apellidos.get(i).toString().trim(); }
    public int    getNivel(int i)    { return (int) niveles.get(i); }
    public String getCorreo(int i)   { return correos.get(i).toString().trim(); }

    // --- Utilidades ---
    /** Retorna la cantidad total de usuarios registrados. */
    public int getCantidad() {
        return correos.size();
    }

    /** Imprime los datos de un usuario dado su indice. */
    public void mostrarUsuario(int i) {
        System.out.println("==============================");
        System.out.println("Correo   : " + getCorreo(i));
        System.out.println("Nombre   : " + getNombre(i) + " " + getApellido(i));
        System.out.println("Nivel    : " + getNivel(i));
        System.out.println("==============================");
    }

    /** Lista todos los usuarios del sistema. */
    public void listarTodos() {
        System.out.println("Total de usuarios: " + getCantidad());
        for (int i = 0; i < correos.size(); i++) {
            mostrarUsuario(i);
        }
    }
}