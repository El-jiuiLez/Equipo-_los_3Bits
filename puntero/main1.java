import java.util.InputMismatchException;
import java.util.Scanner;

public class Main1 {
   
     private static int leerEntero(Scanner sc, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Ingresa un número entre " + min + " y " + max + ".");
            } catch (InputMismatchException e) {
                sc.nextLine(); 
                System.out.println("Entrada inválida. Escribe un número entero.");
            }
        }
    }

  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        implementacion1 lista = new implementacion1();
 
        int n = leerEntero(sc, "¿Cuántas personas deseas registrar? ", 1, 1000);
 
        for (int i = 0; i < n; i++) {
            System.out.println("\nPersona " + (i + 1));
            String nombre;
            do {
                System.out.print("Nombre: ");
                nombre = sc.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("El nombre no puede estar vacío.");
                }
            } while (nombre.isEmpty());
 
            int edad = leerEntero(sc, "Edad: ", 0, 120);
            lista.agregarPersona(nombre, edad);
        }
 
        lista.mostrarLista();
 
        int opcion = leerEntero(sc, "\n¿Deseas ordenar la lista por edad? (1: Sí / 0: No) ", 0, 1);
        if (opcion == 1) {
            lista.ordenarPorEdadBurbuja();
            System.out.println("Lista ordenada por edad.");
            lista.mostrarLista();
        }
 
        System.out.println("\nFin");
        sc.close();
    }
}
 