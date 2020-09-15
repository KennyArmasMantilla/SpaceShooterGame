package gameObjects;

import graphics.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author Koala
 */

//Para identificar los tamañaos y colores.
public enum Size {
    BIG_BROWN(2, Assets.medsBrown),MED_BROWN(2,Assets.smallsBrown),SMALL_BROWN(2,Assets.tiniesBrown),TINY_BROWN(0, null),
    BIG_GRAY(2, Assets.medsGrey) ,MED_GRAY(2,Assets.smallsGrey), SMALL_GRAY(2,Assets.tiniesGrey), TINY_GRAY(0,null);
    
    public int quantity;
    
    public BufferedImage[] textures;
    
    private Size(int quantity, BufferedImage[] textures){
        this.quantity=quantity;
        this.textures = textures;        
    }
    
}
