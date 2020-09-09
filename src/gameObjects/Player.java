
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
    
    public Player(Vector2D position, Vector2D velocity,double maxVel, BufferedImage texture) {
        super(position, velocity, maxVel, texture);
        heading= new Vector2D(0,1);
        acceleration = new Vector2D();
        
    }

   
    @Override
    public void update() {    
        
        if(KeyBoard.RIGHT){
            angle+= DELTAANGLE;
        }
        if(KeyBoard.LEFT){
            angle-= DELTAANGLE;
        }
        //ACELERADO Y FRENADO
        if(KeyBoard.UP){
            acceleration = heading.scale(ACC);
            System.out.println("aceleracion: "+acceleration);
        }else{
            if(velocity.getMagnitud() !=0)
            {
                acceleration= (velocity.scale(-1).normalize()).scale(ACC/2);
            }
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
        at=AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle,Assets.player.getWidth()/2, Assets.player.getHeight()/2);
        g2d.drawImage(Assets.player, at, null);
        
    }
    
}
