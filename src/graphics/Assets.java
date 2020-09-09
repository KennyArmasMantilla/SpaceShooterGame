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
    
    
    public static void init(){
        player = Loader.ImageLoader("/ships/player.png");
    
        speed=Loader.ImageLoader("/effects/fire08.png");
    }
}
