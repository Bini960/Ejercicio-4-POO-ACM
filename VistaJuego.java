/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Encargada de la entrada/salida por consola. Muestra el estado (vida, ataque, defensas temporales, inventario, enemigos vivos, últimas 3 acciones) 
 * y solicita opciones/objetivos/ítems al usuario.
 */

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class VistaJuego {
    private final Scanner sc = new Scanner(System.in);
    private final Consumer<String> out;

    // Recibe el canal de salida desde Main
    public VistaJuego(Consumer<String> out) {
        this.out = out;
    }

    // Pequeño ayudante para enviar texto al canal de salida
    private void print(String s) { out.accept(s); }

    // Muestra vida/ataque del jugador, inventario, enemigos vivos y últimas 3 acciones
    public void mostrarEstado(Jugador jugador, List<Enemigo> enemigos, List<String> ultimasAcciones) {
        print("\n=== ESTADO ===");
        print(String.format("Jugador: %s | Vida: %d | Atk: %d | DefTmp: %d | BuffAtk: %d turnos",
                jugador.getNombre(), jugador.getPuntosVida(), jugador.getPoderAtaque(),
                jugador.getDefensaTemporal(), jugador.getTurnosBuffAtaqueRestantes()));

        print("Ítems:");
        List<Item> items = jugador.listarItems();
        if (items.isEmpty()) {
            print("  (sin ítems)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                Item it = items.get(i);
                print(String.format("  [%d] %s (x%d)", i + 1, it.getNombre(), it.getCantidad()));
            }
        }

        print("Enemigos:");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            print(String.format("  (%d) %s | Vida: %d | Atk: %d",
                    i + 1, e.getNombre(), e.getPuntosVida(), e.getPoderAtaque()));
        }

        print("Últimas acciones:");
        if (ultimasAcciones.isEmpty()) {
            print("  (sin acciones)");
        } else {
            for (String a : ultimasAcciones) print("  - " + a);
        }
    }

    // Menú principal de acciones del jugador
    public int leerOpcionAccion() {
        print("\nAcciones: [1] Atacar  [2] Usar ítem  [3] Pasar  [4] Salir");
        print("Elige: ");
        return leerEnteroRango(1, 4);
    }

    // Selección de objetivo enemigo (1..N)
    public int leerObjetivo(int max) {
        print("Elige objetivo (1-" + max + "): ");
        return leerEnteroRango(1, max);
    }

    // Selección de ítem (0=cancelar; 1..N para elegir)
    public int leerItem(int max) {
        if (max <= 0) return 0;
        print("Elige ítem (1-" + max + ", 0 cancelar): ");
        return leerEnteroRango(0, max);
    }

    // Mensajes informativos
    public void mensaje(String txt) { print(txt); }


    private int leerEnteroRango(int min, int max) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            print("Inválido. Intenta de nuevo: ");
        }
    }
}
