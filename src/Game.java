import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    public static int WIDTH = 480, HEIGHT = 480;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini Zelda"); // Seta título da janela
        frame.pack();
        frame.setLocationRelativeTo(null); // Reset da posição da janela

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// finalizar processo Java quando JFrame for finalizado
        frame.setVisible(true);

        new Thread(game).start();

    }

    public void run() {
        while (true) { // loop infinito do jogo
            System.out.println("Calling game looping!");
        }
    }
}
