/*
 * Nombre: Andrés Castro Morales
 * Carné: 25039
 * Descripción: Enemigo de fuerza bruta: su habilidad especial suma un valor fijo alto al ataque básico, causando picos de daño. 
 */

public class Gigante extends Enemigo {
    private final int fuerzaBruta;

    public Gigante(String nombre, int pv, int atk, String hab, int fuerzaBruta) {
        super(nombre, pv, atk, hab);
        this.fuerzaBruta = Math.max(0, fuerzaBruta);
    }

    @Override
    protected void usarHabilidadEspecial(Combatiente objetivo, Batalla batalla) {
        int golpe = poderAtaque + fuerzaBruta;
        objetivo.recibirAtaque(golpe);
        batalla.registrarAccion(nombre + " descarga " + habilidadEspecial + " por " + golpe + " de daño.");
    }
}
