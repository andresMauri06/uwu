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

    // Atacar al jugador
    public void atacar(Jugador jugador) {
        jugador.recibirDaño(this.ataque);
        System.out.println(tipo + " enemigo ataca al jugador. Salud del jugador: " + jugador.getSalud());
    }

    // Recibir daño
    public void recibirDaño(int cantidad) {
        this.salud -= cantidad;
        if (this.salud < 0) this.salud = 0;
    }

    // Verificar si el enemigo sigue vivo
    public boolean estaVivo() {
        return this.salud > 0;
    }

    // Mover el enemigo hacia abajo
    public void moverAbajo() {
        this.y++;
    }

    // Getters
    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }

    // Mostrar el estado del enemigo
    public void mostrarEstado() {
        System.out.println("Enemigo: " + tipo + " - Salud: " + salud);
    }
}
