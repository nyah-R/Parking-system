package parkingcentrico24_7;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class MiniJuego extends Canvas implements Runnable, KeyListener {

    private final int WIDTH = 800;
    private final int HEIGHT = 400;

    private final int ANCHO_JUGADOR = 100;
    private final int ALTO_JUGADOR = 172;
    private final int ANCHO_OBSTACULO = 82;
    private final int ALTO_OBSTACULO = 82;

    private Image imgJugador;
    private Image imgObstaculo;

    private JFrame frame;
    private Thread gameThread;
    private boolean running = false;

    private Rectangle jugador;
    private ArrayList<Rectangle> obstaculos = new ArrayList<>();
    private Random rand = new Random();

    private boolean saltando = false;
    private int velocidadY = 0;
    private final int GRAVEDAD = 1;

    private int puntuacion = 0;
    private int velocidadJuego = 8;

    // Para mensajes temporales en pantalla
    private String mensajeTemporal = "";
    private long tiempoMensaje = 0;

    public MiniJuego() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame = new JFrame("¡Corre contra los exámenes!");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.addKeyListener(this);
        this.setFocusable(true);

        cargarImagenes();

        jugador = new Rectangle(100, HEIGHT - ALTO_JUGADOR - 40, ANCHO_JUGADOR, ALTO_JUGADOR);
        agregarObstaculo();
    }

    private void cargarImagenes() {
        imgJugador = new ImageIcon(getClass().getResource("Personaje.png")).getImage();
        imgJugador = imgJugador.getScaledInstance(ANCHO_JUGADOR, ALTO_JUGADOR, Image.SCALE_SMOOTH);

        imgObstaculo = new ImageIcon(getClass().getResource("ObstaculoP2.png")).getImage();
        imgObstaculo = imgObstaculo.getScaledInstance(ANCHO_OBSTACULO, ALTO_OBSTACULO, Image.SCALE_SMOOTH);
    }

    public void iniciar() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void agregarObstaculo() {
        int x = WIDTH + rand.nextInt(300);
        int y = HEIGHT - ALTO_OBSTACULO - 40;
        obstaculos.add(new Rectangle(x, y, ANCHO_OBSTACULO, ALTO_OBSTACULO));
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();

        long lastTime = System.nanoTime();
        double nsPerFrame = 1e9 / 60.0;

        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;

            while (delta >= 1) {
                actualizar();
                delta--;
            }

            Graphics g = bs.getDrawGraphics();
            dibujar(g);
            g.dispose();
            bs.show();

            try {
                Thread.sleep(2);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void actualizar() {
        // Salto
        if (saltando) {
            jugador.y += velocidadY;
            velocidadY += GRAVEDAD;

            if (jugador.y >= HEIGHT - ALTO_JUGADOR - 40) {
                jugador.y = HEIGHT - ALTO_JUGADOR - 40;
                saltando = false;
                velocidadY = 0;
            }
        }

        // Mover obstáculos
        for (int i = obstaculos.size() - 1; i >= 0; i--) {
            Rectangle obs = obstaculos.get(i);
            obs.x -= velocidadJuego;

            if (obs.x + obs.width < 0) {
                obstaculos.remove(i);
                puntuacion++;

                // Mostrar mensaje cada 10 puntos
                if (puntuacion % 10 == 0) {
                    mensajeTemporal = "¡Wow, " + puntuacion + " exámenes esquivados!";
                    tiempoMensaje = System.currentTimeMillis();
                }

                if (puntuacion % 5 == 0 && velocidadJuego < 20) {
                    velocidadJuego++;
                    mensajeTemporal = "¡Velocidad aumentada!";
                    tiempoMensaje = System.currentTimeMillis();
                }

                agregarObstaculo();
                continue;
            }

            if (obs.intersects(jugador)) {
                running = false;
                JOptionPane.showMessageDialog(this, "¡Uy! Te atrapó el examen. Puntaje final: " + puntuacion, "Juego terminado", JOptionPane.WARNING_MESSAGE);
                frame.dispose();
            }
        }

        // Limpiar mensaje temporal después de 2 segundos
        if (!mensajeTemporal.isEmpty() && System.currentTimeMillis() - tiempoMensaje > 2000) {
            mensajeTemporal = "";
        }
    }

    private String textoPuntaje(int puntos) {
        if (puntos == 1) {
            return " examen evadido!";
        } else {
            return " examenes evadidos!";
        }
    }

    private void dibujar(Graphics g) {
        // Fondo
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Jugador
        g.drawImage(imgJugador, jugador.x, jugador.y, jugador.width, jugador.height, this);

        // Obstáculos
        for (Rectangle obs : obstaculos) {
            g.drawImage(imgObstaculo, obs.x, obs.y, obs.width, obs.height, this);
        }

        // Suelo
        g.setColor(Color.BLACK);
        g.fillRect(0, HEIGHT - 40, WIDTH, 2);

        // Puntuación
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.BLACK);
        g.drawString("¡Llevás " + puntuacion + textoPuntaje(puntuacion), 10, 20);

        // Mensaje temporal
        if (!mensajeTemporal.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.RED);
            g.drawString(mensajeTemporal, WIDTH / 2 - 150, 50);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !saltando) {
            saltando = true;
            velocidadY = -20;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
