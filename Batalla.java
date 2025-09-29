/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Modelo del combate en turno. Mantiene referencias al Jugador, la lista de Enemigos 
 * y un registro de las últimas 3 acciones. Provee utilidades para consultar enemigos vivos, registrar acciones 
 * y determinar si la batalla terminó.
 */

import java.util.*;

public class Batalla {
    private final Jugador jugador;
    private final List<Enemigo> enemigos;
    private final Deque<String> registro = new ArrayDeque<>();
    private boolean salir;

    public Batalla(Jugador jugador, List<Enemigo> enemigos) {
        this.jugador = Objects.requireNonNull(jugador);
        this.enemigos = new ArrayList<>(Objects.requireNonNull(enemigos));
    }

    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return Collections.unmodifiableList(enemigos); }

    public List<Enemigo> enemigosVivos() {
        List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo e : enemigos) if (e.estaVivo()) vivos.add(e);
        return vivos;
    }

    public void registrarAccion(String texto) {
        if (texto == null || texto.isEmpty()) return;
        registro.addLast(texto);
        while (registro.size() > 3) registro.removeFirst();
    }

    public List<String> getUltimasAcciones() { return new ArrayList<>(registro); }
    public boolean hayGanador() { return salir || !jugador.estaVivo() || enemigosVivos().isEmpty(); }
    public void marcarSalida() { this.salir = true; }
}
