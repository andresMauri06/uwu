import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class BattleSpaceCampo {
    static final int FILAS = 10;
    static final int COLUMNAS = 10;
    static char[][] campo = new char[FILAS][COLUMNAS];

    static Jugador jugador;
    static ArrayList<Enemigo> enemigos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarCampo();
        inicializarJugadorYEnemigos();
        mostrarCampo();

        // Bucle de juego
        while (!enemigos.isEmpty() && jugador.vida > 0) {
            jugarJugador();
            jugarEnemigos();
            eliminarEnemigosDerrotados();
            mostrarCampo();
        }

        System.out.println(jugador.vida > 0 ? "¡Has ganado!" : "¡Game Over!");
    }

    public static void inicializarCampo() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                campo[i][j] = '~';
            }
        }
    }

    public static void inicializarJugadorYEnemigos() {
        jugador = new Jugador(0, 0, 10);
        campo[jugador.x][jugador.y] = 'J';

        enemigos.add(new Enemigo(5, 5, 5));
        enemigos.add(new Enemigo(7, 2, 3));
        

        for (Enemigo e : enemigos) {
            campo[e.x][e.y] = 'E';
        }
    }

    public static void mostrarCampo() {
        System.out.println("\n  Campo de Batalla Espacial");
        System.out.print("  ");
        for (int j = 0; j < COLUMNAS; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int i = 0; i < FILAS; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(campo[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Jugador vida: " + jugador.vida);
    }

    public static void jugarJugador() {
        System.out.println("Tu turno. Ingresa dirección (w/a/s/d): ");
        char mov = scanner.next().charAt(0);
        int nx = jugador.x, ny = jugador.y;

        switch (mov) {
            case 'w': nx--; break;
            case 's': nx++; break;
            case 'a': ny--; break;
            case 'd': ny++; break;
            default:
                System.out.println("Movimiento inválido");
                return;
        }

        if (nx >= 0 && nx < FILAS && ny >= 0 && ny < COLUMNAS) {
            if (campo[nx][ny] == 'E') {
                Enemigo enemigo = encontrarEnemigo(nx, ny);
                if (enemigo != null) {
                    enemigo.vida -= 3;
                    System.out.println("¡Has atacado a un enemigo!");
                }
            }

            campo[jugador.x][jugador.y] = '~';
            jugador.x = nx;
            jugador.y = ny;
            campo[jugador.x][jugador.y] = 'J';
        } else {
            System.out.println("Movimiento fuera de los límites.");
        }
    }

    public static void jugarEnemigos() {
        for (Enemigo e : enemigos) {
            if (Math.abs(e.x - jugador.x) + Math.abs(e.y - jugador.y) <= 1) {
                jugador.vida -= 2;
                System.out.println("¡Un enemigo te ha atacado!");
            }
        }
    }

    public static void eliminarEnemigosDerrotados() {
        Iterator<Enemigo> it = enemigos.iterator();
        while (it.hasNext()) {
            Enemigo e = it.next();
            if (e.vida <= 0) {
                campo[e.x][e.y] = '~';
                it.remove();
                System.out.println("¡Enemigo derrotado!");
            }
        }
    }

    public static Enemigo encontrarEnemigo(int x, int y) {
        for (Enemigo e : enemigos) {
            if (e.x == x && e.y == y) return e;
        }
        return null;
    }
}

