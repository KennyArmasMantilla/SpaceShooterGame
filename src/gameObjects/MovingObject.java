/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author Koala
 */
public abstract class MovingObject extends GameObject{
    
    
    protected Vector2D velocity;
    protected AffineTransform at;// Nos va a permitir rotar la nave
    protected double angle;
    protected double maxVel;
    
    protected int width;
    protected int height;
    protected GameState gameState;
    
    public MovingObject(Vector2D position,Vector2D velocity,double maxVel, BufferedImage texture, GameState gameState) {
        super(position, texture);

        this.velocity= velocity;
        this.maxVel= maxVel;
        this.gameState = gameState;
        width = texture.getWidth();
        height=texture.getHeight();
        angle=0;
        
    }
    
    //Logica para las coliciones
    protected void collidesWith(){
        //Referencia del Arreglo de objetos movibles
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();
        
        for(int i=0; i< movingObjects.size(); i++){
            
            MovingObject m = movingObjects.get(i);
            
            //Para evitar colisicion conmigo mismo
            if(m.equals(this)){
                continue;
            }
            
            //Distancia entre el objeto
            double distance = m.getCenter().subtracc(getCenter()).getMagnitud();
       
            if(distance<m.width/2 +width/2 && movingObjects.contains(this)){
                objectCollision(m, this);
            }
        
        
        }
     }
    
    //no va a ser el caso que siempre se destruya
    //que debe hacer en caso de los objetos colisionen
    private void objectCollision(MovingObject a, MovingObject b){
        //Preguntamos si un objeto es el el jugador
        if(a instanceof Player && ((Player)a).isSpawning()){
            return;
        }
        
        if(b instanceof Player && ((Player)b).isSpawning()){
            return;
        }
        
        //siempre y cuando ninguno sea meteoro lo eliminamos
        if(!(a instanceof Meteor && b instanceof Meteor)){
            //para las explosionces
            gameState.playExplosion(getCenter());
            
            a.Destroy();
            b.Destroy();
        }
    }
    
    
    
    //elimina el objeto del arreglo de objetos movibles
    protected void Destroy(){
        gameState.getMovingObjects().remove(this);
    }
    
    //Para todos poder obtener el centro.
    protected Vector2D getCenter(){
        
        return new Vector2D(position.getX()+ width/2, position.getY()+height/2);
    
    }
    
    
}
