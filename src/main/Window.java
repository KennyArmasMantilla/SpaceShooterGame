package main;


import javax.swing.JFrame;

/**
 *
 * @author Koala
 */
public class Window extends JFrame{

    public static final int WIDTH=800, HEIGHT = 600;
    
    
    
    public Window(){
        setTitle("Space Game Shooter. Kenny Armas");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//La ventana cierre cuando presionamos en X
        setResizable(false);//Para no redimensionar la ventana.
        setLocationRelativeTo(null);
        
        
        
        setVisible(true);
    }


    public static void main(String[] args) {
        // TODO code application logic here
        new Window();
    }
    
}
