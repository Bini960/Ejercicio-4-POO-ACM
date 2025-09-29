public class JefeDragon extends Dragon {
    private final int alientoDragonNivel;

    public JefeDragon(String nombre, int pv, int atk, String hab, int fuegoBase, int alientoDragonNivel) {
        super(nombre, pv, atk, hab, fuegoBase);
        this.alientoDragonNivel = Math.max(1, alientoDragonNivel);
    }

    @Override
    protected void usarHabilidadEspecial(Combatiente objetivo, Batalla batalla) {
        super.usarHabilidadEspecial(objetivo, batalla);
        int dot = alientoDragonNivel; // chip de quemadura
        objetivo.recibirAtaque(dot);
        batalla.registrarAccion(getNombre() + " deja quemadura persistente (" + dot + ").");
    }
}
