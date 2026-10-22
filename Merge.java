package abstraccion_y_uso_datos;

import java.util.Arrays;
import java.util.Scanner;

public class Merge {


    static void mergeSortEnteros(int[] a, int izq, int der) {
        if (izq >= der) return; // caso base: 0 o 1 elemento
        int medio = (izq + der) / 2;
        mergeSortEnteros(a, izq, medio);
        mergeSortEnteros(a, medio + 1, der);
        mezclarEnteros(a, izq, medio, der);
    }

    static void mezclarEnteros(int[] a, int izq, int medio, int der) {
        int[] izqArr = Arrays.copyOfRange(a, izq, medio + 1);
        int[] derArr = Arrays.copyOfRange(a, medio + 1, der + 1);
        int i = 0, j = 0, k = izq;

        while (i < izqArr.length && j < derArr.length) {
            if (izqArr[i] <= derArr[j]) a[k++] = izqArr[i++];
            else                        a[k++] = derArr[j++];
        }
        while (i < izqArr.length) a[k++] = izqArr[i++];
        while (j < derArr.length) a[k++] = derArr[j++];
    }

    // ---------- Caracteres ----------
    static void mergeSortCaracteres(char[] a, int izq, int der) {
        if (izq >= der) return;
        int medio = (izq + der) / 2;
        mergeSortCaracteres(a, izq, medio);
        mergeSortCaracteres(a, medio + 1, der);
        mezclarCaracteres(a, izq, medio, der);
    }

    static void mezclarCaracteres(char[] a, int izq, int medio, int der) {
        char[] izqArr = Arrays.copyOfRange(a, izq, medio + 1);
        char[] derArr = Arrays.copyOfRange(a, medio + 1, der + 1);
        int i = 0, j = 0, k = izq;

        while (i < izqArr.length && j < derArr.length) {
            if (izqArr[i] <= derArr[j]) a[k++] = izqArr[i++];
            else                        a[k++] = derArr[j++];
        }
        while (i < izqArr.length) a[k++] = izqArr[i++];
        while (j < derArr.length) a[k++] = derArr[j++];
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

    static class MergeSort<T extends Comparable<T>> {
        private T[] datos;

        public MergeSort(T[] datos) {
            this.datos = datos;
        }

        public void ordenar() {
            ordenar(0, datos.length - 1);
        }

        private void ordenar(int izq, int der) {
            if (izq >= der) return;
            int medio = (izq + der) / 2;
            ordenar(izq, medio);
            ordenar(medio + 1, der);
            mezclar(izq, medio, der);
        }

        private void mezclar(int izq, int medio, int der) {
            T[] izqArr = Arrays.copyOfRange(datos, izq, medio + 1);
            T[] derArr = Arrays.copyOfRange(datos, medio + 1, der + 1);
            int i = 0, j = 0, k = izq;

            while (i < izqArr.length && j < derArr.length) {
                if (izqArr[i].compareTo(derArr[j]) <= 0) datos[k++] = izqArr[i++];
                else                                     datos[k++] = derArr[j++];
            }
            while (i < izqArr.length) datos[k++] = izqArr[i++];
            while (j < derArr.length) datos[k++] = derArr[j++];
        }

        public void mostrar() {
            System.out.println(Arrays.toString(datos));
        }
    }

    // =====================================================================
    //                        ENTRADA INTERACTIVA
    // =====================================================================
    static final Scanner sc = new Scanner(System.in);

    static int leerEntero(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.println("  Valor fuera de rango (" + min + " a " + max + ").");
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida. Escribe un entero.");
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
                System.out.println("  Entrada incorrecta, Escribe un numero (ej. 8.5).");
            }
        }
    }

    static char leerCaracter(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (s.length() == 1) return s.charAt(0);
            System.out.println("  Escribe exactamente un caracter.");
        }
    }

    static String leerTexto(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("  El texto no puede estar vacio, ingresa nuevamente un dato");
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
            System.out.println("\n------ Mergesort ------");
            System.out.println("1. Estructurado");
            System.out.println("2. Orientado a objetos");
            System.out.println("3. Salir");
            opcion = leerEntero("Escoje una opcion: ", 1, 3);
            switch (opcion) {
                case 1: menuEstructurado(); break;
                case 2: menuPOO(); break;
                default: System.out.println("Bye bye usuario c:");
            }
        } while (opcion != 3);
    }

    static void menuEstructurado() {
        int tipo;
        do {
            System.out.println("\n--- Estructurada ---");
            System.out.println("1. Enteros");
            System.out.println("2. Caracteres");
            System.out.println("3. Volver");
            tipo = leerEntero("Elige tipo de dato: ", 1, 3);
            System.out.println();
            if (tipo == 1) {
                int[] a = leerEnteros();
                System.out.println("Enteros antes:   " + Arrays.toString(a));
                mergeSortEnteros(a, 0, a.length - 1);
                System.out.println("Enteros después: " + Arrays.toString(a));
            } else if (tipo == 2) {
                char[] a = leerCaracteres();
                System.out.println("Caracteres antes:     " + Arrays.toString(a));
                mergeSortCaracteres(a, 0, a.length - 1);
                System.out.println("Caracteres después:   " + Arrays.toString(a));
            }
        } while (tipo != 3);
    }

    static void menuPOO() {
        int tipo;
        do {
            System.out.println("\n--- Orientado a objetos ---");
            System.out.println("1. Enteros");
            System.out.println("2. Caracteres");
            System.out.println("3. Alumnos (se ordenan por promedio)");
            System.out.println("4. Volver");
            tipo = leerEntero("Elige el tipo de dato: ", 1, 4);
            System.out.println();
            if (tipo == 1) {
                Integer[] d = boxear(leerEnteros());
                System.out.println("Enteros antes:   " + Arrays.toString(d));
                Merge<Integer> o = new Merge<>(d);
                o.ordenar();
                System.out.print("Enteros después: ");
                o.mostrar();
            } else if (tipo == 2) {
                Character[] d = boxear(leerCaracteres());
                System.out.println("Caaracteres antes:   " + Arrays.toString(d));
                Merge<Character> o = new Merge<>(d);
                o.ordenar();
                System.out.print("Caracteres después: ");
                o.mostrar();
            } else if (tipo == 3) {
                Alumno[] d = leerAlumnos();
                System.out.println("Alumnos antes:   " + Arrays.toString(d));
                Merge<Alumno> o = new Merge<>(d);
                o.ordenar();
                System.out.print("Alumnos después: ");
                o.mostrar();
            }
        } while (tipo != 4);
    }
}