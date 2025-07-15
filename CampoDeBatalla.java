public class CampoDeBatalla {
    private char[][] campo;  // Matriz que representa el campo de batalla
    private int filas;       // Número de filas
    private int columnas;    // Número de columnas
    private int jugadorX;    // Posición X del jugador
    private int jugadorY;    // Posición Y del jugador
    private Disparo disparo; // El disparo del jugador

    public CampoDeBatalla(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.campo = new char[filas][columnas];
        inicializarCampo();
    }

    // Inicializa el campo de batalla con espacios vacíos y coloca al jugador
    private void inicializarCampo() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                campo[i][j] = '.';  // '.' representa un espacio vacío
            }
        }
        // Coloca al jugador en una posición inicial
        jugadorX = filas / 2;
        jugadorY = columnas / 2;
        campo[jugadorX][jugadorY] = 'J';  // 'J' representa al jugador

        // Coloca algunos enemigos en posiciones aleatorias
        colocarEnemigos(3);  // Coloca 3 enemigos
    }