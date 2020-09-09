package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 *
 * @author Koala
 */
public class Laser extends MovingObject{

    public Laser(Vector2D position, Vector2D velocity, double maxVel,double angle,BufferedImage texture) {
        super(position, velocity, maxVel, texture);
        this.angle=angle;
        this.velocity = velocity.scale(maxVel);
    }

    @Override
    public void update() {

        position=position.add(velocity);

    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        
        at=AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle);
        g2d.drawImage(texture, at, null);
        

    }
    
    
    
    
}
