package states;

import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;
import java.awt.Graphics;
import java.util.ArrayList;
import math.Vector2D;

/**
 *
 * @author Koala
 */
public class GameState {
    
    private Player player;
    //arrego de objeto movible que se va a encargar de actualizar y dibujar todos los objetos que se muevan en el juego
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    
    
    
    public GameState(){
        player = new Player(new Vector2D(390,500),new Vector2D(), 5 ,Assets.player, this);
        movingObjects.add(player);
    }
    
    public void update(){
        //player.update();
        for(int i=0; i<movingObjects.size();i++){
            movingObjects.get(i).update();
        }
        
        
    }
    
    public void draw(Graphics g){
        
        //player.draw(g);
        for(int i=0; i<movingObjects.size();i++){
            movingObjects.get(i).draw(g);
        }

    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    public void setMovingObjects(ArrayList<MovingObject> movingObjects) {
        this.movingObjects = movingObjects;
    }
    
    
    
    
}
