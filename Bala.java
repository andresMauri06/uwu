package battlespace;

public class Bala {
    private int x;
    private int y;

    public Bala(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moverArriba() {
        y--;
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }
}