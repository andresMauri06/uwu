package battlespace;

import java.util.Arrays;

public class CampodeBatalla {
    private final int filas = 15, columnas = 30;
    private char[][] campo = new char[filas][columnas];
    private Jugador jugador;
    private Enemigo[] enemigos = new Enemigo[5];
    private Proyectil[] proyectilesJugador = new Proyectil[20];
    private Proyectil[] proyectilesEnemigos = new Proyectil[30];

    public CampodeBatalla() {
        jugador = new Jugador(filas - 1, columnas / 2);
        for (int i = 0; i < enemigos.length; i++) {
            enemigos[i] = new Enemigo(2, 5 + i * 6);
        }
    }

    public void mostrarCampo() {
        limpiarCampo();
        campo[jugador.getFila()][jugador.getColumna()] = jugador.getSimbolo();

        for (Enemigo e : enemigos) {
            if (e != null && e.estaViva()) {
                campo[e.getFila()][e.getColumna()] = e.getSimbolo();
            }
        }

        for (Proyectil p : proyectilesJugador) {
            if (p != null && p.estaActivo()) {
                campo[p.getFila()][p.getColumna()] = '*';
            }
        }

        for (Proyectil p : proyectilesEnemigos) {
            if (p != null && p.estaActivo()) {
                campo[p.getFila()][p.getColumna()] = '.';
            }
        }

        for (char[] fila : campo) {
            System.out.println(new String(fila));
        }
    }

    private void limpiarCampo() {
        for (int i = 0; i < filas; i++) {
            Arrays.fill(campo[i], ' ');
        }
    }

    public void moverJugador(char tecla) {
    switch (tecla) {
        case 'a': 
            if (jugador.getColumna() > 0)
                jugador.mover(jugador.getFila(), jugador.getColumna() - 1);
            break;
        case 'd': 
            if (jugador.getColumna() < columnas - 1)
                jugador.mover(jugador.getFila(), jugador.getColumna() + 1);
            break;
        case 'w': 
            if (jugador.getFila() > 0)
                jugador.mover(jugador.getFila() - 1, jugador.getColumna());
            break;
        case 's': 
            if (jugador.getFila() < filas - 1)
                jugador.mover(jugador.getFila() + 1, jugador.getColumna());
            break;
        case 'e': 
            dispararJugador();
            break;
        }
    }
    
    public void dispararJugador() {
        for (int i = 0; i < proyectilesJugador.length; i++) {
            if (proyectilesJugador[i] == null || !proyectilesJugador[i].estaActivo()) {
                proyectilesJugador[i] = new Proyectil(jugador.getFila() - 1, jugador.getColumna(), jugador.getAtaque(), -1);
                break;
            }
        }
    }

    public void moverProyectiles() {
        for (Proyectil p : proyectilesJugador) {
            if (p != null && p.estaActivo()) {
                p.mover();
                for (Enemigo e : enemigos) {
                    if (e != null && e.estaViva()
                        && p.getFila() == e.getFila()
                        && p.getColumna() == e.getColumna()) {
                        e.recibirDaño(p.getDaño());
                        p.desactivar();
                        break;
                    }
                }
            }
        }

        for (Proyectil p : proyectilesEnemigos) {
            if (p != null && p.estaActivo()) {
                p.mover();
                if (p.getFila() == jugador.getFila() && p.getColumna() == jugador.getColumna()) {
                    jugador.recibirDaño(p.getDaño());
                    p.desactivar();
                }
            }
        }
    }

    public void enemigosActuan() {
        for (Enemigo e : enemigos) {
            if (e != null && e.estaViva()) {
                e.moverAleatorio(filas, columnas);
                if (Math.random() < 0.7) {
                    for (int i = 0; i < proyectilesEnemigos.length; i++) {
                        if (proyectilesEnemigos[i] == null || !proyectilesEnemigos[i].estaActivo()) {
                            proyectilesEnemigos[i] = new Proyectil(e.getFila() + 1, e.getColumna(), e.getAtaque(), 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean juegoTerminado() {
        if (!jugador.estaViva()) return true;
        for (Enemigo e : enemigos) {
            if (e != null && e.estaViva()) return false;
        }
        return true;
    }

    public boolean jugadorGano() {
        return jugador.estaViva() && !hayEnemigosVivos();
    }

    private boolean hayEnemigosVivos() {
        for (Enemigo e : enemigos) {
            if (e != null && e.estaViva()) return true;
        }
        return false;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
