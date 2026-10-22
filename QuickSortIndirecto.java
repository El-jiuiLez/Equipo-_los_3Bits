package abstraccion_y_uso_datos;

import java.util.Arrays;
import java.util.Scanner;


public class QuickSortIndirecto {


    static int[] crearIndices(int n) {
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        return idx;
    }

    static void intercambiar(int[] idx, int i, int j) {
        int temp = idx[i];
        idx[i] = idx[j];
        idx[j] = temp;
    }

    static void quickSortIndirectoEnteros(int[] datos, int[] idx, int bajo, int alto) {
        if (bajo < alto) {
            int p = particionIndirectaEnteros(datos, idx, bajo, alto);
            quickSortIndirectoEnteros(datos, idx, bajo, p - 1);
            quickSortIndirectoEnteros(datos, idx, p + 1, alto);
        }
    }

    static int particionIndirectaEnteros(int[] datos, int[] idx, int bajo, int alto) {
        int pivote = datos[idx[alto]]; 
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            
            if (datos[idx[j]] <= pivote) {
                i++;
                intercambiar(idx, i, j);
            }
        }
        intercambiar(idx, i + 1, alto);
        return i + 1;
    }

    static void mostrarEnteros(int[] datos, int[] idx) {
        System.out.print("[");
        for (int i = 0; i < idx.length; i++) {
            System.out.print(datos[idx[i]]);
            if (i < idx.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }


    static void quickSortIndirectoCaracteres(char[] datos, int[] idx, int bajo, int alto) {
        if (bajo < alto) {
            int p = particionIndirectaCaracteres(datos, idx, bajo, alto);
            quickSortIndirectoCaracteres(datos, idx, bajo, p - 1);
            quickSortIndirectoCaracteres(datos, idx, p + 1, alto);
        }
    }

    static int particionIndirectaCaracteres(char[] datos, int[] idx, int bajo, int alto) {
        char pivote = datos[idx[alto]];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (datos[idx[j]] <= pivote) {
                i++;
                intercambiar(idx, i, j);
            }
        }
        intercambiar(idx, i + 1, alto);
        return i + 1;
    }

    static void mostrarCaracteres(char[] datos, int[] idx) {
        System.out.print("[");
        for (int i = 0; i < idx.length; i++) {
            System.out.print(datos[idx[i]]);
            if (i < idx.length - 1) System.out.print(", ");
        }
        System.out.println("]");
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

  
    static class QuickSortIndirectoPOO<T extends Comparable<T>> {
        private T[] datos;
        private int[] indices;

        public QuickSortIndirectoPOO(T[] datos) {
            this.datos = datos;
            this.indices = new int[datos.length];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }
        }

        public void ordenar() {
            ordenar(0, indices.length - 1);
        }

        private void ordenar(int bajo, int alto) {
            if (bajo < alto) {
                int p = particion(bajo, alto);
                ordenar(bajo, p - 1);
                ordenar(p + 1, alto);
            }
        }

        private int particion(int bajo, int alto) {
            T pivote = datos[indices[alto]];
            int i = bajo - 1;

            for (int j = bajo; j < alto; j++) {
                if (datos[indices[j]].compareTo(pivote) <= 0) {
                    i++;
                    intercambiar(i, j);
                }
            }
            intercambiar(i + 1, alto);
            return i + 1;
        }

        private void intercambiar(int i, int j) {
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        public void mostrarOriginal() {
            System.out.println(Arrays.toString(datos));
        }

        public void mostrarIndices() {
            System.out.println(Arrays.toString(indices));
        }

        public void mostrarOrdenado() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < indices.length; i++) {
                sb.append(datos[indices[i]]);
                if (i < indices.length - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb);
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
                System.out.println("  Escritura incorrecta, escribe un numero entero.");
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
                System.out.println("  dato invalido, escribe un numero, ejemplo. 8.5.");
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
            System.out.println("  No puedes dejar esto vacio");
        }
    }

    static int leerCantidad() {
        return leerEntero("¿Cuantos elementos quieres ordenar? (1-50): ", 1, 50);
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
            double prom = leerDecimal("    Promedio (0 a 10): ", 0, 10);
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
            System.out.println("\n---- ¿Como quieres tu Quick sort indirecto? ----");
            System.out.println("1 En version estructurada");
            System.out.println("2 En version Orientada a Objetos");
            System.out.println("3 Salir");
            opcion = leerEntero("Elige: ", 1, 3);
            switch (opcion) {
                case 1: menuEstructurado(); break;
                case 2: menuPOO(); break;
                default: System.out.println("Bye bye ^u^");
            }
        } while (opcion != 3);
    }

    static void menuEstructurado() {
        int tipo;
        do {
            System.out.println("\n--- Versión estructurada ---");
            System.out.println("1. Enteros");
            System.out.println("2. Caracteres");
            System.out.println("3. Regresar");
            tipo = leerEntero("Elige el tipo de dato: ", 1, 3);
            System.out.println();
            if (tipo == 1) {
                int[] datos = leerEnteros();
                int[] idx = crearIndices(datos.length);
                quickSortIndirectoEnteros(datos, idx, 0, datos.length - 1);
                System.out.println("Enteros originales: " + Arrays.toString(datos));
                System.out.println("Índices:            " + Arrays.toString(idx));
                System.out.print("Enteros ordenados:  ");
                mostrarEnteros(datos, idx);
            } else if (tipo == 2) {
                char[] datos = leerCaracteres();
                int[] idx = crearIndices(datos.length);
                quickSortIndirectoCaracteres(datos, idx, 0, datos.length - 1);
                System.out.println("Chars originales: " + Arrays.toString(datos));
                System.out.println("Índices:            " + Arrays.toString(idx));
                System.out.print("Chars ordenados:  ");
                mostrarCaracteres(datos, idx);
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
                QuickSortIndirectoPOO<Integer> o = new QuickSortIndirectoPOO<>(d);
                o.ordenar();
                System.out.print("Enteros originales: ");
                o.mostrarOriginal();
                System.out.print("Índices:            ");
                o.mostrarIndices();
                System.out.print("Enteros ordenados:  ");
                o.mostrarOrdenado();
            } else if (tipo == 2) {
                Character[] d = boxear(leerCaracteres());
                QuickSortIndirectoPOO<Character> o = new QuickSortIndirectoPOO<>(d);
                o.ordenar();
                System.out.print("Caracteres originales: ");
                o.mostrarOriginal();
                System.out.print("Índices:            ");
                o.mostrarIndices();
                System.out.print("Caracteress ordenados:  ");
                o.mostrarOrdenado();
            } else if (tipo == 3) {
                Alumno[] d = leerAlumnos();
                QuickSortIndirectoPOO<Alumno> o = new QuickSortIndirectoPOO<>(d);
                o.ordenar();
                System.out.print("Alumnos originales: ");
                o.mostrarOriginal();
                System.out.print("Indices:            ");
                o.mostrarIndices();
                System.out.print("Alumnos ordenados:  ");
                o.mostrarOrdenado();
            }
        } while (tipo != 4);
    }
}