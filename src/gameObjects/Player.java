
package gameObjects;

import input.KeyBoard;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import math.Vector2D;
import java.awt.event.KeyListener;

/**
 *
 * @author Koala
 */
public class Player extends GameObject{

    public Player(Vector2D position, BufferedImage texture) {
        super(position, texture);
    }

    @Override
    public void update() {

           
        if(KeyBoard.RIGHT){
            position.setX(position.getX()+1);
        }
        if(KeyBoard.LEFT){
            position.setX(position.getX()-1);
        }
        if(KeyBoard.UP){
            position.setY(position.getY()-1);
        }
        if(KeyBoard.DOWN){
            position.setY(position.getY()+1);
        }
            
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);

    }
    
}
