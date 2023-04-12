import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;
public class Main {
    public static void main(String[] args) {
        System.out.println("Java Fundamentals");
        System.out.println("Tarea 1: Buscaminas");
        System.out.println("Alumno: Crisstian Mella");
        System.out.println("GitHub: https://github.com/crisstianmella");

        Scanner level = new Scanner(System.in);

        System.out.println("Selecciona el nivel de dificultad:");
        System.out.println("1 - Fácil");
        System.out.println("2 - Medio");
        System.out.println("3 - Difícil");

        int nivel = level.nextInt();

        int filas=5;
        int columnas=5;
        int minas;
        int coordenadasCorrectas = 0;

        if (nivel == 1) {
            minas = 10;
        } else if (nivel == 2) {
            minas = 13;
        } else {
            minas = 15;
        }

        // crear el tablero
        int[][] tablero = new int[filas][columnas];

        // inicializar el tablero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = 0;
            }
        }

        // agregar las minas aleatorias
        int minasRestantes = minas;
        Random rand = new Random();

        while (minasRestantes > 0) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            if (tablero[fila][columna] == 0) {
                tablero[fila][columna] = 1;
                minasRestantes--;
            }
        }

        // Descomentar estas lines en caso de querer ver el tablero inicial
        //System.out.println("Tablero inicial:");
        //imprimirTablero(tablero);

        // leer las coordenadas del usuario y verificar si hay minas
        Scanner scanner = new Scanner(System.in);
        HashSet<String> coordenadasIngresadas = new HashSet<>();

        while (true) {
            System.out.print("Ingresa las coordenadas (fila,columna): ");
            String entrada = scanner.nextLine();
            String[] coordenadas = entrada.split(",");

            int fila = Integer.parseInt(coordenadas[0]);
            int columna = Integer.parseInt(coordenadas[1]);

            // verificar si las coordenadas están fuera del rango del tablero
            if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
                System.out.println("Coordenadas fuera de rango del tablero, ingrese coordenada nuevamente.");
                continue;
            }

            // verificar si las coordenadas ya han sido ingresadas
            if (coordenadasIngresadas.contains(entrada)) {
                System.out.println("Estas coordenadas ya han sido ingresadas, ingrese coordenadas nuevamente.");
                continue;
            }

            coordenadasIngresadas.add(entrada);

            if (tablero[fila][columna] == 1) {
                System.out.println("BOOM! Has encontrado una mina.");
                System.out.println("Perdiste!!");
                System.out.println("Tablero inicial:");
                imprimirTablero(tablero);
                break;
            } else {
                System.out.println("No hay mina en estas coordenada, ingresa otra coordenada ");
                coordenadasCorrectas++;

                if (coordenadasCorrectas == 6) {
                    System.out.println("Felicidades! Has ganado el juego.");
                    System.out.println("Tablero inicial:");
                    imprimirTablero(tablero);
                    break;
                }
            }
        }
    }

    // método para imprimir el tablero
    public static void imprimirTablero(int[][] tablero) {
        for (int[] ints : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}