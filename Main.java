/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Punto de entrada del programa. Crea al Jugador con su inventario inicial, genera entre 1 y 3 Enemigos, construye el modelo Batalla, la Vista y el Controlador y arranca el bucle de juego.
 */

import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        // Canal de salida de texto para toda la aplicación
        Consumer<String> printer = System.out::println;

        // Jugador con inventario inicial
        Jugador jugador = new Jugador("Héroe", 120, 18);
        jugador.agregarItem(new PocionCurativa("Poción Curativa", 2, 35));
        jugador.agregarItem(new PocionAtaque("Poción de Ataque", 1, 8, 2));
        jugador.agregarItem(new BombaFuego("Bomba de Fuego", 1, 12));
        jugador.agregarItem(new EscudoTemporal("Escudo Temporal", 1, 6, 2));

        // Generar entre 1 y 3 enemigos, con posibilidad de jefes
        List<Enemigo> enemigos = generarEnemigos();

        // Modelo (estado de la batalla) y Vista (I/O por consola)
        Batalla batalla = new Batalla(jugador, enemigos);
        VistaJuego vista = new VistaJuego(printer);

        // Controlador (orquesta turnos y decisiones)
        ControladorJuego ctrl = new ControladorJuego(vista, batalla);
        ctrl.iniciar();
    }

    private static List<Enemigo> generarEnemigos() {
        Random rnd = new Random();
        int cantidad = 1 + rnd.nextInt(3); // 1..3
        List<Enemigo> lista = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            int tipo = rnd.nextInt(3);      // 0=Elfo, 1=Gigante, 2=Dragón
            boolean jefe = rnd.nextDouble() < 0.35;
            lista.add(crear(tipo, jefe, i + 1));
        }
        return lista;
    }

    private static Enemigo crear(int tipo, boolean jefe, int idx) {
        switch (tipo) {
            case 0 -> {
                if (jefe) return new JefeElfo("Jefe Elfo " + idx, 85, 16, "Flecha Sombría", 0.30, "Golpe Fantasmal");
                return new Elfo("Elfo " + idx, 60, 12, "Disparo Preciso", 0.25);
            }
            case 1 -> {
                if (jefe) return new JefeGigante("Jefe Gigante " + idx, 120, 18, "Martillazo", 6, 3);
                return new Gigante("Gigante " + idx, 90, 15, "Aplastamiento", 4);
            }
            default -> {
                if (jefe) return new JefeDragon("Jefe Dragón " + idx, 110, 17, "Aliento Ígneo", 7, 2);
                return new Dragon("Dragón " + idx, 80, 14, "Llamarada", 5);
            }
        }
    }
}
