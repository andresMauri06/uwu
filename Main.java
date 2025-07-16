package battlespace;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Iniciando Battlespace...");
        System.out.println("Controles: A/D para moverse, ESPACIO para disparar, Q para salir");
        System.out.println("¡Presiona ENTER para comenzar!");

        try {
            System.in.read();
        } catch (Exception e) {
            // Ignorar
        }

        Juego juego = new Juego(5); // Crear el juego con 5 enemigos
        juego.iniciarJuego();

        while (!juego.juegoTerminado) {
            juego.ejecutarTurno();
        }
    }
}
