public class Elfo extends Enemigo {
    private final double evasivo;

    public Elfo(String nombre, int pv, int atk, String hab, double evasivo) {
        super(nombre, pv, atk, hab);
        this.evasivo = Math.max(0, Math.min(0.5, evasivo));
    }

    @Override
    public void recibirAtaque(int dano) {
        int real = dano;
        if (evasivo > 0 && Math.random() < 0.30) { // 30% mitiga a la mitad
            real = Math.max(0, dano / 2);
        }
        super.recibirAtaque(real);
    }

    @Override
    protected void usarHabilidadEspecial(Combatiente objetivo, Batalla batalla) {
        int extra = 4;
        objetivo.recibirAtaque(poderAtaque + extra);
        batalla.registrarAccion(nombre + " usa " + habilidadEspecial + " y hace " + (poderAtaque + extra) + " de daño.");
    }
}
