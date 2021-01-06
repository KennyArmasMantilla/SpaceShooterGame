package graphics;
//Cargar Imagenes o Cualquier otro uso externo como videos audiso..

import java.awt.Font;
import java.awt.FontFormatException;
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

    //Cargando la imagen
    public static BufferedImage ImageLoader(String paht){
        
        try {
            return ImageIO.read(Loader.class.getResource(paht));
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
    
    //Cargando la fuente
    public static Font loadFont(String path, int size){
        
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN,size);
        } catch (FontFormatException | IOException e) {
        
            e.printStackTrace();
            return null;
        }
    }
}
