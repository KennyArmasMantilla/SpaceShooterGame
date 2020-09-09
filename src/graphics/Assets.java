//Recursos externos
package graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Koala
 */
public class Assets {
    
    public static BufferedImage player;
    
    //effects
    public static BufferedImage speed;
    //lasers
    public static BufferedImage laserBlue,laserGreen,laserRed;
    
    
    public static void init(){
        player = Loader.ImageLoader("/ships/player.png");
    
        speed=Loader.ImageLoader("/effects/fire08.png");
        
        laserBlue=Loader.ImageLoader("/lasers/laserBlue05.png");
        laserGreen=Loader.ImageLoader("/lasers/laserGreen05.png");
        laserRed=Loader.ImageLoader("/lasers/laserRed03.png");
        
    }
}
