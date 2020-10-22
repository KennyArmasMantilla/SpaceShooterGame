package gameObjects;

import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author Koala
 */
public class Meteor extends MovingObject{
    
    //Etiqueta para saber si es grande, mediano...
    private Size size;
    
    

    public Meteor(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Size size) {
        super(position, velocity, maxVel, texture, gameState);
        this.size=size;
        
        //aumentando velocidad del meteoro
        this.velocity = velocity.scale(maxVel);
    }

    @Override
    public void update() {
        position = position.add(velocity);
        
        //Para que no Salga del cuadro de dibujo.
        if(position.getX()>Constants.WIDTH){
            position.setX(-width);
        }
        if(position.getY()>Constants.HEIGHT){
            position.setY(-height);
        }
        
        if(position.getX()< -width){
            position.setX(Constants.WIDTH);
        }
        if(position.getY()< -height){
            position.setY(Constants.HEIGHT);
        }
        
        
        /*if(position.getX() > 743.1) {
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
        }*/
        
        angle+= Constants.DELTAANGLE;
        
    }
    
    @Override
    public void Destroy(){
        gameState.divideMeteor(this);
        gameState.addScore(Constants.METEOR_SCORE);
        super.Destroy();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d =(Graphics2D)g;
        
        at=AffineTransform.getTranslateInstance(position.getX(), position.getY());
        
        at.rotate(angle,width/2, height/2);
        
        g2d.drawImage(texture, at, null);
        
    }

    public Size getSize(){
        return size;
    }
    
}
