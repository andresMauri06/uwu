package battlespace;

public class Enemigo {
    private int x;
    private int y;

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moverAbajo() {
        y++;
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }
}