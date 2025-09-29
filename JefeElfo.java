/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Versión jefe del Elfo. Además de la habilidad del Elfo, encadena una habilidad extra que agrega un daño adicional ligero; 
 * conserva la naturaleza evasiva, volviéndose más peligroso en turnos consecutivos.
 */

public class JefeElfo extends Elfo {
    private final String habilidadExtra;

    public JefeElfo(String nombre, int pv, int atk, String hab, double evasivo, String habilidadExtra) {
        super(nombre, pv, atk, hab, evasivo);
        this.habilidadExtra = habilidadExtra == null ? "" : habilidadExtra;
    }

    @Override
    protected void usarHabilidadEspecial(Combatiente objetivo, Batalla batalla) {
        super.usarHabilidadEspecial(objetivo, batalla);
        int extra = 3;
        objetivo.recibirAtaque(extra);
        batalla.registrarAccion(getNombre() + " encadena " + habilidadExtra + " (+ " + extra + " daño).");
    }
}
