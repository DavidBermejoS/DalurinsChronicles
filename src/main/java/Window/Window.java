package Window;

import javax.swing.*;
import Window.GamePane;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * <h2>Clase Window</h2>
 * Esta clase gestionará la creación de la ventana principal de juego
 * y se encargará ademas de instanciar la clase panel de juego "GamePane"
 * para su inicialización.
 * @see GamePane
 * @author David Bermejo Simon
 */
public class Window {

     JFrame frame;
     GamePane gamePane;

    /**
     * Constructor de la clase
     * instancia el JFrame de la clase y le da unas medidas iniciales de 800x600.
     * además establecemos como método de cierre "EXIT_ON_CLOSE"
     */
     public Window(){
         this.frame = new JFrame();
         frame.setBounds(0,0,800,600);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     }

    /**
     * Metodos encargados de anadir elementos al frame de la clase.
     */
    public void addComponents(){
         this.frame.setLayout(null);
         this.gamePane = new GamePane();
         frame.add(gamePane);
         BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
         Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                 cursorImg,
                 new Point(0, 0),
                 "blank cursor"
         );
         frame.getContentPane().setCursor(blankCursor);

     }

    /**
     * Metodo encargado de hacer visible la ventana y de llamar al metodo
     * addComponents
     * @see #addComponents()
     */
    public void startGame(){
         this.frame.setVisible(true);
         addComponents();
     }



}
