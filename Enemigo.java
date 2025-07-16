package battlespace;

public class Enemigo {
    private String tipo;
    private int salud;
    private int ataque;
    private int x;
    private int y;

    public Enemigo(String tipo, int salud, int ataque, int x, int y) {
        this.tipo = tipo;
        this.salud = salud;
        this.ataque = ataque;
        this.x = x;
        this.y = y;
    }

    public void atacar(Jugador jugador) {
        jugador.recibirDaño(this.ataque);
        System.out.println(tipo + " enemigo ataca al jugador. Salud del jugador: " + jugador.getSalud());
    }

    public void recibirDaño(int cantidad) {
        this.salud -= cantidad;
        if (this.salud < 0) this.salud = 0;
    }
    
    public boolean estaVivo() {
        return this.salud > 0;
    }

    public void moverAbajo() {
        this.y++;
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }

    public void mostrarEstado() {
        System.out.println("Enemigo: " + tipo + " - Salud: " + salud);
    }
}
