package battlespace;

import java.io.IOException;

public class InputHandler implements Runnable {
    private Game game;
    private boolean running = true;

    public void setGame(Game game) {
        this.game = game;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try {
         
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
               
                while (running) {
                    if (System.in.available() > 0) {
                        int key = System.in.read();
                        if (key == -1) break;
                        processInput((char) key);
                    }
                    Thread.sleep(10); 
                }
            } else {
                
                while (running) {
                    if (System.in.available() > 0) {
                        int key = System.in.read();
                        if (key == -1) break;
                        processInput((char) key);
                    }
                    Thread.sleep(10);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processInput(char c) {
        switch (Character.toUpperCase(c)) {
            case 'A':
                game.movePlayerLeft();
                break;
            case 'D':
                game.movePlayerRight();
                break;
            case ' ':
                game.fireBullet();
                break;
            case 'Q':
                game.quit();
                break;
        }
    }
}
