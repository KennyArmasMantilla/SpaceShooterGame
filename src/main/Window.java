package main;


import java.awt.Canvas;
import java.awt.Color;
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
    
    //Fotogramas por segundo
    private final int FPS = 60;
    private double TARGETTIME = 1000000000/FPS; //tiempo requerido para aumentar un fotograma, tiempo objtivo en nano segundos
    private double delta = 0; //Almacena temporalmente el tiempo que vaya pasando.
    private int AVERGAREFPS=FPS; //Fotogramas por segundo promedio 
    
    
    
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
            canvas.createBufferStrategy(3);//El 3 es un numero de buffers.
            return ;
        }
        g = bs.getDrawGraphics();
        //-------------------------Inicio del dibujo------------------------
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        g.drawRect(x,0, 100, 100);
        g.setColor(Color.BLACK);
        g.drawString(""+AVERGAREFPS, 10, 10);
        
        //-------------------------Fin del dibujo---------------------------
        g.dispose();
        bs.show();
    }
    
    //Generado por runnable.
    @Override
    public void run() {
        //System.out.println(running);
        //Implemando las variables para restingrir el ciclo y que corra a 60fps
        long now =0;//Registro del tiempo.
        long lastTime = System.nanoTime();//Dar la hora actual del sistemas en nanosegundos
        
        //Pasa ver por pantalla los fotogramas, tiempo de ejecucion.
        int frames = 0;
        long time=0;
        
        
    
        while(running){
            
            now = System.nanoTime();
            delta+=(now-lastTime)/TARGETTIME;
            time+=(now-lastTime);
            lastTime= now;
            if(delta>=1){
                update();
                draw();
                delta--;
                frames ++;
                //System.out.println(frames);
            }
            
            if(time>=1000000000){
                AVERGAREFPS = frames;
                frames=0;
                time=0;
            }
            
            
            /*update();
            draw();*/
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
