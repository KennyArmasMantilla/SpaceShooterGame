
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
