/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 *
 * @author Koala
 */
public abstract class MovingObject extends GameObject{
    
    
    protected Vector2D velocity;
    protected AffineTransform at;// Nos va a permitir rotar la nave
    protected double angle;
    protected double maxVel;
    
    
    public MovingObject(Vector2D position,Vector2D velocity,double maxVel, BufferedImage texture) {
        super(position, texture);

        this.velocity= velocity;
        this.maxVel= maxVel;
        angle=0;
        
    }
    
}
