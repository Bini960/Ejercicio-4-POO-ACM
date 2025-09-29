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
