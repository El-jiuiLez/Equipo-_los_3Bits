public abstract class abs1 implements interfaz1 {
    protected persona1 cabeza;
    protected persona1 ultimo;  
    protected int contador;

    public abs1() {
        this.cabeza = null;
        this.ultimo = null;
        this.contador = 0;
    }

    @Override
    public boolean estaVacia() {
        return cabeza == null;
    }
 
    @Override
    public int getContador() {
        return contador;
    }
}