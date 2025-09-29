/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Combatiente controlado por el usuario. Posee inventario de Item, puede usar ítems y gestiona efectos temporales. Su turno lo decide el Controlador; aquí solo se aplican efectos.
 */

import java.util.*;

public class Jugador extends Combatiente {
    private final List<Item> inventario = new ArrayList<>();
    private int defensaTemporal = 0;
    private int turnosDefensaRestantes = 0;
    private int bonusAtaque = 0;
    private int turnosBuffAtaque = 0;

    public Jugador(String nombre, int puntosVida, int poderAtaque) {
        super(nombre, puntosVida, poderAtaque);
    }

    public void agregarItem(Item item) {
        if (item != null) inventario.add(item);
    }

    public List<Item> listarItems() { return Collections.unmodifiableList(inventario); }

    public int getDefensaTemporal() { return defensaTemporal; }
    public int getTurnosBuffAtaqueRestantes() { return turnosBuffAtaque; }

    @Override
    public void recibirAtaque(int dano) {
        int mitigado = Math.max(0, dano - defensaTemporal);
        super.recibirAtaque(mitigado);
    }

    public void usarItem(Item item, Combatiente objetivo, Batalla batalla) {
        if (item == null || !inventario.contains(item)) {
            batalla.registrarAccion("Ítem inválido.");
            return;
        }
        if (item.getCantidad() <= 0) {
            batalla.registrarAccion("No quedan usos de " + item.getNombre() + ".");
            return;
        }
        item.usar(this, objetivo, batalla);
        if (item.getCantidad() <= 0) inventario.remove(item);
    }

    public void aplicarBuffAtaque(int bonus, int turnos) {
        bonusAtaque += Math.max(0, bonus);
        turnosBuffAtaque = Math.max(turnosBuffAtaque, Math.max(0, turnos));
    }

    public void aplicarEscudoTemporal(int defensaExtra, int turnos) {
        defensaTemporal = Math.max(defensaTemporal, Math.max(0, defensaExtra));
        turnosDefensaRestantes = Math.max(turnosDefensaRestantes, Math.max(0, turnos));
    }

    public void avanzarTurnoTemporal() {
        if (turnosDefensaRestantes > 0) {
            turnosDefensaRestantes--;
            if (turnosDefensaRestantes == 0) defensaTemporal = 0;
        }
        if (turnosBuffAtaque > 0) {
            turnosBuffAtaque--;
            if (turnosBuffAtaque == 0) bonusAtaque = 0;
        }
    }

    @Override
    public void atacar(Combatiente obj, Batalla batalla) {
        int dmg = poderAtaque + bonusAtaque;
        obj.recibirAtaque(dmg);
        batalla.registrarAccion(nombre + " ataca a " + obj.getNombre() + " por " + dmg + ".");
    }

    @Override
    public void tomarTurno(Batalla batalla) {
        // El Controlador decide qué hacer; aquí no imprimimos ni leemos.
    }
}
