package abstraccion_y_uso_datos;
import java.util.Arrays;
import java.util.Scanner;


public class MergeSortIndirecto {


    static int[] crearIndices(int n) {
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        return idx;
    }


    static void mergeSortIndirectoEnteros(int[] datos, int[] idx, int izq, int der) {
        if (izq >= der) return; 
        int medio = (izq + der) / 2;
        mergeSortIndirectoEnteros(datos, idx, izq, medio);
        mergeSortIndirectoEnteros(datos, idx, medio + 1, der);
        mezclarIndirectoEnteros(datos, idx, izq, medio, der);
    }

    static void mezclarIndirectoEnteros(int[] datos, int[] idx, int izq, int medio, int der) {
        
        int[] izqIdx = Arrays.copyOfRange(idx, izq, medio + 1);
        int[] derIdx = Arrays.copyOfRange(idx, medio + 1, der + 1);
        int i = 0, j = 0, k = izq;

        while (i < izqIdx.length && j < derIdx.length) {
            // se comparan los datos, pero se acomodan los índices
            if (datos[izqIdx[i]] <= datos[derIdx[j]]) idx[k++] = izqIdx[i++];
            else                                      idx[k++] = derIdx[j++];
        }
        while (i < izqIdx.length) idx[k++] = izqIdx[i++];
        while (j < derIdx.length) idx[k++] = derIdx[j++];
    }

    static void mostrarEnteros(int[] datos, int[] idx) {
        System.out.print("[");
        for (int i = 0; i < idx.length; i++) {
            System.out.print(datos[idx[i]]);
            if (i < idx.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }


    static void mergeSortIndirectoCaracteres(char[] datos, int[] idx, int izq, int der) {
        if (izq >= der) return;
        int medio = (izq + der) / 2;
        mergeSortIndirectoCaracteres(datos, idx, izq, medio);
        mergeSortIndirectoCaracteres(datos, idx, medio + 1, der);
        mezclarIndirectoCaracteres(datos, idx, izq, medio, der);
    }

    static void mezclarIndirectoCaracteres(char[] datos, int[] idx, int izq, int medio, int der) {
        int[] izqIdx = Arrays.copyOfRange(idx, izq, medio + 1);
        int[] derIdx = Arrays.copyOfRange(idx, medio + 1, der + 1);
        int i = 0, j = 0, k = izq;

        while (i < izqIdx.length && j < derIdx.length) {
            if (datos[izqIdx[i]] <= datos[derIdx[j]]) idx[k++] = izqIdx[i++];
            else                                      idx[k++] = derIdx[j++];
        }
        while (i < izqIdx.length) idx[k++] = izqIdx[i++];
        while (j < derIdx.length) idx[k++] = derIdx[j++];
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

    static class MergeSortIndirectoPOO<T extends Comparable<T>> {
        private T[] datos;
        private int[] indices;

        public MergeSortIndirectoPOO(T[] datos) {
            this.datos = datos;
            this.indices = new int[datos.length];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }
        }

        public void ordenar() {
            ordenar(0, indices.length - 1);
        }

        private void ordenar(int izq, int der) {
            if (izq >= der) return;
            int medio = (izq + der) / 2;
            ordenar(izq, medio);
            ordenar(medio + 1, der);
            mezclar(izq, medio, der);
        }

        private void mezclar(int izq, int medio, int der) {
            int[] izqIdx = Arrays.copyOfRange(indices, izq, medio + 1);
            int[] derIdx = Arrays.copyOfRange(indices, medio + 1, der + 1);
            int i = 0, j = 0, k = izq;

            while (i < izqIdx.length && j < derIdx.length) {
                if (datos[izqIdx[i]].compareTo(datos[derIdx[j]]) <= 0) indices[k++] = izqIdx[i++];
                else                                                   indices[k++] = derIdx[j++];
            }
            while (i < izqIdx.length) indices[k++] = izqIdx[i++];
            while (j < derIdx.length) indices[k++] = derIdx[j++];
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
            System.out.println("\n---- Elige una opcion para tu mergesort indirecto ----");
            System.out.println("1. Estructurada");
            System.out.println("2. Orientada a Objetos");
            System.out.println("3. Salir");
            opcion = leerEntero("Elige una opción: ", 1, 3);
            switch (opcion) {
                case 1: menuEstructurado(); break;
                case 2: menuPOO(); break;
                default: System.out.println("Bye bye :3");
            }
        } while (opcion != 3);
    }

    static void menuEstructurado() {
        int tipo;
        do {
            System.out.println("\n--- Estructurada ---");
            System.out.println("1. Enteros");
            System.out.println("2. Caracteres");
            System.out.println("3. Regresar");
            tipo = leerEntero("Elige tu opcion: ", 1, 3);
            System.out.println();
            if (tipo == 1) {
                int[] datos = leerEnteros();
                int[] idx = crearIndices(datos.length);
                mergeSortIndirectoEnteros(datos, idx, 0, datos.length - 1);
                System.out.println("Enteros originales: " + Arrays.toString(datos));
                System.out.println("Indices:            " + Arrays.toString(idx));
                System.out.print("Enteros ordenados:  ");
                mostrarEnteros(datos, idx);
            } else if (tipo == 2) {
                char[] datos = leerCaracteres();
                int[] idx = crearIndices(datos.length);
                mergeSortIndirectoCaracteres(datos, idx, 0, datos.length - 1);
                System.out.println("Caracteres originales: " + Arrays.toString(datos));
                System.out.println("Indices:            " + Arrays.toString(idx));
                System.out.print("Caracteres ordenados:  ");
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
                MergeSortIndirectoPOO<Integer> o = new MergeSortIndirectoPOO<>(d);
                o.ordenar();
                System.out.print("Enteros originales: ");
                o.mostrarOriginal();
                System.out.print("Índices:            ");
                o.mostrarIndices();
                System.out.print("Enteros ordenados:  ");
                o.mostrarOrdenado();
            } else if (tipo == 2) {
                Character[] d = boxear(leerCaracteres());
                MergeSortIndirectoPOO<Character> o = new MergeSortIndirectoPOO<>(d);
                o.ordenar();
                System.out.print("Caracteress originales: ");
                o.mostrarOriginal();
                System.out.print("Indices:            ");
                o.mostrarIndices();
                System.out.print("Caracteres ordenados:  ");
                o.mostrarOrdenado();
            } else if (tipo == 3) {
                Alumno[] d = leerAlumnos();
                MergeSortIndirectoPOO<Alumno> o = new MergeSortIndirectoPOO<>(d);
                o.ordenar();
                System.out.print("Alumnos originales: ");
                o.mostrarOriginal();
                System.out.print("Índices:            ");
                o.mostrarIndices();
                System.out.print("Alumnos ordenados:  ");
                o.mostrarOrdenado();
            }
        } while (tipo != 4);
    }
}