package battlespace;

public class Renderizador {
    private static boolean juegoIniciado = false;
    private static final String ANSI_GUARDAR_CURSOR = "\033[s";
    private static final String ANSI_RESTAURAR_CURSOR = "\033[u";
    private static final String ANSI_OCULTAR_CURSOR = "\033[?25l";
    private static final String ANSI_MOSTRAR_CURSOR = "\033[?25h";

    public static void renderizarJuego(char[][] tablero, int puntuacion, int vidas, int ancho) {
        if (!juegoIniciado) {
            limpiarConsola();
            System.out.print(ANSI_OCULTAR_CURSOR);
            juegoIniciado = true;
        }

        StringBuilder pantalla = new StringBuilder();

        // Línea 1: Puntuación y vidas
        pantalla.append("PUNTUACIÓN: ").append(puntuacion).append("   VIDAS: ");
        for (int i = 0; i < vidas; i++) {
            pantalla.append("♥");
        }

        for (int i = 0; i < 20; i++) {
            pantalla.append(" ");
        }
        pantalla.append("\n");

        pantalla.append("-".repeat(ancho)).append("\n");

        for (char[] fila : tablero) {
            pantalla.append(new String(fila)).append("\n");
        }

        pantalla.append("-".repeat(ancho)).append("\n");

        pantalla.append("Usa A/D para moverte, ESPACIO para disparar. Pulsa Q para salir.\n");

        System.out.print("\033[H");
        System.out.print(pantalla.toString());
        System.out.flush();
    }

    public static void limpiarConsola() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    public static void mostrarJuegoTerminado(int puntuacionFinal) {
        System.out.print(ANSI_MOSTRAR_CURSOR);
        System.out.println("\n💀 JUEGO TERMINADO 💀 Puntuación Final: " + puntuacionFinal);
        System.out.println("¡Gracias por jugar!");
    }
}
