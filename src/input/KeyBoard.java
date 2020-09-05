
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Koala
 */

//Esta clase se encarga de todo lo que ingrese por teclado.
//Implementamos keyListener para que escuche por teclado
public class KeyBoard  implements KeyListener{
    
    private boolean[] keys= new boolean[256];
    
    public static boolean UP, LEFT, RIGHT, DOWN;
    
    
    public KeyBoard(){
        UP=false;
        LEFT= false;
        RIGHT=false;
        DOWN=false;
    }

//NOS PERMITE MANTENER EL ESTADO DE CADA BOOLEANO.
    public void update(){
        
        UP = keys[KeyEvent.VK_UP];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        DOWN = keys[KeyEvent.VK_DOWN];
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyCode());
        keys[e.getKeyCode()]=true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
