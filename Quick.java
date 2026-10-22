package abstraccion_y_uso_datos;

import java.util.Arrays;
import java.util.Scanner;

public class Quick {


    static void quickSortEnteros(int[] a, int bajo, int alto) {
        if (bajo < alto) {
            int p = particionEnteros(a, bajo, alto);
            quickSortEnteros(a, bajo, p - 1);
            quickSortEnteros(a, p + 1, alto);
        }
    }

    static int particionEnteros(int[] a, int bajo, int alto) {
        int pivote = a[alto]; 
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (a[j] <= pivote) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    
        int temp = a[i + 1];
        a[i + 1] = a[alto];
        a[alto] = temp;
        return i + 1;
    }

  
    static void quickSortCaracteres(char[] a, int bajo, int alto) {
        if (bajo < alto) {
            int p = particionCaracteres(a, bajo, alto);
            quickSortCaracteres(a, bajo, p - 1);
            quickSortCaracteres(a, p + 1, alto);
        }
    }

    static int particionCaracteres(char[] a, int bajo, int alto) {
        char pivote = a[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (a[j] <= pivote) {
                i++;
                char temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        char temp = a[i + 1];
        a[i + 1] = a[alto];
        a[alto] = temp;
        return i + 1;
    }

    static class Alumno implements Comparable<Alumno> {
        private String nombre;
        private double promedio;

        public Alumno(String nombre, double promedio) {
            this.nombre = nombre;
            this.promedio = promedio;
        }

        public String getNombre() { return nombre; }
        public double getPromedio() { return promedio; }

        @Override
        public int compareTo(Alumno otro) {
            return Double.compare(this.promedio, otro.promedio);
        }

        @Override
        public String toString() {
            return nombre + " (" + promedio + ")";
        }
    }

    static class QuickSort<T extends Comparable<T>> {
        private T[] datos;

        public QuickSort(T[] datos) {
            this.datos = datos;
        }

        public void ordenar() {
            ordenar(0, datos.length - 1);
        }

        private void ordenar(int bajo, int alto) {
            if (bajo < alto) {
                int p = particion(bajo, alto);
                ordenar(bajo, p - 1);
                ordenar(p + 1, alto);
            }
        }

        private int particion(int bajo, int alto) {
            T pivote = datos[alto];
            int i = bajo - 1;

            for (int j = bajo; j < alto; j++) {
                if (datos[j].compareTo(pivote) <= 0) {
                    i++;
                    intercambiar(i, j);
                }
            }
            intercambiar(i + 1, alto);
            return i + 1;
        }

        private void intercambiar(int i, int j) {
            T temp = datos[i];
            datos[i] = datos[j];
            datos[j] = temp;
        }

        public void mostrar() {
            System.out.println(Arrays.toString(datos));
        }
    }


    static final Scanner sc = new Scanner(System.in);

    static int leerEntero(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.println("  Valor fuera de rango (" + min + " a " + max + ").");
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida. Escribe un número entero.");
            }
        }
    }

    static double leerDecimal(String msg, double min, double max) {
        while (true) {
            System.out.print(msg);
            try {
                double v = Double.parseDouble(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.println("  Valor fuera de rango (" + min + " a " + max + ").");
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida. Escribe un número (ej. 8.5).");
            }
        }
    }

    static char leerCaracter(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (s.length() == 1) return s.charAt(0);
            System.out.println("  Escribe exactamente un carácter.");
        }
    }

    static String leerTexto(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("  El texto no puede estar vacío.");
        }
    }

    static int leerCantidad() {
        return leerEntero("¿Cuántos elementos quieres ordenar? (1-50): ", 1, 50);
    }

    static int[] leerEnteros() {
        int n = leerCantidad();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = leerEntero("  Entero #" + (i + 1) + ": ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return a;
    }

    static char[] leerCaracteres() {
        int n = leerCantidad();
        char[] a = new char[n];
        for (int i = 0; i < n; i++) {
            a[i] = leerCaracter("  Carácter #" + (i + 1) + ": ");
        }
        return a;
    }

    static Alumno[] leerAlumnos() {
        int n = leerCantidad();
        Alumno[] a = new Alumno[n];
        for (int i = 0; i < n; i++) {
            System.out.println("  Alumno #" + (i + 1));
            String nombre = leerTexto("    Nombre: ");
            double prom = leerDecimal("    Promedio (0-10): ", 0, 10);
            a[i] = new Alumno(nombre, prom);
        }
        return a;
    }

    static Integer[] boxear(int[] a) {
        Integer[] r = new Integer[a.length];
        for (int i = 0; i < a.length; i++) r[i] = a[i];
        return r;
    }

    static Character[] boxear(char[] a) {
        Character[] r = new Character[a.length];
        for (int i = 0; i < a.length; i++) r[i] = a[i];
        return r;
    }

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n----- Menu quiecksort -----");
            System.out.println("1. Estructurado");
            System.out.println("2. Orientado a objetos");
            System.out.println("3. Salir");
            opcion = leerEntero("Elige una opción: ", 1, 3);
            switch (opcion) {
                case 1: menuEstructurado(); break;
                case 2: menuPOO(); break;
                default: System.out.println("Bye bye c:");
            }
        } while (opcion != 3);
    }

    static void menuEstructurado() {
        int tipo;
        do {
            System.out.println("\n--- Estructurado ---");
            System.out.println("1. Enteros");
            System.out.println("2. Caracteres");
            System.out.println("3. Volver");
            tipo = leerEntero("Elige que quieres introducir: ", 1, 3);
            System.out.println();
            if (tipo == 1) {
                int[] a = leerEnteros();
                System.out.println("Enteros antes:   " + Arrays.toString(a));
                quickSortEnteros(a, 0, a.length - 1);
                System.out.println("Enteros después: " + Arrays.toString(a));
            } else if (tipo == 2) {
                char[] a = leerCaracteres();
                System.out.println("Caracteres antes:     " + Arrays.toString(a));
                quickSortCaracteres(a, 0, a.length - 1);
                System.out.println("Caracteres después:   " + Arrays.toString(a));
            }
        } while (tipo != 3);
    }

    static void menuPOO() {
        int tipo;
        do {
            System.out.println("\n--- Orientado a Objetos ---");
            System.out.println("1. Enteros");
            System.out.println("2. Caracteres");
            System.out.println("3. Alumnos (se ordenan por promedio)");
            System.out.println("4. Volver");
            tipo = leerEntero("Elige el tipo de dato que quieras introducir: ", 1, 4);
            System.out.println();
            if (tipo == 1) {
                Integer[] d = boxear(leerEnteros());
                System.out.println("Enteros antes:   " + Arrays.toString(d));
                QuickSort<Integer> o = new QuickSort<>(d);
                o.ordenar();
                System.out.print("Enteros después: ");
                o.mostrar();
            } else if (tipo == 2) {
                Character[] d = boxear(leerCaracteres());
                System.out.println("Caracteres antes:   " + Arrays.toString(d));
                QuickSort<Character> o = new QuickSort<>(d);
                o.ordenar();
                System.out.print("Caracteress después: ");
                o.mostrar();
            } else if (tipo == 3) {
                Alumno[] d = leerAlumnos();
                System.out.println("Alumnos antes:   " + Arrays.toString(d));
                QuickSort<Alumno> o = new QuickSort<>(d);
                o.ordenar();
                System.out.print("Alumnos después: ");
                o.mostrar();
            }
        } while (tipo != 4);
    }
}