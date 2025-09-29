public class BombaFuego extends Item {
    private final int danioArea;

    public BombaFuego(String nombre, int cantidad, int danioArea) {
        super(nombre, cantidad);
        this.danioArea = Math.max(1, danioArea);
    }

    @Override
    public boolean requiereObjetivoEnemigo() { return false; }

    @Override
    public void usar(Jugador usuario, Combatiente objetivo, Batalla batalla) {
        if (cantidad <= 0) {
            batalla.registrarAccion("No quedan " + nombre + ".");
            return;
        }
        for (Enemigo e : batalla.enemigosVivos()) {
            e.recibirAtaque(danioArea);
        }
        consumir();
        batalla.registrarAccion(usuario.getNombre() + " lanza " + nombre + " e impacta a todos los enemigos por " + danioArea + ".");
    }
}
