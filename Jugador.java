package battlespace;

public class Jugador {
    private int x;
    private int y;

    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover(int dx, int anchoMaximo) {
        x += dx;
        if (x < 0) x = 0;
        if (x >= anchoMaximo) x = anchoMaximo - 1;
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }
}