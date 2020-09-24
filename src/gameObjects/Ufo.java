package gameObjects;

import java.awt.Graphics;
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
        
        }
        
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


    }

    @Override
    public void draw(Graphics g) {


    }
    
}
