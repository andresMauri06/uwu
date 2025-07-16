package battlespace;

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
            
            while (corriendo) {
                if (System.in.available() > 0) {
                    int tecla = System.in.read();
                    if (tecla == -1) break;
                    procesarEntrada((char) tecla);
                }
                Thread.sleep(10); 
            }
        } catch (Exception e) {
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
