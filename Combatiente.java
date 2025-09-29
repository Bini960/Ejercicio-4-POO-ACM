/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Superclase común a Jugador y Enemigo. Define identidad (nombre), estado de combate, acciones básicas y el contrato tomarTurno(Batalla), que cada subclase implementa.
 */
public abstract class Combatiente {
    protected final String nombre;
    protected int puntosVida;
    protected final int poderAtaque;

    public Combatiente(String nombre, int puntosVida, int poderAtaque) {
        this.nombre = nombre;
        this.puntosVida = Math.max(1, puntosVida);
        this.poderAtaque = Math.max(1, poderAtaque);
    }

    public String getNombre() { return nombre; }
    public int getPuntosVida() { return puntosVida; }
    public int getPoderAtaque() { return poderAtaque; }

    public boolean estaVivo() { return puntosVida > 0; }

    public void recibirAtaque(int dano) {
        int real = Math.max(0, dano);
        puntosVida -= real;
        if (puntosVida < 0) puntosVida = 0;
    }

    public void curar(int cantidad) {
        puntosVida += Math.max(0, cantidad);
    }

    public void atacar(Combatiente obj, Batalla batalla) {
        obj.recibirAtaque(poderAtaque);
        batalla.registrarAccion(nombre + " ataca a " + obj.getNombre() + " por " + poderAtaque + ".");
    }

    public abstract void tomarTurno(Batalla batalla);
}
