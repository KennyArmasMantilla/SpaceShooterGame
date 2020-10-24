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
    
    //---------------------Meteoros------------------
    //Marrones
    public static BufferedImage[] bigsBrown = new BufferedImage[4];
    public static BufferedImage[] medsBrown = new BufferedImage[2];
    public static BufferedImage[] smallsBrown = new BufferedImage[2];
    public static BufferedImage[] tiniesBrown = new BufferedImage[2];
       
    //Plomos
    public static BufferedImage[] bigsGrey = new BufferedImage[4];
    public static BufferedImage[] medsGrey = new BufferedImage[2];
    public static BufferedImage[] smallsGrey = new BufferedImage[2];
    public static BufferedImage[] tiniesGrey= new BufferedImage[2];
    
    //----------------Explosiones--------------------------
    public static BufferedImage[] exp = new BufferedImage[9];
    
    //-----------------ENEMYS------------------------------
    //BLACK
    public static BufferedImage[] enemiesBlack = new BufferedImage[5];
    //BLUE
    public static BufferedImage[] enemiesBlues = new BufferedImage[5];
    //GREEN
    public static BufferedImage[] enemiesGreen = new BufferedImage[5];
    //RED
    public static BufferedImage[] enemiesRed = new BufferedImage[5];
    
    //------------UFO-------------------------------------
    public static BufferedImage[] ufo = new BufferedImage[4];
    
    //------------HUD-------------------------------------
    //Numbers
    public static BufferedImage[] numbers = new BufferedImage[10];
    //life
    public static BufferedImage life1;
    public static BufferedImage life2;
    //x
    public static BufferedImage X;
    
    
    public static void init(){
        //Nave
        player = Loader.ImageLoader("/ships/player.png");
                
        //Propulsion
        speed=Loader.ImageLoader("/effects/fire08.png");
        
        //Laser
        laserBlue=Loader.ImageLoader("/lasers/laserBlue05.png");
        laserGreen=Loader.ImageLoader("/lasers/laserGreen05.png");
        laserRed=Loader.ImageLoader("/lasers/laserRed03.png");
        
        //------------Meteoros----------------
        //Brown
        for(int i = 0; i<bigsBrown.length; i++){
            bigsBrown[i]=Loader.ImageLoader("/meteoros/Brown_big"+(i+1)+".png");
        }
        
        for(int i = 0; i<medsBrown.length; i++){
            medsBrown[i]=Loader.ImageLoader("/meteoros/Brown_med"+(i+1)+".png");
        }
        
        for(int i = 0; i<smallsBrown.length; i++){
            smallsBrown[i]=Loader.ImageLoader("/meteoros/Brown_small"+(i+1)+".png");
        }
        
        for(int i = 0; i<tiniesBrown.length; i++){
            tiniesBrown[i]=Loader.ImageLoader("/meteoros/Brown_tiny"+(i+1)+".png");
        }
        
        //Grey
        for(int i = 0; i<bigsGrey.length; i++){
            bigsGrey[i]=Loader.ImageLoader("/meteoros/Grey_big"+(i+1)+".png");
        }
        
        for(int i = 0; i<medsGrey.length; i++){
            medsGrey[i]=Loader.ImageLoader("/meteoros/Grey_med"+(i+1)+".png");
        }

        for(int i = 0; i<smallsGrey.length; i++){
            smallsGrey[i]=Loader.ImageLoader("/meteoros/Grey_small"+(i+1)+".png");
        }

        for(int i = 0; i<tiniesGrey.length; i++){
            tiniesGrey[i]=Loader.ImageLoader("/meteoros/Grey_tiny"+(i+1)+".png");
        }
        
        //-------------explosiones---------------
        for(int i = 0; i<exp.length; i++){
            exp[i]=Loader.ImageLoader("/explosion/exp"+(i+1)+".png");
        }
        
        //-------------enemies-------------------
       //BLACK
        for(int i = 0; i<enemiesBlack.length; i++){
            enemiesBlack[i]=Loader.ImageLoader("/enemies/enemyBlack"+(i+1)+".png");
        }
       //BLUES
       for(int i = 0; i<enemiesBlues.length; i++){
           enemiesBlues[i]=Loader.ImageLoader("/enemies/enemyBlue"+(i+1)+".png");
       }
       //GREEN
       for(int i = 0; i<enemiesGreen.length; i++){
           enemiesGreen[i]=Loader.ImageLoader("/enemies/enemyGreen"+(i+1)+".png");
       }
       //RED
       for(int i = 0; i<enemiesRed.length; i++){
           enemiesRed[i]=Loader.ImageLoader("/enemies/enemyRed"+(i+1)+".png");
       }
        
       //UFO
       for(int i = 0; i<ufo.length; i++){
           ufo[i]= Loader.ImageLoader("/UFO/ufo"+(i+1)+".png");
       }
        
       //---------------------HUD----------------
       //Numbers for HUD
       for(int i = 0; i<numbers.length; i++){
            numbers[i]= Loader.ImageLoader("/HUD/"+(i+0)+".png");
       }
       
       //Seta1
       life1=Loader.ImageLoader("/HUD/life.png");
       
       //seta2
       life2=Loader.ImageLoader("/HUD/seta.png");
       
       //X
       X=Loader.ImageLoader("/HUD/10.png");
       
    }
}
