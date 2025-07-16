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
    
    public void atacar(Enemigo enemigo) {
        enemigo.recibirDaño(this.ataque);
        this.puntaje += 10;
    }
    public void recibirDaño(int cantidad) {
        this.salud -= cantidad;
        if (this.salud < 0) this.salud = 0;
    }

    public boolean estaVivo() {
        return this.salud > 0;
    }

    public void disparar() {
    }
    
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

