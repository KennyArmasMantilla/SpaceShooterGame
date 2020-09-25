package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author Koala
 */
public class Ufo extends MovingObject{

    //Arreglo con los nodos que forman el camino
    private ArrayList<Vector2D> path;
    
    //Referencia al nodo actual que se persigue
    private Vector2D currentNode;
    
    //Representa el indice en el arreglo de nodos
    private int index;
    
    //Booleando para saber si acabo de recorrer el camino
    private boolean following;
    
    public Ufo(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
            ArrayList<Vector2D> path ,GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.path=path;
        index=0;
        following=true;
        
    }

    //gestionar todo el camino
    private Vector2D pathFollowing(){
        currentNode = path.get(index);
        //calculando la distancia hacia el nodo
        double distaceToNode = currentNode.subtracc(getCenter()).getMagnitud();
        //preguntar si la distancia al nodo es menor que el radio
        if(distaceToNode < Constants.NODE_RADIUS){
            //cambiamos al siguiente nodo
            index++;
            //si el indice llego al final
            if(index >=path.size()){
                following=false;
            }
            
        }
        return seekForce(currentNode);
    }
    
    
    
    
    //Para calcular la fuerza
    private Vector2D seekForce(Vector2D target){
        //la velocidad deseada
        Vector2D desiredVelocity = target.subtracc(getCenter());
        desiredVelocity=desiredVelocity.normalize().scale(maxVel);
        return desiredVelocity.subtracc(velocity);
    }
    
    
    @Override
    public void update() {
        Vector2D pathFollowing;
        //Si seguimos el camino
        if(following){
            pathFollowing = pathFollowing();
        }
        //de lo contrario acabamos el camino
        else
        {
            pathFollowing = new Vector2D();
        }
        //aceleracion
        pathFollowing = pathFollowing.scale(1/Constants.UFO_MASS);
     
        velocity = velocity.add(pathFollowing);
        
        velocity = velocity.limit(maxVel);
        
        position = position.add(velocity);
        
        if(position.getX() > Constants.WIDTH || position.getY() > Constants.HEIGHT
                || position.getX() < 0 || position.getY() < 0){
            Destroy();
        }
        
        angle+=0.05;
        collidesWith();
    }

    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
        
        at=AffineTransform.getTranslateInstance(position.getX(), position.getY());
        
        at.rotate(angle,width/2, height/2);
        
        g2d.drawImage(texture, at, null);
        
        
        //para ver los nodos del camino
        g.setColor(Color.red);
        for(int i = 0; i<path.size(); i++){
            g.drawRect((int)path.get(i).getX(), (int)path.get(i).getY(), 5, 5);
        }
    }
    
}
