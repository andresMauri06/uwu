package battlespace;

import battlespace.Jugador;

public class Mejora {
    private String tipo;
    private int valor;
    private int fila;
    private int columna;

    public Mejora(String tipo, int valor, int fila, int columna) {
        this.tipo = tipo;
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String description() {
        return "Mejora de " + tipo + " con valor +" + valor;
    }

    public void aplicar(Jugador jugador) {
        if (tipo.equals("vida")) {
            jugador.recibirCura(valor);
            System.out.println("Jugador recibe mejora de vida +" + valor + ". Salud actual: " + jugador.getSalud());
        } else if (tipo.equals("ataque")) {
            jugador.aumentarAtaque(valor);
            System.out.println("Jugador recibe mejora de ataque +" + valor + ". Ataque actual: " + jugador.getAtaque());
        }
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
