
package math;

import java.awt.image.BufferedImage;

/**
 *
 * @author Koala
 */
public class Vector2D {
    private double x,y;
    
    
    public Vector2D(double x, double y){
        this.x=x;
        this.y=y;
    }
    
    public Vector2D(){
        x=0;
        y=0;
        
    }

    public double getMagnitud(){
    
        return Math.sqrt(x*x + y*y);
        
    }
    
    public Vector2D setDirection(double angle){
        
        double magnitude = getMagnitud();
        
        return new Vector2D(Math.cos((angle)*magnitude), Math.sin((angle)*magnitude));
    }
    
    //Mover la nave
    public Vector2D add(Vector2D v){
        return new Vector2D(x+v.getX(), y+ v.getY());
    }
    
    //Nos va a servir para modificar la magintud del vector heading
    public Vector2D scale(double value){
        return new Vector2D(x*value, y*value);
    }   
    
    //movimiento
    public void limit(double value){
    
        if(x>value)
            x=value;
        if(x< -value)
            x= -value;
        if(y>value)
            y=value;
        if(y< -value)
            y= -value;
    }
    
    //FRENADO O DESACELERACION
    public Vector2D normalize(){
    
        double magnitude = getMagnitud();
        
        return new Vector2D(x/magnitude, y/magnitude);
    }
    
    public Vector2D(int i, int i0, BufferedImage player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
}
