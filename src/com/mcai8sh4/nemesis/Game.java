package com.mcai8sh4.nemesis;

import com.mcai8sh4.nemesis.entity.mob.Player;
import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;
import com.mcai8sh4.nemesis.input.Keyboard;
import com.mcai8sh4.nemesis.input.Mouse;
import com.mcai8sh4.nemesis.level.Level;
import com.mcai8sh4.nemesis.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private static int width = 300;
    private static int height = width / 16 * 9;
    private static int scale = 3;

    public static String title = "Nemesis";

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();


    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate player_spawn = new TileCoordinate(27, 4);
        player = new Player(player_spawn.x(), player_spawn.y(), key);
        player.init(level);
        addKeyListener(key);
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }


    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0.0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                System.out.println(updates + "ups, " + frames + "fps");
                frame.setTitle(title + " || preAlpha test 0.01");
                updates = 0;
                frames = 0;
                Screen.msg = "";
                Screen.msg_1 = "";
            }
        }
        stop();
    }

    public void update() {
        key.update();
        player.update();
        level.update();

        if (key.quit) System.exit(0);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }


        screen.clear();
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);


        // DELETE ME JUST A TEST
//        Sprite sprite = new Sprite(2, 2, 0xffffff);
//        Random random = new Random();
//        for (int i = 0; i < 100; i++) {
//            int x = random.nextInt(25);
//            int y = random.nextInt(25);
//            screen.renderSprite(365 + x, 18 + y, sprite, true);
//        }

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0,

                           getWidth(), getHeight

                                               (), null);
        g.setColor(Color.CYAN);
        g.setFont(new

                          Font("Verdana", 0, 12)

        );
        g.drawString("X: " + player.x + ", Y: " + player.y + Screen.msg_1, 10, 15);
        g.drawString(Screen.msg, 10, 32);
        g.drawRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
        if (Mouse.getButton() != -1) g.drawString("Mouse : " + Mouse.getButton(), 10, 49);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}



