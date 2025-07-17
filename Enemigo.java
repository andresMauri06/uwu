package battlespace;

import java.util.Random;

public class Enemigo {
    private String tipo;
    private int salud;
    private int ataque;
    private int fila;
    private int columna;
    private char simbolo;

    public Enemigo(int fila, int columna) {
        this.tipo = "NaveAlienigena";
        this.salud = 50;
        this.ataque = 10;
        this.fila = fila;
        this.columna = columna;
        this.simbolo = 'E';
    }

    public void atacar(Jugador jugador) {
        jugador.recibirDaño(this.ataque);
        System.out.println(tipo + " ataca al jugador. Salud del jugador: " + jugador.getSalud());
    }

    public void recibirDaño(int cantidad) {
        this.salud -= cantidad;
        if (this.salud < 0) this.salud = 0;
        System.out.println(tipo + " recibe " + cantidad + " de daño. Salud restante: " + this.salud);
    }

    public boolean estaViva() {
        return this.salud > 0;
    }

    public void moverAleatorio(int filas, int columnas) {
        Random rand = new Random();
        int direccion = rand.nextInt(10); 
        int nuevaFila = this.fila;
        int nuevaColumna = this.columna;

        if (direccion < 7) { 
            if (nuevaFila < filas - 1) nuevaFila++;
        } else if (direccion == 7) { 
            if (nuevaColumna > 0) nuevaColumna--;
        } else if (direccion == 8) { 
            if (nuevaColumna < columnas - 1) nuevaColumna++;
        } else {
            if (nuevaFila > 0) nuevaFila--;
        }

        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    public Mejora soltarMejora() {
        Random rand = new Random();
        if (rand.nextDouble() < 0.3) { // 30% de probabilidad
            String tipoMejora = rand.nextBoolean() ? "vida" : "ataque";
            int valor = 10;
            return new Mejora(tipoMejora, valor, this.fila, this.columna);
        }
        return null;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getAtaque() {
        return ataque;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void mostrarEstado() {
        System.out.println("Enemigo: " + tipo + " - Salud: " + salud + " - Posición: (" + fila + ", " + columna + ")");
    }
}
