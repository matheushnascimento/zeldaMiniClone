import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 640, HEIGHT = 480, SCALE = 3;
    public Player player;
    public World world;
    public List<Enemy> enemy = new ArrayList<Enemy>();

    public Game() {
        int min = 1, max = 5;
        int posX = ThreadLocalRandom.current().nextInt(32, 640 - 64);
        int posY = ThreadLocalRandom.current().nextInt(32, 480 - 64);
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        new Spritesheet();

        player = new Player(32, 32);
        for (int i = 0; i < randomNum; i++) {
            enemy.add(new Enemy(posX, posY));
            posX = ThreadLocalRandom.current().nextInt(32, 640 - 64);
            posY = ThreadLocalRandom.current().nextInt(32, 480 - 64);

        }
        world = new World();
    }

    public void tick() {
        player.tick();

        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0, 137, 12));
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        player.render(g);
        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).render(g);
        }
        world.render(g);

        bs.show();
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
            tick();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            player.shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }

}
