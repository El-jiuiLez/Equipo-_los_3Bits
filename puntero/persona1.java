public class persona1 {
private String nombre;
    private int edad;
    private persona1 siguiente;   // referencia (el "puntero") al siguiente nodo
 
    public persona1(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.siguiente = null;
    }
 
    public String getNombre() { return nombre; }
 
    public int getEdad() { return edad; }
 
    public persona1 getSiguiente() { return siguiente; }
 
    public void setSiguiente(persona1 siguiente) { this.siguiente = siguiente; }
}