/*
 * Proyecto Final - Programacion III
 * Sistema de Inventario de Tienda de Musica
 */
package profinal.prograiii.jmmss.pkg2;

import java.util.ArrayList;

/**
 * @author JMMSS
 */
public class Instrumento {

    // ArrayLists paralelos (un indice = un instrumento)
    private ArrayList codigos   = new ArrayList();
    private ArrayList tipos     = new ArrayList();
    private ArrayList marcas    = new ArrayList();
    private ArrayList modelos   = new ArrayList();
    private ArrayList precios   = new ArrayList();
    private ArrayList stocks    = new ArrayList();

    /**
     * Constructor: carga los datos predeterminados del inventario.
     */
    public Instrumento() {
        agregar("G001", "Guitarra Acustica",   "Yamaha",    "F310",          199.99,  8);
        agregar("G002", "Guitarra Electrica",  "Fender",    "Stratocaster",  799.99,  3);
        agregar("G003", "Guitarra Clasica",    "Alhambra",  "1C",            250.00,  5);
        agregar("B001", "Bajo Electrico",      "Ibanez",    "GSR200",        329.99,  4);
        agregar("T001", "Teclado",             "Yamaha",    "PSR-E473",      350.00,  5);
        agregar("S001", "Sintetizador",        "Roland",    "Juno-D",        899.99,  2);
        agregar("D001", "Bateria Acustica",    "Pearl",     "Export Series", 650.00,  2);
        agregar("D002", "Bateria Electronica", "Roland",    "TD-1K",         499.99,  3);
        agregar("V001", "Violin",              "Stentor",   "Student I",     120.00,  6);
        agregar("P001", "Pedal Reverb",        "BOSS",      "RV-6",          150.00, 10);
        agregar("M001", "Mezcladora",          "Behringer", "Xenyx 1204USB", 180.00,  4);
        agregar("A001", "Amplificador",        "Marshall",  "MG15",          189.99,  7);
    }

    // --- Metodo AGREGAR ---
    /**
     * Agrega un instrumento al inventario.
     */
    public void agregar(String codigo, String tipo, String marca, String modelo, double precio, int stock) {
        codigos.add(codigo);
        tipos.add(tipo);
        marcas.add(marca);
        modelos.add(modelo);
        precios.add(precio);
        stocks.add(stock);
    }

    // --- Metodo BUSCAR ---
    /**
     * Busca si existe un instrumento con el codigo dado.
     */
    public boolean buscarPorCodigo(String elCodigo) {
        for (int i = 0; i < codigos.size(); i++) {
            if (codigos.get(i).toString().equalsIgnoreCase(elCodigo)) {
                return true;
            }
        }
        return false;
    }

    // --- Metodo GET INDICE ---
    /**
     * Retorna el indice del instrumento con el codigo dado.
     * Retorna -1 si no existe.
     */
    public int getIndice(String elCodigo) {
        for (int i = 0; i < codigos.size(); i++) {
            if (codigos.get(i).toString().equalsIgnoreCase(elCodigo)) {
                return i;
            }
        }
        return -1;
    }

    // --- Metodo ELIMINAR ---
    /**
     * Elimina el instrumento en la posicion dada.
     */
    public void eliminar(int indice) {
        codigos.remove(indice);
        tipos.remove(indice);
        marcas.remove(indice);
        modelos.remove(indice);
        precios.remove(indice);
        stocks.remove(indice);
    }

    // --- Metodo EDITAR ---
    /**
     * Actualiza los campos de un instrumento en la posicion dada.
     */
    public void editar(int indice, String tipo, String marca, String modelo, double precio, int stock) {
        tipos.set(indice, tipo);
        marcas.set(indice, marca);
        modelos.set(indice, modelo);
        precios.set(indice, precio);
        stocks.set(indice, stock);
    }

    // --- Getters por indice ---
    public String getCodigo(int i)  { return codigos.get(i).toString().trim(); }
    public String getTipo(int i)    { return tipos.get(i).toString().trim(); }
    public String getMarca(int i)   { return marcas.get(i).toString().trim(); }
    public String getModelo(int i)  { return modelos.get(i).toString().trim(); }
    public double getPrecio(int i)  { return (double) precios.get(i); }
    public int    getStock(int i)   { return (int) stocks.get(i); }

    // --- Utilidades de listado ---
    /** Retorna la cantidad total de instrumentos en el inventario. */
    public int getCantidad() {
        return codigos.size();
    }

    /** Imprime los datos de un instrumento dado su indice. */
    public void mostrarInstrumento(int i) {
        System.out.println("==============================");
        System.out.println("Codigo  : " + getCodigo(i));
        System.out.println("Tipo    : " + getTipo(i));
        System.out.println("Marca   : " + getMarca(i));
        System.out.println("Modelo  : " + getModelo(i));
        System.out.printf( "Precio  : $%.2f%n", getPrecio(i));
        System.out.println("Stock   : " + getStock(i) + " unidades");
        System.out.println("==============================");
    }

    /** Imprime todos los instrumentos del inventario. */
    public void listarTodos() {
        if (codigos.isEmpty()) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("Total de instrumentos: " + getCantidad());
        for (int i = 0; i < codigos.size(); i++) {
            mostrarInstrumento(i);
        }
    }
}