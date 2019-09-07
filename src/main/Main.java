package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable{

    public static final String title = "MineSweeper";

    private JFrame frame;
    private Thread thread;
    private Boolean running = false;

    private MineField mineField;
    private Input input;

    private Runtime runtime = Runtime.getRuntime();

    public Main() {
        frame = new JFrame();

        mineField = new MineField(10 ,10);

        input = new Input(mineField);

        addMouseListener(input);
    }

    private void render() {

        BufferStrategy bufferStrategy = getBufferStrategy();

        if (bufferStrategy == null)
        {
            createBufferStrategy(2);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        mineField.render(graphics);

        graphics.dispose();
        bufferStrategy.show();


    }

    private void update() {
        mineField.update();
    }

    public synchronized void  start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();

        mineField.create();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(Exception e) {}
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            long mb = (runtime.totalMemory() - runtime.freeMemory()) /1024 /1024;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + " | " + "FPS:" + frames + "  Updates:" + updates
                        + " || Memory: " + mb );
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.frame.setTitle(title);
        main.frame.setResizable(false);
        main.frame.add(main);
        main.frame.pack();
        main.frame.setSize(515, 530);
        main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.frame.setLocationRelativeTo(null);
        main.frame.setVisible(true);

        main.start();
    }
}
