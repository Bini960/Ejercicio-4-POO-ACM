public class JefeGigante extends Gigante {
    private final int resistenciaExtra;

    public JefeGigante(String nombre, int pv, int atk, String hab, int fuerzaBruta, int resistenciaExtra) {
        super(nombre, pv, atk, hab, fuerzaBruta);
        this.resistenciaExtra = Math.max(0, resistenciaExtra);
    }

    @Override
    public void recibirAtaque(int dano) {
        int mitigado = Math.max(0, dano - resistenciaExtra);
        super.recibirAtaque(mitigado);
    }
}
