package graphics;
//Cargar Imagenes o Cualquier otro uso externo como videos audiso..

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author Koala
 */

public class Loader {

    public static BufferedImage ImageLoader(String paht){
        
        try {
            return ImageIO.read(Loader.class.getResource(paht));
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
