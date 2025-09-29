import java.util.List;
import java.util.Scanner;

public class VistaJuego {
    private final Scanner sc = new Scanner(System.in);

    public void mostrarEstado(Jugador jugador, List<Enemigo> enemigos, List<String> ultimasAcciones) {
        System.out.println("\n=== ESTADO ===");
        System.out.printf("Jugador: %s | Vida: %d | Atk: %d | DefTmp: %d | BuffAtk: %d turnos\n",
                jugador.getNombre(), jugador.getPuntosVida(), jugador.getPoderAtaque(),
                jugador.getDefensaTemporal(), jugador.getTurnosBuffAtaqueRestantes());
        System.out.println("Ítems:");
        List<Item> items = jugador.listarItems();
        if (items.isEmpty()) System.out.println("  (sin ítems)");
        else for (int i = 0; i < items.size(); i++)
            System.out.printf("  [%d] %s (x%d)\n", i + 1, items.get(i).getNombre(), items.get(i).getCantidad());

        System.out.println("Enemigos:");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            System.out.printf("  (%d) %s | Vida: %d | Atk: %d\n", i + 1, e.getNombre(), e.getPuntosVida(), e.getPoderAtaque());
        }
        System.out.println("Últimas acciones:");
        if (ultimasAcciones.isEmpty()) System.out.println("  (sin acciones)");
        for (String a : ultimasAcciones) System.out.println("  - " + a);
    }

    public int leerOpcionAccion() {
        System.out.println("\nAcciones: [1] Atacar  [2] Usar ítem  [3] Pasar  [4] Salir");
        System.out.print("Elige: ");
        return leerEnteroRango(1, 4);
    }

    public int leerObjetivo(int max) {
        System.out.print("Elige objetivo (1-" + max + "): ");
        return leerEnteroRango(1, max);
    }

    public int leerItem(int max) {
        if (max <= 0) return 0;
        System.out.print("Elige ítem (1-" + max + ", 0 cancelar): ");
        return leerEnteroRango(0, max);
    }

    private int leerEnteroRango(int min, int max) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            System.out.print("Inválido. Intenta de nuevo: ");
        }
    }

    public void mensaje(String txt) { System.out.println(txt); }
}
