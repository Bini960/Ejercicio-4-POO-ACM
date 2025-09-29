public class PocionCurativa extends Item {
    private final int curacion;

    public PocionCurativa(String nombre, int cantidad, int curacion) {
        super(nombre, cantidad);
        this.curacion = Math.max(1, curacion);
    }

    @Override
    public void usar(Jugador usuario, Combatiente objetivo, Batalla batalla) {
        if (cantidad <= 0) {
            batalla.registrarAccion("No quedan " + nombre + ".");
            return;
        }
        objetivo.curar(curacion);
        consumir();
        batalla.registrarAccion(usuario.getNombre() + " usa " + nombre + " y cura " + curacion + " a " + objetivo.getNombre() + ".");
    }
}
