package main;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Koala
 */
public class Window extends JFrame implements Runnable{

    public static final int WIDTH=800, HEIGHT = 600;
    private Canvas canvas;
    
    //Hilo principal del juego, para la logica del juego.
    private Thread thread;
    private boolean running =false;
    
    
    //Para dibujar
    private BufferStrategy bs;
    private Graphics g;
    
    public Window(){
        setTitle("Space Game Shooter. Kenny Armas");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//La ventana cierre cuando presionamos en X
        setResizable(false);//Para no redimensionar la ventana.
        setLocationRelativeTo(null);
        setVisible(true);
        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        canvas.setFocusable(true);//Permite recibir entradas por parte del teclado.
        
        add(canvas);

    }


    public static void main(String[] args) {
        // TODO code application logic here
        new Window().start();
    }

    //mover
    int x=0;
    private void update(){
        x++;
    }
    
    private void draw(){
        bs = canvas.getBufferStrategy();
        
        if(bs == null){
            canvas.createBufferStrategy(5);//El 3 es un numero de buffers.
            return ;
        }
        g = bs.getDrawGraphics();
        //-------------------------Inicio del dibujo------------------------
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        g.drawRect(x,0, 100, 100);
        
        
        //-------------------------Fin del dibujo---------------------------
        g.dispose();
        bs.show();
    }
    
    //Generado por runnable.
    @Override
    public void run() {
//        System.out.println(running);
        while(running){
            update();
            draw();
        }
        
        stop();
    }
    
    //Metodos para uniciar y detener el hilo principal
    private void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    private void stop(){
        try {
            thread.join();
            running=false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
