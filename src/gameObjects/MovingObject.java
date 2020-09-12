/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
    
}
