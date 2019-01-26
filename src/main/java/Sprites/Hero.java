package Sprites;

import Utilities.ResourcesCollector;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * <h2>Clase Hero</h2>
 * Clase que define los parametros del personaje.
 * Hereda de la clase Sprite, y es encargada de guardar las estadisticas del personaje
 *
 * @author David Bermejo Simon
 */
public class Hero extends Sprite {
    boolean isAlive;
    String user;
    int totalHp;
    int atk;
    int def;


    JProgressBar life;
    Item[] items;
    Skill[] skills;
    String[][] matrixAnimation;
    String[] routesAnimation;


    private boolean moving;

    private String lastDirection;
    private String actualDirection;
    private int paramDirection;


    private boolean attacking;

    BufferedImage[] actualAnimationLine;
    BufferedImage[][] walkingImagesLine;
    BufferedImage[][] attackingImagesLine;


    /**
     * Constructor de la clase vacio
     */
    public Hero() {
        this.walkingImagesLine = new BufferedImage[8][10];
        loadWalkingImages();
        this.attackingImagesLine = new BufferedImage[8][18];
        loadAttackingImages();
        lastDirection = "";
        attacking = false;
        lastDirection = "";
        matrixAnimation = new String[10][8];
        routesAnimation = new String[10];
    }

    /**
     * Constructor con los parametros propios de la clase y los de la clase Sprite
     *
     * @param posX
     * @param posY
     * @param vX
     * @param vY
     * @param id
     * @param imageRoutes
     * @param isAlive
     * @param user
     * @see Sprite
     */
    public Hero(int posX, int posY, int vX, int vY, String id, String[] imageRoutes, boolean isAlive, String user) {

        super(posX, posY, vX, vY, id);
        matrixAnimation = new String[10][8];
        routesAnimation = new String[10];
        this.isAlive = isAlive;
        this.user = user;
    }

    /**
     * Constructor con los parametros propios de la clase
     *
     * @param isAlive
     * @param user
     */
    public Hero(boolean isAlive, String user) {
        matrixAnimation = new String[10][8];
        routesAnimation = new String[10];
        this.isAlive = isAlive;
        this.user = user;
    }

    /**
     * Metodo encargado de devolver el item
     * cuyo nombre se recibe por parametro
     *
     * @param name : nombre del item
     * @return
     */
    public Item getSelectedItem(String name) {
        Item itemReturn = null;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName().equalsIgnoreCase(name)) {
                itemReturn = items[i];
            }
        }
        return itemReturn;
    }


    /**
     * Metodo encargado de devolver el item
     * cuyo nombre se recibe por parametro
     *
     * @param name : nombre de la habilidad
     * @return
     */
    public Skill getSelectedSkill(String name) {
        Skill skillReturn = null;
        for (int i = 0; i < skills.length; i++) {
            if (skills[i].getName().equalsIgnoreCase(name)) {
                skillReturn = skills[i];
            }
        }
        return skillReturn;
    }
    //COLECCION DE METODOS QUE GESTIONAN EL MOVIMIENTO DEL HEROE

    /**
     * Metodo encargado de englobar los otros metodos que establecen el movimiento del personaje
     */
    public void moveCharacter(boolean[] keys) {
        setMoveDirection(keys);
        setParamDirection();
        setMoveParameters(keys);
        setMoveAnimation();

    }

    public void Attack() {

        setParamDirection();
        setAttackAnimation();

    }


    /**
     * Metodo encargado de definir que dirección toma el heroe segun la combinacion de teclas que tenga pulsada
     *
     * @param keys : array de booleanos con todos las teclas que han sido pulsadas y las que no
     */
    public void setMoveDirection(boolean[] keys) {
        String directionAux = "";
        int count = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] && count < 3) {
                count++;
                switch (i) {
                    case KEY_N:
                        directionAux = directionAux.concat("N");
                        break;
                    case KEY_W:
                        directionAux = directionAux.concat("W");
                        break;
                    case KEY_S:
                        directionAux = directionAux.concat("S");
                        break;
                    case KEY_E:
                        directionAux = directionAux.concat("E");
                        break;
                }
            }
            this.actualDirection = directionAux;
        }
    }

    /**
     * Metodo encargado de ajustar la velocidad y los parametros de movimiento segun la
     * direccion actual
     *
     * @param keys : array de booleanos con todos las teclas que han sido pulsadas y las que no
     */
    public void setMoveParameters(boolean[] keys) {
        this.vX = 0;
        this.vY = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i]) {
                switch (i) {
                    case 0:
                        vY += -3;
                        break;
                    case 1:
                        vX += -3;
                        break;
                    case 2:
                        vY += 3;
                        break;
                    case 3:
                        vX += 3;
                        break;
                }
            }
        }
        if (vX == 0 && vY == 0) {
            this.moving = false;
        } else {
            this.moving = true;
        }
        vTotal = Math.sqrt(Math.pow(vX, 2) + Math.pow(vY, 2));
        if (vTotal > 3) {
            vX = Math.abs(vX) / vX * 1.44;
            vY = Math.abs(vY) / vY * 1.44;
        }
    }

    /**
     * Metodo encargado de establecer una direccion como parametro para hacer referencia al array de imagenes
     * que necesita para cargar la animacion
     */
    private void setParamDirection() {
        if (actualDirection.equalsIgnoreCase("N")) {
            this.paramDirection = 0;

        }
        if (actualDirection.equalsIgnoreCase("S")) {
            this.paramDirection = 1;

        }
        if (actualDirection.equalsIgnoreCase("E")) {
            this.paramDirection = 5;

        }
        if (actualDirection.equalsIgnoreCase("W")) {
            this.paramDirection = 4;

        }
        if (actualDirection.equalsIgnoreCase("NW") || actualDirection.equalsIgnoreCase("WN")) {
            this.paramDirection = 6;
        }
        if (actualDirection.equalsIgnoreCase("NE") || actualDirection.equalsIgnoreCase("EN")) {
            this.paramDirection = 7;
        }
        if (actualDirection.equalsIgnoreCase("SW") || actualDirection.equalsIgnoreCase("WS")) {
            this.paramDirection = 2;
        }
        if (actualDirection.equalsIgnoreCase("SE") || actualDirection.equalsIgnoreCase("ES")) {
            this.paramDirection = 3;
        }
        if (actualDirection.equalsIgnoreCase("")) {
            this.actualDirection = lastDirection;
            setParamDirection();
        }
    }

    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual de movimiento
     */
    public void setMoveAnimation() {
        if (isMoving()) {
            if (!lastDirection.equalsIgnoreCase(actualDirection) && !actualDirection.equalsIgnoreCase("")) {
                this.actualAnimationLine = this.walkingImagesLine[paramDirection];
                lastDirection = actualDirection;
            }
            countAnimatorPhase++;
            this.imageSprite = actualAnimationLine[countAnimatorPhase / 3 % actualAnimationLine.length];
            this.refreshBuffer();
        }
    }


    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual de ataque
     */
    public void setAttackAnimation() {
        if (isAttacking()) {
            if (!actualDirection.equalsIgnoreCase("")) {
                this.actualAnimationLine = this.attackingImagesLine[paramDirection];
            }
            countAnimatorPhase++;
            this.imageSprite = actualAnimationLine[countAnimatorPhase / 2 % actualAnimationLine.length];
            this.refreshBuffer();
        }

    }


    //METODOS ENCARGADO DE GUARDAR LAS IMAGENES QUE USARÁ EL HEROE

    /**
     * Este metodo se encarga de cargar las imagenes del heroe andando en el array de la clase walkingImagesLine
     */
    private void loadWalkingImages() {
        ResourcesCollector resCol = new ResourcesCollector();
        for (int i = 0; i < allDirections.length; i++) {
            walkingImagesLine[i] = resCol.getImagesTargetActionDirection(resCol.HERO_TARGET, resCol.WALK_ACTION, allDirections[i]);
        }
    }

    /**
     * Este metodo se encarga de cargar las imagenes del heroe atacando en el array de la clase attackingImagesLine
     */
    private void loadAttackingImages() {
        ResourcesCollector resCol = new ResourcesCollector();
        for (int j = 0; j < allDirections.length; j++) {
            attackingImagesLine[j] = resCol.getImagesTargetActionDirection(resCol.HERO_TARGET, resCol.ATTACK_ACTION, allDirections[j]);
        }
    }


    //GESTION DE COLISIONES DEL HEROE
    public void checkHeroCollisions() {

    }


    //GETTERS Y SETTERS
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTotalHp() {
        return totalHp;
    }

    public void setTotalHp(int totalHp) {
        this.totalHp = totalHp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public BufferedImage getBufferedImage() {
        return buffer;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.buffer = bufferedImage;
    }

    public JProgressBar getLife() {
        return life;
    }

    public void setLife(JProgressBar life) {
        this.life = life;
    }

    public Item[] getItems() {
        return items;
    }

    public int getNumItems() {
        if (items == null) {
            return 0;
        }
        return items.length;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public String getActualDirection() {
        return actualDirection;
    }

    public void setActualDirection(String actualDirection) {
        this.actualDirection = actualDirection;
    }

    public void setAttacking(Boolean b) {
        this.attacking = b;
    }

    public boolean isAttacking() {
        return this.attacking;
    }
}
