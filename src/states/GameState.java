package states;

import gameObjects.Player;
import graphics.Assets;
import java.awt.Graphics;
import math.Vector2D;

/**
 *
 * @author Koala
 */
public class GameState {
    
    private Player player;
    
    
    
    public GameState(){
        player = new Player(new Vector2D(390,500),new Vector2D(), 5 ,Assets.player);
    }
    
    public void update(){
        player.update();
        
    }
    
    public void draw(Graphics g){
        
        player.draw(g);
    }
    

}
