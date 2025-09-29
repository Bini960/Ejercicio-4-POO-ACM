/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Ítem defensivo: otorga defensa extra al jugador por N turnos, mitigando daño entrante durante ese periodo.
 */

public class EscudoTemporal extends Item {
    private final int defensaExtra;
    private final int turnos;

    public EscudoTemporal(String nombre, int cantidad, int defensaExtra, int turnos) {
        super(nombre, cantidad);
        this.defensaExtra = Math.max(1, defensaExtra);
        this.turnos = Math.max(1, turnos);
    }

    @Override
    public void usar(Jugador usuario, Combatiente objetivo, Batalla batalla) {
        if (cantidad <= 0) {
            batalla.registrarAccion("No quedan " + nombre + ".");
            return;
        }
        usuario.aplicarEscudoTemporal(defensaExtra, turnos);
        consumir();
        batalla.registrarAccion(usuario.getNombre() + " activa " + nombre + " (+" + defensaExtra + " DEF por " + turnos + " turnos).");
    }
}
