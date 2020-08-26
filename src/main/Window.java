package main;


import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Koala
 */
public class Window extends JFrame{

    public static final int WIDTH=800, HEIGHT = 600;
    private Canvas canvas;
    
    
    public Window(){
        setTitle("Space Game Shooter. Kenny Armas");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//La ventana cierre cuando presionamos en X
        setResizable(false);//Para no redimensionar la ventana.
        setLocationRelativeTo(null);
        setVisible(true);
        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        canvas.setFocusable(true);//Permite recibir entradas por parte del teclado.
        
        add(canvas);
        
    
    
    
    }


    public static void main(String[] args) {
        // TODO code application logic here
        new Window();
    }
    
}
