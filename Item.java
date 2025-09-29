/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción:Base para ítems usables por el jugador. Lleva nombre y cantidad de usos. Define usar(Jugador, Combatiente, Batalla) para aplicar el efecto y consumir() para descontar usos. 
 * Por defecto no requiere objetivo enemigo (algunos ítems pueden redefinir esa necesidad).
 */ 

public abstract class Item {
    protected final String nombre;
    protected int cantidad;

    public Item(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = Math.max(0, cantidad);
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }

    public boolean requiereObjetivoEnemigo() { return false; }

    public abstract void usar(Jugador usuario, Combatiente objetivo, Batalla batalla);

    protected void consumir() {
        if (cantidad > 0) cantidad--;
    }
}
