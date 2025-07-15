package battlespace;

import java.io.IOException;

public class ManejadorDeEntrada implements Runnable {
    private Juego juego;
    private boolean corriendo = true;

    public void establecerJuego(Juego juego) {
        this.juego = juego;
    }

    public void detener() {
        corriendo = false;
    }

    @Override
    public void run() {
        try {
            // Configurar la terminal para entrada sin buffer
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                // En Windows es más complicado, pero funciona básicamente
                while (corriendo) {
                    if (System.in.available() > 0) {
                        int tecla = System.in.read();
                        if (tecla == -1) break;
                        procesarEntrada((char) tecla);
                    }
                    Thread.sleep(10); // Pausa pequeña para no consumir CPU
                }
            } else {
                // Linux/Mac
                while (corriendo) {
                    if (System.in.available() > 0) {
                        int tecla = System.in.read();
                        if (tecla == -1) break;
                        procesarEntrada((char) tecla);
                    }
                    Thread.sleep(10);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void procesarEntrada(char c) {
        switch (Character.toUpperCase(c)) {
            case 'A':
                juego.moverJugadorIzquierda();
                break;
            case 'D':
                juego.moverJugadorDerecha();
                break;
            case ' ':
                juego.dispararBala();
                break;
            case 'Q':
                juego.salir();
                break;
        }
    }
}
