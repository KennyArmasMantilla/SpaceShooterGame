package states;

import gameObjects.Constants;
import gameObjects.Meteor;
import gameObjects.MovingObject;
import gameObjects.Player;
import gameObjects.Size;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;

//

/**
 *
 * @author Koala
 */
public class GameState {
    
    private Player player;
    //arrego de objeto movible que se va a encargar de actualizar y dibujar todos los objetos que se muevan en el juego
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
    //Meteoros
    private int meteors;
    
    
    public GameState(){
        player = new Player(new Vector2D(390,500),new Vector2D(), 5 ,Assets.player, this);
        movingObjects.add(player);
    
        meteors=1;
        startWave();
    
    }
    
    //Dividir los Meteoros
    public void divideMeteor(Meteor meteor){
        
        Size size = meteor.getSize();
        
        BufferedImage[] textures = size.textures;
        
        Size newSize = null;
        
        switch(size){
            case BIG_BROWN:
                newSize = Size.MED_BROWN;
                break;
                
            case MED_BROWN:
                newSize = Size.SMALL_BROWN;
                break;
            
            case SMALL_BROWN:
                newSize = Size.TINY_BROWN;
                break;
            
            case BIG_GRAY:
                newSize = Size.MED_GRAY;
                break;
                
            case MED_GRAY:
                newSize = Size.SMALL_GRAY;
                break;
            
            case SMALL_GRAY:
                newSize = Size.TINY_GRAY;
                break;
                
            default:
                return;
        }
        
        //Agregar los meteoros
        for(int i=0; i<size.quantity;i++){
        
            movingObjects.add(new Meteor(
                    meteor.getPosition(),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+1,
                    textures[(int)(Math.random()*textures.length)],
                    this,
                    newSize));
        
        }
        
        
        
    }
    



    
    //Para iniciar oleadas de meteoros
    private void startWave(){
        double x,y;
        for(int i = 0; i<meteors; i++){
            //posiciones aleatorios
            x= i%2==0 ? Math.random()*Constants.WIDTH : 0;
            y= i%2==0 ? 0: Math.random()*Constants.HEIGHT;            
                
            BufferedImage texture = Assets.bigsBrown[(int)(Math.random()*Assets.bigsBrown.length)];
                    movingObjects.add(new Meteor(
                    new Vector2D(x,y),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+1,
                    texture,
                    this,
                    Size.BIG_BROWN));
                    
            BufferedImage texture2 = Assets.bigsGrey[(int)(Math.random()*Assets.bigsBrown.length)];
                    movingObjects.add(new Meteor(
                    new Vector2D(x,y),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL*Math.random()+1,
                    texture2,
                    this,
                    Size.BIG_GRAY));
                    
            }

        meteors++;        

        
    }
    
    
    public void update(){
        //player.update();
        for(int i=0; i<movingObjects.size();i++){
            movingObjects.get(i).update();
        }
        
        //MAS OLEADAS
        for(int i=0; i<movingObjects.size();i++){
            if(movingObjects.get(i) instanceof Meteor){
                return ;
            }
        }
        startWave();
        
        
    }
    
    public void draw(Graphics g){
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
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
