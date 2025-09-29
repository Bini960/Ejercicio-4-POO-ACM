public class Dragon extends Enemigo {
    private final int fuegoBase;

    public Dragon(String nombre, int pv, int atk, String hab, int fuegoBase) {
        super(nombre, pv, atk, hab);
        this.fuegoBase = Math.max(0, fuegoBase);
    }

    @Override
    protected void usarHabilidadEspecial(Combatiente objetivo, Batalla batalla) {
        int flama = poderAtaque + fuegoBase;
        objetivo.recibirAtaque(flama);
        batalla.registrarAccion(nombre + " exhala " + habilidadEspecial + " causando " + flama + " de daño.");
    }
}
