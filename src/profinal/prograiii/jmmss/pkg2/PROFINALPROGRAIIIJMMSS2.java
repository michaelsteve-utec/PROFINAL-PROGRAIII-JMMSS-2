/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package profinal.prograiii.jmmss.pkg2;

import java.util.Scanner;

/**
 *
 * @author MICHAELP
 */
public class PROFINALPROGRAIIIJMMSS2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ejecutarPrograma = true;

        while (ejecutarPrograma) {
            int intentos = 0;
            boolean logueado = false;

            System.out.println("\n--- ACTIVIDAD 1 - PROGRA III ---");

            while (intentos < 3 && !logueado) {
                System.out.println("\n*** LOGIN ***");
                System.out.print("Usuario: ");
                String usuario = scanner.nextLine();
                System.out.print("Clave: ");
                String clave = scanner.nextLine();

                if (usuario.equals("PROG3") && clave.equals("123")) {
                    logueado = true;
                    System.out.println("Acceso correcto. ¡Bienvenido!");
                } else {
                    intentos++;
                    System.out.println("Credenciales incorrectas. Intento " + intentos + " de 3.");
                }
            }

            if (!logueado) {
                System.out.println("Se han agotado los intentos permitidos. Saliendo del sistema...");
                break;
            }

            boolean continuarMenu = true;

            while (continuarMenu) {
                System.out.println("\n==========================================");
                System.out.println("               MENÚ PRINCIPAL               ");
                System.out.println("==========================================");
                System.out.println("1- Ejercicios estructura de control selectivas if-else");
                System.out.println("2- Ejercicios estructura de control selectiva switch");
                System.out.println("3- Ejercicios estructura de control repetitiva while");
                System.out.println("4- Ejercicios estructura de control repetitiva do-while");
                System.out.println("5- Ejercicios estructura de control repetitiva for");
                System.out.println("6- Acerca de…");
                System.out.println("7- Cerrar sesión");
                System.out.println("8- Salir");
                System.out.print("Seleccione una opción: ");

                String entrada = scanner.nextLine();
                int opcion = 0;

                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    // Si no es un número, se irá al default del switch
                }

                switch (opcion) {
                    case 1:
                        menuIfElse(scanner);
                        break;
                    case 2:
                        menuSwitch(scanner);
                        break;
                    case 3:
                        menuWhile(scanner);
                        break;
                    case 4:
                        menuDoWhile(scanner);
                        break;
                    case 5:
                        menuFor(scanner);
                        break;
                    case 6:
                        System.out.println("\n--- Acerca de ---");
                        System.out.println("Actividad 1 - Estructuras de Control.");
                        System.out.println("Asignatura: Programación III.");
                        System.out.println("Sección: 01");
                        System.out.println("Alumno: Michael Steve Pérez Guandique");
                        System.out.println("Carnet: 29-4596-2011");
                        System.out.println("-----------------");
                        break;
                    case 7:
                        System.out.println("\nCerrando sesión...\n");
                        continuarMenu = false;
                        break;
                    case 8:
                        System.out.println("\nSaliendo del sistema... ¡Hasta pronto!");
                        continuarMenu = false;
                        ejecutarPrograma = false;
                        break;
                    default:
                        System.out.println("\nOpción no válida. Intente de nuevo.");
                }
            }
        }
        scanner.close();
    }

    private static void menuIfElse(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Submenú de estructura de control if-else ---");
            System.out.println("1. Mayor de dos números");
            System.out.println("2. Número par o impar");
            System.out.println("3. Positivo, negativo o cero");
            System.out.println("4. Aprobado o reprobado (nota mínima 6)");
            System.out.println("5. Regresar al menú principal");
            System.out.print("Seleccione un ejercicio: ");

            String entrada = scanner.nextLine();
            int opcion = 0;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (Exception e) {
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejercicio 1: Mayor de dos números ---");
                    System.out.print("Ingrese el primer número: ");
                    double n1 = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingrese el segundo número: ");
                    double n2 = Double.parseDouble(scanner.nextLine());
                    if (n1 > n2) {
                        System.out.println("El número " + n1 + " es mayor.");
                    } else if (n2 > n1) {
                        System.out.println("El número " + n2 + " es mayor.");
                    } else {
                        System.out.println("Ambos números son iguales.");
                    }
                    break;
                case 2:
                    System.out.println("\n--- Ejercicio 2: Número par o impar ---");
                    System.out.print("Ingrese un número entero: ");
                    int numPar = Integer.parseInt(scanner.nextLine());
                    if (numPar % 2 == 0) {
                        System.out.println("El número es Par.");
                    } else {
                        System.out.println("El número es Impar.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- Ejercicio 3: Positivo, negativo o cero ---");
                    System.out.print("Ingrese un número: ");
                    double numPos = Double.parseDouble(scanner.nextLine());
                    if (numPos > 0) {
                        System.out.println("El número es Positivo.");
                    } else if (numPos < 0) {
                        System.out.println("El número es Negativo.");
                    } else {
                        System.out.println("El número es Cero.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- Ejercicio 4: Aprobado o reprobado ---");
                    System.out.print("Ingrese la calificación (0-10): ");
                    double nota = Double.parseDouble(scanner.nextLine());
                    if (nota >= 6.0 && nota <= 10.0) {
                        System.out.println("Estado: Aprobado.");
                    } else if (nota >= 0 && nota < 6.0) {
                        System.out.println("Estado: Reprobado.");
                    } else {
                        System.out.println("Nota fuera de rango.");
                    }
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida.");
            }
        }
    }

    private static void menuSwitch(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Submenú de estructura de control switch ---");
            System.out.println("1. Día de la semana (1-7)");
            System.out.println("2. Menú de opciones simple");
            System.out.println("3. Tipo de vehículo (1-Carro, 2-Moto, 3-Bicicleta)");
            System.out.println("4. Vocal o consonante");
            System.out.println("5. Regresar al menú principal");
            System.out.print("Seleccione un ejercicio: ");

            String entrada = scanner.nextLine();
            int opcion = 0;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (Exception e) {
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejercicio 1: Día de la semana ---");
                    System.out.print("Ingrese un número (1-7): ");
                    int dia = Integer.parseInt(scanner.nextLine());
                    switch (dia) {
                        case 1:
                            System.out.println("Lunes");
                            break;
                        case 2:
                            System.out.println("Martes");
                            break;
                        case 3:
                            System.out.println("Miércoles");
                            break;
                        case 4:
                            System.out.println("Jueves");
                            break;
                        case 5:
                            System.out.println("Viernes");
                            break;
                        case 6:
                            System.out.println("Sábado");
                            break;
                        case 7:
                            System.out.println("Domingo");
                            break;
                        default:
                            System.out.println("Número no válido.");
                    }
                    break;
                case 2:
                    System.out.println("\n--- Ejercicio 3: Menú de opciones ---");
                    System.out.println("A - Saludar");
                    System.out.println("B - Despedirse");
                    System.out.print("Ingrese opción (A/B): ");
                    String optLetra = scanner.nextLine().toUpperCase();
                    switch (optLetra) {
                        case "A":
                            System.out.println("¡Hola! Qué tal.");
                            break;
                        case "B":
                            System.out.println("¡Adiós! Hasta luego.");
                            break;
                        default:
                            System.out.println("Opción desconocida.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- Ejercicio 4: Tipo de vehículo ---");
                    System.out.print("Ingrese código de vehículo (1-Carro, 2-Moto, 3-Bicicleta): ");
                    int vehiculo = Integer.parseInt(scanner.nextLine());
                    switch (vehiculo) {
                        case 1:
                            System.out.println("Carro tiene 4 ruedas.");
                            break;
                        case 2:
                            System.out.println("Moto tiene 2 ruedas.");
                            break;
                        case 3:
                            System.out.println("Bicicleta no tiene motor.");
                            break;
                        default:
                            System.out.println("Vehículo no registrado.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- Ejercicio 5: Vocal o consonante ---");
                    System.out.print("Ingrese una letra: ");
                    String letra = scanner.nextLine().toLowerCase();
                    if (letra.length() > 0) {
                        switch (letra.charAt(0)) {
                            case 'a':
                            case 'e':
                            case 'i':
                            case 'o':
                            case 'u':
                                System.out.println("Es una vocal.");
                                break;
                            default:
                                if (Character.isLetter(letra.charAt(0))) {
                                    System.out.println("Es una consonante.");
                                } else {
                                    System.out.println("No es una letra.");
                                }
                        }
                    }
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida.");
            }
        }
    }

    private static void menuWhile(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Submenú de estructura de control while ---");
            System.out.println("1. Contador de 1 a N");
            System.out.println("2. Tabla de multiplicar");
            System.out.println("3. Contador regresivo");
            System.out.println("4. Regresar al menú principal");
            System.out.print("Seleccione un ejercicio: ");

            String entrada = scanner.nextLine();
            int opcion = 0;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (Exception e) {
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejercicio 1: Contador de 1 a N ---");
                    System.out.print("Ingrese un número N: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    int i = 1;
                    while (i <= n) {
                        System.out.print(i + " ");
                        i++;
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("\n--- Ejercicio 2: Tabla de multiplicar ---");
                    System.out.print("Ingrese la tabla que desea ver: ");
                    int tabla = Integer.parseInt(scanner.nextLine());
                    int j = 1;
                    while (j <= 10) {
                        System.out.println(tabla + " x " + j + " = " + (tabla * j));
                        j++;
                    }
                    break;
                case 3:
                    System.out.println("\n--- Ejercicio 3: Contador regresivo ---");
                    System.out.print("Desde qué número contar: ");
                    int contReg = Integer.parseInt(scanner.nextLine());
                    while (contReg >= 0) {
                        System.out.print(contReg + " ");
                        contReg--;
                    }
                    System.out.println();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida.");
            }
        }
    }

    private static void menuDoWhile(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Submenú de estructura de control do-while ---");
            System.out.println("1. Pedir números hasta que ingrese 0");
            System.out.println("2. Validar contraseña secreta");
            System.out.println("3. Repetir mensaje al menos una vez");
            System.out.println("4. Leer un número entre 1 y 5");
            System.out.println("5. Regresar al menú principal");
            System.out.print("Seleccione un ejercicio: ");

            String entrada = scanner.nextLine();
            int opcion = 0;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (Exception e) {
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejercicio 1: Números hasta 0 ---");
                    int val;
                    do {
                        System.out.print("Ingrese número (0 para salir): ");
                        val = Integer.parseInt(scanner.nextLine());
                    } while (val != 0);
                    System.out.println("Terminó.");
                    break;
                case 2:
                    System.out.println("\n--- Ejercicio 2: Validar contraseña secreta ---");
                    String pass;
                    do {
                        System.out.print("Adivine la contraseña secreta (tip: admin): ");
                        pass = scanner.nextLine();
                    } while (!pass.equals("admin"));
                    System.out.println("¡Correcto!");
                    break;
                case 3:
                    System.out.println("\n--- Ejercicio 3: Repetir al menos una vez ---");
                    String resp;
                    do {
                        System.out.println("Esto se ejecuta al menos una vez.");
                        System.out.print("¿Desea repetirlo? (s/n): ");
                        resp = scanner.nextLine();
                    } while (resp.equalsIgnoreCase("s"));
                    break;
                case 4:
                    System.out.println("\n--- Ejercicio 4: Número entre 1 y 5 ---");
                    int validNum;
                    do {
                        System.out.print("Por favor, ingrese un número del 1 al 5: ");
                        validNum = Integer.parseInt(scanner.nextLine());
                        if (validNum < 1 || validNum > 5) {
                            System.out.println("Error, número fuera de rango.");
                        }
                    } while (validNum < 1 || validNum > 5);
                    System.out.println("Gracias, ingresó el " + validNum);
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida.");
            }
        }
    }

    private static void menuFor(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Submenú de estructura de control for ---");
            System.out.println("1. Imprimir números del 1 al 10");
            System.out.println("2. Números pares del 1 al 20");
            System.out.println("3. Suma de los primeros N números");
            System.out.println("4. Invertir palabra");
            System.out.println("5. Regresar al menú principal");
            System.out.print("Seleccione un ejercicio: ");

            String entrada = scanner.nextLine();
            int opcion = 0;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (Exception e) {
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Ejercicio 1: Números del 1 al 10 ---");
                    for (int i = 1; i <= 10; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("\n--- Ejercicio 2: Pares del 1 al 20 ---");
                    for (int i = 2; i <= 20; i += 2) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("\n--- Ejercicio 3: Suma primeros N números ---");
                    System.out.print("Ingrese la cantidad de números a sumar: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    int suma = 0;
                    for (int i = 1; i <= n; i++) {
                        suma += i;
                    }
                    System.out.println("La suma de los primeros " + n + " números es: " + suma);
                    break;
                case 4:
                    System.out.println("\n--- Ejercicio 4: Invertir palabra ---");
                    System.out.print("Ingrese una palabra: ");
                    String palabra = scanner.nextLine();
                    System.out.print("Palabra invertida: ");
                    for (int i = palabra.length() - 1; i >= 0; i--) {
                        System.out.print(palabra.charAt(i));
                    }
                    System.out.println();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida.");
            }
        }
    }
    
}
