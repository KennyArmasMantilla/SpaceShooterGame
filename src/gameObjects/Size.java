package gameObjects;

import graphics.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author Koala
 */

//Para identificar los tamañaos y colores.
public enum Size {
    BIG_BROWN(2, Assets.bigsBrown),MED_BROWN(2,Assets.medsBrown),SMALL_BROWN(2,Assets.smallsBrown),TINY_BROWN(0, null),
    BIG_GRAY(2, Assets.bigsGrey) ,MED_GRAY(2,Assets.medsGrey), SMALL_GRAY(2,Assets.smallsGrey), TINY_GRAY(2,Assets.tiniesGrey);
    
    public int quantity;
    
    public BufferedImage[] textures;
    
    private Size(int quantity, BufferedImage[] textures){
        this.quantity=quantity;
        this.textures = textures;        
    }
    
}
