package gameObjects;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import graphics.Assets;
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
    
    //disparo
    private Chronometer fireRate;
    
    //Angulo de la nave
    private double angulo;
    
    
    public Ufo(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
            ArrayList<Vector2D> path ,GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.path=path;
        index=0;
        following=true;
        fireRate= new Chronometer();
        fireRate.run(Constants.UFO_FIRE_RATE);
        angulo=0;
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
        //SHOOT
        if(!fireRate.isRunning()){
            Vector2D toPlayer = gameState.getPlayer().getCenter().subtracc(getCenter());
            
            toPlayer = toPlayer.normalize();
            
            double currentAngle = toPlayer.getAngle();
            //Disparo directo
            //double newAngle = currentAngle;
            
            currentAngle += Math.random()*Constants.UFO_ANGLE_RANGE - Constants.UFO_ANGLE_RANGE / 2;
            
            if(toPlayer.getX()<0){
                currentAngle = -currentAngle + Math.PI;
            }
            
            toPlayer=toPlayer.setDirection(currentAngle);
            
            Laser laser = new Laser( 
                    getCenter().add(toPlayer.scale(width)),
                    toPlayer,
                    Constants.LASER_VEL,
                    currentAngle + Math.PI/2,
                    Assets.laserGreen, gameState);
            //Lo agregos en el arreglo en posicion  para que se dibujo primero
            gameState.getMovingObjects().add(0,laser);
            
            //Correr de nuevoo el cronometro
            fireRate.run(Constants.UFO_FIRE_RATE);
//            angulo=newAngle;
        }
        //angle=angulo;
        angle+=0.05;
        
        collidesWith();
        fireRate.update();
    }
    //Para sobreescribir 
    @Override
    public void Destroy(){
        gameState.addScore(Constants.UFO_SCORE);
        super.Destroy();
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
