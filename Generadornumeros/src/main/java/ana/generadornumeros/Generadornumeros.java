/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ana.generadornumeros;

import java.io.*;
import java.util.*;

public class Generadornumeros {

    private static class Nodo {
        int valor;
        Nodo siguiente;

        public Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;

    public Generadornumeros() {
        cabeza = null;
    }

    public void insertar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
    }

    public void ordenar(boolean ascendente) {
        if (cabeza == null || cabeza.siguiente == null) {
            return;
        }

        Nodo cabezaOrdenada = null;
        Nodo actual = cabeza;
        while (actual != null) {
            Nodo siguiente = actual.siguiente;
            if (ascendente) {
                cabezaOrdenada = insertarOrdenadoAscendente(cabezaOrdenada, actual);
            } else {
                cabezaOrdenada = insertarOrdenadoDescendente(cabezaOrdenada, actual);
            }
            actual = siguiente;
        }
        cabeza = cabezaOrdenada;
    }

    private Nodo insertarOrdenadoAscendente(Nodo cabeza, Nodo nuevoNodo) {
        if (cabeza == null || cabeza.valor >= nuevoNodo.valor) {
            nuevoNodo.siguiente = cabeza;
            return nuevoNodo;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.valor < nuevoNodo.valor) {
            actual = actual.siguiente;
        }
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
        return cabeza;
    }

    private Nodo insertarOrdenadoDescendente(Nodo cabeza, Nodo nuevoNodo) {
        if (cabeza == null || cabeza.valor <= nuevoNodo.valor) {
            nuevoNodo.siguiente = cabeza;
            return nuevoNodo;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.valor > nuevoNodo.valor) {
            actual = actual.siguiente;
        }
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
        return cabeza;
    }

    public void escribirArchivo(String rutaArchivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
            Nodo actual = cabeza;
            while (actual != null) {
                escritor.write(Integer.toString(actual.valor));
                escritor.newLine();
                actual = actual.siguiente;
            }
            System.out.println("Se han almacenado correctamente los números en el archivo " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        final int cantidadNumeros = 1000000;
        final String rutaArchivo = "C:\\Users\\User\\Documents\\MARIANO\\saber que semestre\\Progra 3\\Generador de numeros\\GeneradorOrdenado.txt";

        // Generar números aleatorios y almacenarlos en una lista enlazada
        Generadornumeros generador = new Generadornumeros();
        generarNumerosAleatorios(generador, cantidadNumeros);

        // Ordenar los números en la lista enlazada de manera ascendente
        generador.ordenar(true);

        // Escribir los números ordenados en el archivo de salida
        generador.escribirArchivo(rutaArchivo);
    }

    private static void generarNumerosAleatorios(Generadornumeros generador, int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad; i++) {
            int numeroAleatorio = random.nextInt(20000001) - 10000000; // Rango de -10,000,000 a 10,000,000
            generador.insertar(numeroAleatorio);
        }
        System.out.println("Se han generado y almacenado correctamente los números aleatorios en la lista enlazada");
    }
}