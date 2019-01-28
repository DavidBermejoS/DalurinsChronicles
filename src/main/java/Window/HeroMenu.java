package Window;

import Sprites.Hero;
import Utilities.ResourcesCollector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeroMenu extends JPanel {

    GamePane gamePane;
    BufferedImage[] imagesFaces;
    BufferedImage faceImage;
    JLabel canvasHeroImage;
    JProgressBar heroLife;
    JLabel numPotions;
    JLabel labelAttack;
    JLabel labelDefense;
    JLabel labelMaxHP;
    BufferedImage potionImage;
    Hero hero;


    private static final int NORMAL_FACE_PARAM = 0;
    private static final int COMBAT_FACE_PARAM = 1;
    private static final int VICTORY_FACE_PARAM = 3;
    private static final int DANGER_FACE_PARAM = 5;
    private static final int DEFEAT_FACE_PARAM = 6;


    public HeroMenu(GamePane gamePane) {
        this.gamePane = gamePane;
        this.hero = gamePane.getHero();
        try {
            this.potionImage = ImageIO.read(new File("src/main/resources/potions/attack_potion.png"));
            this.imagesFaces = new BufferedImage[8];
            addComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MEtodo para anadir los componentes del menu superior
     */
    public void addComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints settings;
        ResourcesCollector rc = new ResourcesCollector();

        this.imagesFaces = rc.getHeroFaces();

        this.canvasHeroImage = new JLabel();
        this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/normal.png", "normalFace"));
        settings = new GridBagConstraints();
        settings.gridx = 0;
        settings.gridy = 0;
        settings.ipadx = 5;
        settings.ipady = 5;
        this.add(canvasHeroImage, settings);


        this.heroLife = new JProgressBar(0, Hero.MAX_HP);
        this.heroLife.setString("HERO HP");
        this.heroLife.setBackground(Color.RED);
        this.heroLife.setForeground(Color.GREEN);
        settings = new GridBagConstraints();
        settings.gridx = 1;
        settings.gridy = 0;
        settings.ipadx = 40;
        settings.ipady = 10;
        this.add(heroLife, settings);


        this.labelAttack = new JLabel("ATK : "+this.hero.getAtk());
        this.labelAttack.setForeground(Color.RED);
        settings = new GridBagConstraints();
        settings.gridx = 2;
        settings.gridy=0;
        settings.ipadx = 40;
        settings.ipady = 10;
        settings.insets = new Insets(0,20,0,0);

        this.add(labelAttack, settings);

        this.labelDefense= new JLabel("DEF : "+this.hero.getDef());
        this.labelDefense.setForeground(Color.RED);
        settings = new GridBagConstraints();
        settings.gridx = 3;
        settings.gridy=0;
        settings.ipadx = 40;
        settings.ipady = 10;
        settings.insets = new Insets(0,20,0,0);

        this.add(labelDefense, settings);

        this.labelMaxHP= new JLabel("MAX HP : "+Hero.MAX_HP);
        this.labelMaxHP.setForeground(Color.RED);
        settings = new GridBagConstraints();
        settings.gridx = 4;
        settings.gridy=0;
        settings.ipadx = 40;
        settings.ipady = 10;
        settings.insets = new Insets(0,20,0,0);
        this.add(labelMaxHP, settings);

    }

    /**
     * Metodo para pintar los componentes del menu superior
     */
    public void paintMenuComponents(Hero hero, boolean endLevel) {
        this.hero = hero;
        if(hero!=null){

            if (hero.getTotalHp() < Hero.MAX_HP / 2) {
                this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/danger.png", "dangerFace"));
            }

            if (hero.getTotalHp() <= 0) {
                this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/defeat.png", "defeatFace"));
            }

            if(this.gamePane.isGameOver()){
                this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/victory.png", "victoryFace"));
            }

            this.heroLife.setValue(gamePane.getHero().getTotalHp());
            this.heroLife.setString(String.valueOf(hero.getTotalHp()));
        }
    }


    public void statsBar(Graphics g, Hero hero, GamePane gamePane) {
//        Random r = new Random();
//        g.fillRect(0,0,gamePane.getWidth(),gamePane.getHeight());
//        g.setColor(Color.RED);
//        g.setFont(new Font("MonoSpace", Font.BOLD, 24));
//        g.drawString("Potions: ", 0, 0);
//        g.drawString("x "+ hero.getNumItems(),30,0);
//        BufferedImage potionRed = new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
//        Graphics graphPotion = potionRed.getGraphics();
//        try {
//            BufferedImage imageAux = ImageIO.read(new File("src/main/resources/potions/attack_potion.png"));
//            graphPotion.drawImage(imageAux,20,20,null);
//            graphPotion.dispose();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
