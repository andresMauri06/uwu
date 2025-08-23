package battlespace;

public class Juego {
    private Jugador jugador;
    private Enemigo[] enemigos;
    private boolean juegoTerminado;
    private int turno;
    private Combate combate;

    public Juego(String nombreJugador, int cantidadEnemigos) {
        this.jugador = new Jugador(nombreJugador, 9, 7);
        this.enemigos = new Enemigo[cantidadEnemigos];
        this.juegoTerminado = false;
        this.turno = 1;
        generarEnemigos(cantidadEnemigos);
        this.combate = new Combate(jugador, enemigos);
    }

    public void iniciarJuego() {
        juegoTerminado = false;
        turno = 1;
        System.out.println("¡Battle Space iniciado!");
        System.out.println("Controles: A/D/W/S para moverse, E para disparar, Q para salir");
        mostrarEstado();
        combate.mostrarCampo();
    }

    public void ejecutarTurno(char tecla) {
        if (juegoTerminado) {
            return;
        }

        System.out.println("Turno " + turno);
        combate.jugarJugador(tecla);
        combate.jugarEnemigos();
        combate.eliminarEnemigosDerrotados();
        mostrarEstado();
        combate.mostrarCampo();
        verificarFinDelJuego();
        turno++;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    private void generarEnemigos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            enemigos[i] = new Enemigo("NaveAlienigena", 50, 10, 2, 1 + i * 3);
        }
    }

    private void verificarFinDelJuego() {
        if (!jugador.estaVivo()) {
            juegoTerminado = true;
            System.out.println("¡Has perdido! " + jugador.getNombre() + " ha sido derrotado.");
            System.out.println("Puntaje final: " + jugador.getPuntaje());
        } else if (!hayEnemigosVivos()) {
            juegoTerminado = true;
            System.out.println("¡Has ganado! " + jugador.getNombre() + " ha derrotado a todos los enemigos.");
            System.out.println("Puntaje final: " + jugador.getPuntaje());
        }
    }

    private boolean hayEnemigosVivos() {
        for (Enemigo e : enemigos) {
            if (e != null && e.estaVivo()) return true;
        }
        return false;
    }

    public void mostrarEstado() {
        System.out.println("Jugador: " + jugador.getNombre() + " - Salud: " + jugador.getSalud() +
                " - Ataque: " + jugador.getAtaque() + " - Puntaje: " + jugador.getPuntaje());
        System.out.println("Enemigos restantes:");
        for (Enemigo e : enemigos) {
            if (e != null && e.estaVivo()) {
                e.mostrarEstado();
            }
        }
    }
}
