/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.Text;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author Koala
 */
public class Message {
    
    private GameState gameState;
    private float alpha;//0 Indicara la transparencia 
    private String text;
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean fade;//efecto de visible a invisible
    private Font font;//
    private final float deltaAlpha = 0.01f;//Mas sea el valor mas rapido la transicion

    public Message(Vector2D position, boolean fade, String text, Color color, boolean center, Font font, GameState gameState){
    
        this.font = font;
        this.gameState = gameState;
        this.text = text;
        this.position = position;
        this.fade = fade;
        this.color=color;
        this.center = center;
        
        if(fade){
            alpha=1;
        }
        else{
            alpha=0;
        }
    }

    public void draw (Graphics2D g2d){
        //transparencia
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
    
        Text.drawText(g2d, text, position, center, color, font);
        //
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));        
    
        position.setY(position.getY()-1);
        
        if(fade){
            alpha -= deltaAlpha;  
        }
        else{
            alpha+=deltaAlpha;
        }
        
        if(fade && alpha < 0){
            gameState.getMessages().remove(this);
        }
        
        if(!fade && alpha > 1){
            fade = true;
            alpha = 1;
        }
        
    }
    
     
    
}
