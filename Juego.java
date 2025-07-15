package battlespace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Juego {
    private final int ANCHO = 30;
    private final int ALTO = 20;
    private char[][] tablero;

    private Jugador jugador;
    private List<Enemigo> enemigos;
    private List<Bala> balas;

    private boolean corriendo = true;
    private int puntuacion = 0;
    private int vidas = 3;

    private ManejadorDeEntrada manejadorDeEntrada;

    // Contadores para movimientos lentos
    private int contadorMovimientoEnemigos = 0;
    private int intervaloMovimientoEnemigos = 90; // cada 90 frames (3 segundos a 30fps)

    private int contadorMovimientoBalas = 0;
    private int intervaloMovimientoBalas = 15; // cada 15 frames (0.5 segundos)

    private int tiempoEnfriamientoDisparo = 0;
    private final int RETRASO_DISPARO_BALA = 10; // 10 frames de retraso entre disparos

    public Juego() {
        tablero = new char[ALTO][ANCHO];
        jugador = new Jugador(ANCHO / 2, ALTO - 2);
        enemigos = new ArrayList<>();
        balas = new ArrayList<>();

        generarEnemigos();

        manejadorDeEntrada = new ManejadorDeEntrada();
        manejadorDeEntrada.establecerJuego(this);
        new Thread(manejadorDeEntrada).start();
    }

    public void iniciar() {

        renderizar();

        long ultimaVez = System.currentTimeMillis();
        final int FPS_OBJETIVO = 30;
        final long TIEMPO_FRAME = 1000 / FPS_OBJETIVO;

        while (corriendo) {
            long tiempoActual = System.currentTimeMillis();
            long deltaTime = tiempoActual - ultimaVez;

            if (deltaTime >= TIEMPO_FRAME) {
                actualizar();
                ultimaVez = tiempoActual;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        Renderizador.mostrarJuegoTerminado(puntuacion);
        manejadorDeEntrada.detener();
    }

    private void actualizar() {
        boolean necesitaRenderizar = false;

        if (tiempoEnfriamientoDisparo > 0) {
            tiempoEnfriamientoDisparo--;
        }

        contadorMovimientoBalas++;
        if (contadorMovimientoBalas >= intervaloMovimientoBalas) {
            Iterator<Bala> it = balas.iterator();
            while (it.hasNext()) {
                Bala b = it.next();
                b.moverArriba();
                necesitaRenderizar = true;
                if (b.obtenerY() < 0) {
                    it.remove();
                } else {
                    Iterator<Enemigo> ei = enemigos.iterator();
                    while (ei.hasNext()) {
                        Enemigo e = ei.next();
                        if (e.obtenerX() == b.obtenerX() && e.obtenerY() == b.obtenerY()) {
                            ei.remove();
                            it.remove();
                            puntuacion += 100;
                            necesitaRenderizar = true;
                            break;
                        }
                    }
                }
            }
            contadorMovimientoBalas = 0;
        }

        for (Enemigo e : enemigos) {
            if (e.obtenerY() >= jugador.obtenerY()) {
                vidas--;
                enemigos.clear();
                balas.clear();
                generarEnemigos();
                necesitaRenderizar = true;
                // Aumentar dificultad un poco
                if (intervaloMovimientoEnemigos > 60) {
                    intervaloMovimientoEnemigos -= 5;
                }
                break;
            }
        }

        if (enemigos.isEmpty()) {
            generarEnemigos();
            necesitaRenderizar = true;

            if (intervaloMovimientoEnemigos > 60) {
                intervaloMovimientoEnemigos -= 3;
            }
            if (intervaloMovimientoBalas > 8) {
                intervaloMovimientoBalas -= 1;
            }
        }

        if (vidas <= 0) corriendo = false;

        contadorMovimientoEnemigos++;
        if (contadorMovimientoEnemigos >= intervaloMovimientoEnemigos) {
            for (Enemigo e : enemigos) {
                e.moverAbajo();
            }
            contadorMovimientoEnemigos = 0;
            necesitaRenderizar = true;
        }

        if (necesitaRenderizar) {
            renderizar();
        }
    }

    private void renderizar() {
        limpiarTablero();

        tablero[jugador.obtenerY()][jugador.obtenerX()] = '^';

        for (Enemigo e : enemigos) {
            if (e.obtenerY() >= 0 && e.obtenerY() < ALTO)
                tablero[e.obtenerY()][e.obtenerX()] = '@';
        }

        // Dibujar balas
        for (Bala b : balas) {
            if (b.obtenerY() >= 0 && b.obtenerY() < ALTO)
                tablero[b.obtenerY()][b.obtenerX()] = '|';
        }

        // Usar el renderizador optimizado
        Renderizador.renderizarJuego(tablero, puntuacion, vidas, ANCHO);
    }

    private void limpiarTablero() {
        for (int y = 0; y < ALTO; y++) {
            for (int x = 0; x < ANCHO; x++) {
                tablero[y][x] = ' ';
            }
        }
    }

    private void generarEnemigos() {
        for (int fila = 0; fila < 3; fila++) {
            for (int i = 4; i < ANCHO - 4; i += 4) {
                enemigos.add(new Enemigo(i, 2 + fila));
            }
        }
    }

    public void moverJugadorIzquierda() {
        jugador.mover(-1, ANCHO);
        renderizar(); // Solo renderizar cuando el jugador se mueva
    }

    public void moverJugadorDerecha() {
        jugador.mover(1, ANCHO);
        renderizar(); // Solo renderizar cuando el jugador se mueva
    }

    public void dispararBala() {
        if (tiempoEnfriamientoDisparo <= 0) {
            balas.add(new Bala(jugador.obtenerX(), jugador.obtenerY() - 1));
            tiempoEnfriamientoDisparo = RETRASO_DISPARO_BALA;
            renderizar(); // Solo renderizar cuando se dispare
        }
    }

    public void salir() {
        corriendo = false;
    }
}
