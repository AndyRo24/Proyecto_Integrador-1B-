package com.mycompany.proyecto_integrador;
import java.util.Scanner;

public class Proyecto_Integrador {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        // Solicitar el primer número binario
        System.out.print("Introduce el primer número binario: ");
        String binario1 = leer.nextLine();

        // Solicitar el segundo número binario
        System.out.print("Introduce el segundo número binario: ");
        String binario2 = leer.nextLine();

        // Sumar binarios
        String suma = sumaBinaria(binario1, binario2);
        System.out.println("Resultado de la suma: " + suma);

        // Restar binarios (si el primer número es mayor o igual al segundo)
        if (esMayorOIgual(binario1, binario2)) {
            String resta = restaBinaria(binario1, binario2);
            System.out.println("Resultado de la resta: " + resta);
        } else {
            System.out.println("No se puede realizar la resta: el primer número debe ser mayor o igual que el segundo.");
        }

    }

    // Método para sumar dos números binarios
    public static String sumaBinaria(String bin1, String bin2) {
        // Asegurarse de que los números binarios tengan la misma longitud
        int maxLen = Math.max(bin1.length(), bin2.length());
        bin1 = agregarCeros(bin1, maxLen);
        bin2 = agregarCeros(bin2, maxLen);

        StringBuilder resultado = new StringBuilder();
        int acarreo = 0;

        // Realizar la suma bit por bit
        for (int i = maxLen - 1; i >= 0; i--) {
            int bit1 = bin1.charAt(i) - '0';  // Convertir '0' o '1' a entero
            int bit2 = bin2.charAt(i) - '0';

            int suma = bit1 + bit2 + acarreo;
            if (suma == 0) {
                resultado.insert(0, '0');
                acarreo = 0;
            } else if (suma == 1) {
                resultado.insert(0, '1');
                acarreo = 0;
            } else if (suma == 2) {
                resultado.insert(0, '0');
                acarreo = 1;
            } else {
                resultado.insert(0, '1');
                acarreo = 1;
            }
        }

        if (acarreo != 0) {
            resultado.insert(0, '1');
        }

        return resultado.toString();
    }

    // Método para restar dos números binarios (suponiendo que el primero es mayor o igual que el segundo)
    public static String restaBinaria(String bin1, String bin2) {
        // Asegurarse de que los números binarios tengan la misma longitud
        int maxLen = Math.max(bin1.length(), bin2.length());
        bin1 = agregarCeros(bin1, maxLen);
        bin2 = agregarCeros(bin2, maxLen);

        StringBuilder resultado = new StringBuilder();
        int prestado = 0;

        // Realizar la resta bit por bit
        for (int i = maxLen - 1; i >= 0; i--) {
            int bit1 = bin1.charAt(i) - '0';  // Convertir '0' o '1' a entero
            int bit2 = bin2.charAt(i) - '0';

            int resta = bit1 - bit2 - prestado;
            if (resta == 1) {
                resultado.insert(0, '1');
                prestado = 0;
            } else if (resta == 0) {
                resultado.insert(0, '0');
                prestado = 0;
            } else {  // resta == -1
                resultado.insert(0, '1');
                prestado = 1;
            }
        }

        // Eliminar los ceros a la izquierda
        while (resultado.length() > 1 && resultado.charAt(0) == '0') {
            resultado.deleteCharAt(0);
        }

        return resultado.toString();
    }

    // Método para asegurarse de que las cadenas binarias tengan la misma longitud
    public static String agregarCeros(String bin, int longitud) {
        StringBuilder sb = new StringBuilder(bin);
        while (sb.length() < longitud) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    // Método para verificar si el primer número binario es mayor o igual que el segundo
    public static boolean esMayorOIgual(String bin1, String bin2) {
        // Asegurarse de que las cadenas tengan la misma longitud
        int maxLen = Math.max(bin1.length(), bin2.length());
        bin1 = agregarCeros(bin1, maxLen);
        bin2 = agregarCeros(bin2, maxLen);

        return bin1.compareTo(bin2) >= 0;
    }
}