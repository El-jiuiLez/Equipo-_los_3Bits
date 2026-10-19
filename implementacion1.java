public class implementacion1 extends abs1 {
 
    @Override
    public void agregarPersona(String nombre, int edad) {
        persona1 nueva = new persona1(nombre, edad);
        if (estaVacia()) {
            cabeza = nueva;
        } else {
            ultimo.setSiguiente(nueva);
        }
        ultimo = nueva;
        contador++;
    }
 
    @Override
    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println("\nLista de personas:");
        persona1 actual = cabeza;
        while (actual != null) {
            System.out.println("Nombre: " + actual.getNombre()
                    + " | Edad: " + actual.getEdad() + " años");
            actual = actual.getSiguiente();
        }
    }
 
    @Override
    public void ordenarPorEdadBurbuja() {
        if (contador < 2) return;
 
        boolean huboCambio;
        do {
            huboCambio = false;
            persona1 anterior = null;
            persona1 actual = cabeza;
 
            while (actual.getSiguiente() != null) {
                persona1 sig = actual.getSiguiente();
 
                if (actual.getEdad() > sig.getEdad()) {
                    actual.setSiguiente(sig.getSiguiente());
                    sig.setSiguiente(actual);
 
                    
                    if (anterior == null) {
                        cabeza = sig;
                    } else {
                        anterior.setSiguiente(sig);
                    }
 
                   
                    if (actual.getSiguiente() == null) {
                        ultimo = actual;
                    }
 
                    anterior = sig;   
                    huboCambio = true;
                } else {
                    anterior = actual;
                    actual = sig;
                }
            }
        } while (huboCambio);
    }
}