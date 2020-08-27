//Recursos externos
package graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Koala
 */
public class Assets {
    
    public static BufferedImage player;
    
    public static void init(){
        player = Loader.ImageLoader("/ships/player.png");
    }
}
