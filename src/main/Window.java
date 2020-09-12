package main;


import gameObjects.Constants;
import graphics.Assets;
import input.KeyBoard;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import states.GameState;
/**
 *
 * @author Koala
 */
public class Window extends JFrame implements Runnable{

   
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
    
    //Gamestate
    private GameState gameState;
    
    
    //Ingreso por teclado KeyBoard
    private KeyBoard keyBoard;
    
    public Window(){
        
        //Inizializacion
        setTitle("Space Game Shooter. Kenny Armas");
        setSize(Constants.WIDTH,Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//La ventana cierre cuando presionamos en X
        setResizable(false);//Para no redimensionar la ventana.
        setLocationRelativeTo(null);
        setVisible(true);
        canvas = new Canvas();
        keyBoard = new KeyBoard();

        canvas.setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        canvas.setFocusable(true);//Permite recibir entradas por parte del teclado.
        
        add(canvas);
        canvas.addKeyListener(keyBoard);//Agregand el keyboard al canvas.

    }


    public static void main(String[] args) {
        // TODO code application logic here
        new Window().start();
    }

    //mover
    private void update(){
        keyBoard.update();
        gameState.update();
    }
    
    private void draw(){
        bs = canvas.getBufferStrategy();
        
        if(bs == null){
            canvas.createBufferStrategy(3);//El 3 es un numero de buffers.
            return ;
        }
        g = bs.getDrawGraphics();
        //-------------------------Inicio del dibujo------------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
//        g.drawImage(Assets.player, 100, 100, null);
        
        gameState.draw(g);
        
        g.setColor(Color.WHITE);
        g.drawString(""+AVERGAREFPS, 10, 10);
        
        //-------------------------Fin del dibujo---------------------------
        g.dispose();
        bs.show();
    }
    //para inicializar los componentes que necesitemos
    private void init(){
        Assets.init();
        gameState = new GameState();
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
        
        
        init();
        
    
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
