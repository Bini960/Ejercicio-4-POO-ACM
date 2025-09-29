/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Ítem de buff que aumenta el ataque del jugador por N turnos. No requiere objetivo enemigo. Pensado para planificar picos de daño.
 */

public class PocionAtaque extends Item {
    private final int bonus;
    private final int turnos;

    public PocionAtaque(String nombre, int cantidad, int bonus, int turnos) {
        super(nombre, cantidad);
        this.bonus = Math.max(1, bonus);
        this.turnos = Math.max(1, turnos);
    }

    @Override
    public void usar(Jugador usuario, Combatiente objetivo, Batalla batalla) {
        if (cantidad <= 0) {
            batalla.registrarAccion("No quedan " + nombre + ".");
            return;
        }
        usuario.aplicarBuffAtaque(bonus, turnos);
        consumir();
        batalla.registrarAccion(usuario.getNombre() + " usa " + nombre + " (+" + bonus + " ATQ por " + turnos + " turnos).");
    }
}
