package battlespace;

public class Jugador {
    private String nombre;
    private int salud;
    private int ataque;
    private int puntaje;
    private int x;
    private int y;

    public Jugador(int x, int y) {
        this.nombre = "Jugador1";
        this.salud = 100;
        this.ataque = 10;
        this.puntaje = 0;
        this.x = x;
        this.y = y;
    }
    
    public void mover(int dx, int dy, int maxWidth, int maxHeight) {
        x += dx;
        y += dy;

        if (x < 0) x = 0;
        if (x >= maxWidth) x = maxWidth - 1;
        if (y < 0) y = 0;
        if (y >= maxHeight) y = maxHeight - 1;
    }

    // Atacar a un enemigo
    public void atacar(Enemigo enemigo) {
        enemigo.recibirDaño(this.ataque);
        this.puntaje += 10;
    }
    public void recibirDaño(int cantidad) {
        this.salud -= cantidad;
        if (this.salud < 0) this.salud = 0;
    }

    // Verificar si el jugador sigue vivo
    public boolean estaVivo() {
        return this.salud > 0;
    }

    // Disparar (lógica del disparo no implementada aún)
    public void disparar() {
        // Aquí va la lógica para disparar
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }

    public int getSalud() {
        return salud;
    }

    public int getPuntaje() {
        return puntaje;
    }
}

