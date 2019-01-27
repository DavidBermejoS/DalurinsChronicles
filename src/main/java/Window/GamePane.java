package Window;

import Screens.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//TODO realizar la documentacion de los metodos

/**
 * <h2>Clase GamePane</h2>
 * Clase que hereda del componente JPanel y que se encargara de gestionar la entrada de los niveles/pantallas
 * "Screens". Esta clase se encarga además de gestionar el render del juego (tasa de refresco) además de controlar
 * si se ha perdido en el juego y que puntuación se ha obtenido
 *
 * @author David Bermejo Simon
 * @implements Runnable, KeyListener
 * @see JPanel
 * @see IScreen
 */
public class GamePane extends JPanel implements Runnable, KeyListener, MouseListener {


    public HeroMenu heroMenu;

    public String userName;
    private boolean endLevel;
    private int actualLevel;
    private IScreen screen;
    private boolean gameOver;
    private double score;

    /**
     * <h3>Constructor de la clase.</h3>
     * Se inicializan las variables, se hace focus sobre el componente y se inicializan
     * sus listeners. Ademas comienza a ejecutar el run.
     */
    public GamePane() {
        this.actualLevel = 0;
        this.score = 0;
        this.endLevel = true;
        this.gameOver = false;
        this.screen = new FirstScreen(this);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        new Thread(this).start();
    }

    //METODOS PARA PINTAR LA PANTALLA

    /**
     * Metodo encargado de llamar al metodo drawScreen de la pantalla que este cargada
     * según el flujo establecido por el metodo checkLevel
     *
     * @param g : componentes graphicos del panel de juego
     * @see #checkLevel()
     * @see IScreen
     */
    @Override
    protected void paintComponent(Graphics g) {
        this.screen.drawScreen(g);
    }


    //METODO RUN DE EJECUCIÓN DEL HILO

    /**
     * Metodo encargado de gestionar la tasa de refresco (render)
     */
    @Override
    public void run() {
        while (true) {
            try {
                if(screen!=null){
                    repaint();
                    this.screen.manageGameFunctions();
                }
                checkLevel();
                Toolkit.getDefaultToolkit().sync();
                Thread.currentThread().sleep(15);//ORIGINAL : 15
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //METODOS DE GESTION DE FLUJO

    /**
     * Metodo encargado de gestionar el nivel actual en el juego
     * y cargar la pantalla en función a dicho flujo
     */
    private void checkLevel() {
        if (endLevel && !gameOver) {
            actualLevel++;
            switch (actualLevel) {
                case 0:
                    this.screen = new StartScreen(this);
                    endLevel = false;
                    break;
                case 1:
                    this.screen = new FirstScreen(this);
                    endLevel = false;
                    break;
                //TODO gestion del flujo para la carga de los distintos niveles
                case 5:
                    this.screen = new VictoryScreen(this);
                    endLevel = false;
                    break;
            }
        } else if (endLevel && gameOver) {
            this.screen = new GameOverScreen(this);
            actualLevel = -1;
            endLevel = false;

        }
    }


    //EVENTOS DE TECLADO

    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {
        this.screen.keyPressed(keyEvent);
        this.screen.dispatchKeyEvent(keyEvent);
    }

    public void keyReleased(KeyEvent keyEvent) {
        this.screen.dispatchKeyEvent(keyEvent);

    }

    //EVENTOS DE MOUSE

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.screen.clickMouse(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        //NO HACE NADA

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        //NO HACE NADA

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        //NO HACE NADA

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        //NO HACE NADA

    }


    //GETTERS Y SETTERS DE LA CLASE

    public boolean isEndLevel() {
        return endLevel;
    }

    public void setEndLevel(boolean endLevel) {
        this.endLevel = endLevel;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public void setActualLevel(int actualLevel) {
        this.actualLevel = actualLevel;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HeroMenu getHeroMenu() {
        return heroMenu;
    }

    public void setHeroMenu(HeroMenu heroMenu) {
        this.heroMenu = heroMenu;
    }
}