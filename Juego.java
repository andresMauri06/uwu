package battlespace;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private final int ANCHO = 30;
    private final int ALTO = 20;
    private char[][] tablero;

    private Jugador jugador;
    private List<Enemigo> enemigos;

    private boolean juegoTerminado = false;
    private int turno = 1;

    private ManejadorDeEntrada manejadorDeEntrada;

    public Juego(int cantidadEnemigos) {
        tablero = new char[ALTO][ANCHO];
        jugador = new Jugador(ANCHO / 2, ALTO - 2);
        enemigos = new ArrayList<>();

        generarEnemigos(cantidadEnemigos);

        manejadorDeEntrada = new ManejadorDeEntrada();
        manejadorDeEntrada.establecerJuego(this);
        new Thread(manejadorDeEntrada).start();
    }

    
    public void iniciarJuego() {
        juegoTerminado = false;
        turno = 1;
        mostrarEstado();
    }

    
    public void ejecutarTurno() {
        if (juegoTerminado) {
            System.out.println("El juego ha terminado.");
            return;
        }

        System.out.println("Turno " + turno);

    
        jugador.atacar(enemigos.get(0));

        
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                enemigo.atacar(jugador);
            }
        }

        verificarFinDelJuego();
        turno++;
    }

     
    public void verificarFinDelJuego() {
        if (!jugador.estaVivo()) {
            juegoTerminado = true;
            System.out.println("¡Has perdido! El jugador ha sido derrotado.");
        }

        if (enemigos.isEmpty()) {
            juegoTerminado = true;
            System.out.println("¡Has ganado! Todos los enemigos han sido derrotados.");
        }
    }
    
    public void mostrarEstado() {
        System.out.println("Jugador: " + jugador.getNombre() + " - Salud: " + jugador.getSalud());
        System.out.println("Enemigos restantes:");
        for (Enemigo enemigo : enemigos) {
            enemigo.mostrarEstado();
        }
    }

    public void generarEnemigos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            int x = (int) (Math.random() * ANCHO);
            int y = (int) (Math.random() * (ALTO / 2));
            enemigos.add(new Enemigo("TipoA", 50, 10, x, y));
        }
    }

    public void moverJugadorIzquierda() {
        jugador.mover(-1, 0, ANCHO, ALTO);
        renderizar();
    }
    
    public void moverJugadorDerecha() {
        jugador.mover(1, 0, ANCHO, ALTO);
        renderizar();
    }

    public void moverJugadorArriba() {
        jugador.mover(0, -1, ANCHO, ALTO);
        renderizar();
    }

    public void moverJugadorAbajo() {
        jugador.mover(0, 1, ANCHO, ALTO);
        renderizar();
    }
    
    public void dispararBala() {
        jugador.disparar();
    }

    public void renderizar() {
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                tablero[i][j] = ' ';
            }
        }

        tablero[jugador.obtenerY()][jugador.obtenerX()] = '^';  
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                tablero[enemigo.obtenerY()][enemigo.obtenerX()] = '@'; 
            }
        }

        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }
}
