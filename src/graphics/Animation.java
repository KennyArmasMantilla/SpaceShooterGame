package graphics;

import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 *
 * @author Koala
 * Se usara para almacenar todo tipo de informacion sobre cualquier tipo de
 * animacion en el juego
 */




public class Animation {
    
    //Numero total de fotogramas
    private BufferedImage[] frames;
     
    //Velocidad con la que se va a cambiar los fotogramas
    private int velocity;
    
    //Index del fotograma actual que se dibuja
    private int index;
    
    
    //para saber si la animacion esta corriendo
    private boolean running;
    
    //Vector para representar la posision donde se a a dibujar
    private Vector2D position;
    
    //variables auxiliares para controlar el tiempo.
    private long time,lastTime;
     
    public Animation(BufferedImage[] frames, int velocity, Vector2D position){
        this.frames= frames;
        this.velocity=velocity;
        this.position= position;
        index=0;
        running=true;
        time=0;
        lastTime= System.currentTimeMillis();
    }
    
    public void update(){
        time +=System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        //Si es mayor cambia el fotograma
        if(time > velocity){
            time = 0;
            index++;
            //Si el indice sale del borde del tamaño del fotograma
            if(index >= frames.length){
                running = false;
            }
        }
    }
    
    public boolean  isRunning(){
        return running;
    }
    
    public Vector2D getPosition(){
        return position;
    }
    
    //retorna el fotograma actual del arreglo de fotogramas
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
}
