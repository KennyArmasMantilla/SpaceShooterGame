
package gameObjects;

import graphics.Assets;
import input.KeyBoard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import math.Vector2D;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import states.GameState;

/**
 *
 * @author Koala
 */
public class Player extends MovingObject{
    
    private Vector2D heading; //Representa hacia donde esta mirando la nave
    private Vector2D acceleration;//Acelerar la nave.... Viene a ser el acmbio con respecto al tiempo.
    private final double ACC=0.08;//Representa cuanto queremos que acelere la nave
    private final double DELTAANGLE =0.1;
    private Window window;
    private GameState gameState;
    
    //effects
    private boolean accelerating = false;
    
    //Velocidad de las balas
    private long time, lastTime;
    
    public Player(Vector2D position, Vector2D velocity,double maxVel, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture);
        this.gameState=gameState;
        heading= new Vector2D(0,1);
        acceleration = new Vector2D();
        time=0;
        lastTime=System.currentTimeMillis();
    }

   
    @Override
    public void update() {    
        
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(KeyBoard.SHOOT && time > 100){
            gameState.getMovingObjects().add(0,new Laser
                (getCenter().add(heading.scale(width)),
                        heading, 10, angle, Assets.laserRed));
                    
            time=0;
        }
        
        
        if(KeyBoard.RIGHT){
            angle+= DELTAANGLE;
        }
        if(KeyBoard.LEFT){
            angle-= DELTAANGLE;
        }
        //ACELERADO Y FRENADO
        if(KeyBoard.UP){
            acceleration = heading.scale(ACC);
            accelerating=true;
            System.out.println("aceleracion: "+acceleration);
        }else{
            if(velocity.getMagnitud() !=0)
            {
                acceleration= (velocity.scale(-1).normalize()).scale(ACC/2);
            }
            accelerating=false;
        }
        
        //lETRAS
/*        if(KeyBoard.D){
            position.setX(position.getX()+2);
        }
        if(KeyBoard.A){
            position.setX(position.getX()-2);
        }
        if(KeyBoard.W){
            position.setY(position.getY()-2);
        }
        if(KeyBoard.S){
            position.setY(position.getY()+2);
        }*/
        
        
        velocity = velocity.add(acceleration);
        velocity.limit(maxVel);
        
        heading = heading.setDirection(angle - Math.PI/2);//MENOS MATH.PI/2 YA QUE LA IMAGEN ESTA EN 90°

        //Para que no Salga del cuadro de dibujo.
        if(position.getX() > 743.1) {
            System.out.println("Posicion X: "+position.getX());
            position.setX(743);
        }
        else 
        {
            if(position.getX() < 7){
                System.out.println("Posicion X: "+position.getX());
                position.setX(8);
            }
            else{
                if (position.getY()>525.1) {
                    System.out.println("Posicion Y: "+position.getY());
                    position.setY(525);
                }
                else
                {
                    if (position.getY()<10) {
                        System.out.println("Posicion Y: "+position.getY());
                        position.setY(11);
                    }
                    else{
                       position = position.add(velocity);                
                    }
                }
            }
        }
       
               
        
        System.out.println("Ancho de la ventana: "+742);
        System.out.println("Posicion X:"+position.getX());
        System.out.println("Posicion Y:"+position.getY());
        System.out.println("Angulo: "+angle);             
            
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        //effects
        AffineTransform at1= AffineTransform.getTranslateInstance(position.getX()+ width/2 + 5, position.getY()+height/2 + 10);
        AffineTransform at2= AffineTransform.getTranslateInstance(position.getX()+5, position.getY()+height/2+10);

        at1.rotate(angle, -5,-10);
        at2.rotate(angle,width/2-5,-10);
        
        if(accelerating)
        {
            g2d.drawImage(Assets.speed, at1, null);
            g2d.drawImage(Assets.speed, at2, null);
        }
        
        
        
        
        at=AffineTransform.getTranslateInstance(position.getX(), position.getY());
        
        at.rotate(angle,width/2, height/2);
        
        g2d.drawImage(Assets.player, at, null);
        
    }
    
    public Vector2D getCenter(){
        
        return new Vector2D(position.getX()+ width/2, position.getY()+height/2);
    
    }
    
    
    
}
