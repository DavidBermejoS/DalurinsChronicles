package Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import Window.GamePane;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;


/**
 * <h2>Clase Window</h2>
 * Esta clase gestionará la creación de la ventana principal de juego
 * y se encargará ademas de instanciar la clase panel de juego "GamePane"
 * para su inicialización.
 * @see GamePane
 * @author David Bermejo Simon
 */
public class Window {

    private BufferedImage cursorImg = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB_PRE);
    JFrame frame;
    GamePane gamePane;
    HeroMenu heroMenu;

    /**
     * Constructor de la clase
     * instancia el JFrame de la clase y le da unas medidas iniciales de 800x600.
     * además establecemos como método de cierre "EXIT_ON_CLOSE"
     */
     public Window(){
         this.frame = new JFrame();
         frame.setBounds(0,0,1200,800);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         try {
             cursorImg = ImageIO.read(new File("src/main/resources/cursor/cursor_lighter.png"));
         } catch (IOException e) {
             System.out.println("aqui");
             e.printStackTrace();
         }
     }

    /**
     * Metodos encargados de anadir elementos al frame de la clase.
     */
    public void addComponents(){
         this.frame.setLayout(new GridBagLayout());
         GridBagConstraints settings;

        this.gamePane = new GamePane();
        settings = new GridBagConstraints();
        settings.gridx = 0;
        settings.gridy = 1;
        settings.weightx = 1;
        settings.weighty = 10;
        settings.fill = GridBagConstraints.BOTH;
        frame.add(gamePane,settings);

        this.heroMenu = new HeroMenu(this.gamePane);
        settings = new GridBagConstraints();
        settings.gridx = 0;
        settings.gridy = 0;
        settings.weightx = 1;
        settings.weighty = 1;
        settings.fill = GridBagConstraints.BOTH;

        frame.add(heroMenu,settings);
        gamePane.setHeroMenu(this.heroMenu);

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
