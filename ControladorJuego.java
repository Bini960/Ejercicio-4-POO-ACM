/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Orquesta el flujo por turnos (patrón MVC). Pide decisiones a la Vista, aplica las acciones sobre el Modelo, ejecuta los turnos de los enemigos y avanza los efectos temporales del jugador. Termina cuando hay victoria, derrota o el jugador elige salir.
 */

import java.util.List;

public class ControladorJuego {
    private final VistaJuego vista;
    private final Batalla batalla;

    public ControladorJuego(VistaJuego vista, Batalla batalla) {
        this.vista = vista;
        this.batalla = batalla;
    }

    public void iniciar() {
        vista.mensaje("== BATALLA ==");
        while (!batalla.hayGanador()) {
            vista.mostrarEstado(batalla.getJugador(), batalla.enemigosVivos(), batalla.getUltimasAcciones());

            // Turno jugador
            if (!batalla.getJugador().estaVivo()) break;
            int op = vista.leerOpcionAccion();
            if (op == 1) {
                List<Enemigo> vivos = batalla.enemigosVivos();
                if (vivos.isEmpty()) break;
                int idx = vista.leerObjetivo(vivos.size()) - 1;
                batalla.getJugador().atacar(vivos.get(idx), batalla);
            } else if (op == 2) {
                List<Item> items = batalla.getJugador().listarItems();
                int idx = vista.leerItem(items.size());
                if (idx == 0) batalla.registrarAccion("Jugador canceló ítem.");
                else {
                    Item elegido = items.get(idx - 1);
                    if (elegido.requiereObjetivoEnemigo()) {
                        List<Enemigo> vivos = batalla.enemigosVivos();
                        if (vivos.isEmpty()) batalla.registrarAccion("No hay enemigos para ese ítem.");
                        else {
                            int idEn = vista.leerObjetivo(vivos.size()) - 1;
                            batalla.getJugador().usarItem(elegido, vivos.get(idEn), batalla);
                        }
                    } else {
                        batalla.getJugador().usarItem(elegido, batalla.getJugador(), batalla);
                    }
                }
            } else if (op == 3) {
                batalla.registrarAccion("El jugador pasa el turno.");
            } else if (op == 4) {
                batalla.registrarAccion("El jugador decidió salir.");
                batalla.marcarSalida();
            }

            if (batalla.hayGanador()) break;

            // Turno enemigos
            for (Enemigo e : batalla.enemigosVivos()) {
                if (!batalla.getJugador().estaVivo()) break;
                e.tomarTurno(batalla);
            }

            // Efectos temporales
            batalla.getJugador().avanzarTurnoTemporal();
        }

        if (batalla.getJugador().estaVivo() && batalla.enemigosVivos().isEmpty()) vista.mensaje("\n¡Victoria!");
        else if (!batalla.getJugador().estaVivo()) vista.mensaje("\nDerrota...");
        else vista.mensaje("\nFin de la batalla.");
    }
}
