/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Base para enemigos concretos. Tiene una habilidadEspecial y decide su acción de turno con una regla simple. Declara el método abstracto usarHabilidadEspecial(...) que personalizan las subclases.
 */

import java.util.Random;

public abstract class Enemigo extends Combatiente {
    protected final String habilidadEspecial;
    protected final Random rnd = new Random();

    public Enemigo(String nombre, int pv, int atk, String habilidadEspecial) {
        super(nombre, pv, atk);
        this.habilidadEspecial = habilidadEspecial == null ? "" : habilidadEspecial;
    }

    @Override
    public void tomarTurno(Batalla batalla) {
        if (!estaVivo()) return;
        Jugador j = batalla.getJugador();
        if (!j.estaVivo()) return;

        // 30% habilidad, 70% ataque
        if (rnd.nextDouble() < 0.30) {
            usarHabilidadEspecial(j, batalla);
        } else {
            atacar(j, batalla);
        }
    }

    protected abstract void usarHabilidadEspecial(Combatiente objetivo, Batalla batalla);
}
