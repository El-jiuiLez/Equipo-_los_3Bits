package puntero;

import java.util.Scanner;

class Auto {
    private double precio;
    private int anio;
    private Auto sig;               

    public Auto(double precio, int anio) {
        this.precio = precio;
        this.anio = anio;
    }
    public double getPrecio() { return precio; }
    public int getAnio()      { return anio; }
    public Auto getSig()      { return sig; }
    public void setSig(Auto sig) { this.sig = sig; }
}

class Persona {
    private String nombre, ap, am;
    private char genero;
    private int edad;
    private Persona sig;            // referencia al mismo tipo

    public Persona(String nombre, String ap, String am, char genero, int edad) {
        this.nombre = nombre; this.ap = ap; this.am = am;
        this.genero = genero; this.edad = edad;
    }
    public Persona getSig() { return sig; }
    public void setSig(Persona sig) { this.sig = sig; }

    @Override
    public String toString() {
        return nombre + " " + ap + " " + am + " | Genero: " + genero + " | Edad: " + edad;
    }
}

class ListaAutos {
    private Auto inicio;

    public void agregar(double precio, int anio) {
        Auto nuevo = new Auto(precio, anio);
        if (inicio == null) { inicio = nuevo; return; }
        Auto aux = inicio;
        while (aux.getSig() != null) aux = aux.getSig();
        aux.setSig(nuevo);
    }

    public void mostrar() {
        int i = 1;
        for (Auto p = inicio; p != null; p = p.getSig())
            System.out.println("Auto " + i++ + " -> Precio: " + p.getPrecio() + " | Anio: " + p.getAnio());
    }
}

class ListaPersonas {
    private Persona inicio;

    public void agregar(Persona nueva) {
        if (inicio == null) { inicio = nueva; return; }
        Persona aux = inicio;
        while (aux.getSig() != null) aux = aux.getSig();
        aux.setSig(nueva);
    }

    public void mostrar() {
        int i = 1;
        for (Persona p = inicio; p != null; p = p.getSig())
            System.out.println("Persona " + i++ + " -> " + p);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaAutos autos = new ListaAutos();
        ListaPersonas personas = new ListaPersonas();
        int op;

        do {
            System.out.print("\n1. Agregar auto\n2. Mostrar autos\n3. Agregar persona\n4. Mostrar personas\n0. Salir\nOpcion: ");
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(sc.nextLine());
                    System.out.print("Anio: ");
                    int anio = Integer.parseInt(sc.nextLine());
                    autos.agregar(precio, anio);
                    break;
                case 2: autos.mostrar(); break;
                case 3:
                    System.out.print("Nombre: ");           String n  = sc.nextLine();
                    System.out.print("Apellido paterno: "); String ap = sc.nextLine();
                    System.out.print("Apellido materno: "); String am = sc.nextLine();
                    System.out.print("Genero (M/F): ");     char g = sc.nextLine().charAt(0);
                    System.out.print("Edad: ");             int e = Integer.parseInt(sc.nextLine());
                    personas.agregar(new Persona(n, ap, am, g, e));
                    break;
                case 4: personas.mostrar(); break;
            }
        } while (op != 0);
        sc.close();
    }
}