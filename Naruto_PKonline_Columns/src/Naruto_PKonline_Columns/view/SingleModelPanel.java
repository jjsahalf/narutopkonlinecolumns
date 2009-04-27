//This is the singlemodel panel
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.controller.Controller;
import tetris.entities.Ground;
import tetris.entities.ShapeFactory;
import tetris.view.GamePanel;

public class SingleModelPanel extends JPanel {

    GameOverPanel gameOverPanel;
    SpriteAnimationStream SingleBackThunder = new SpriteAnimationStream();
    SpriteAnimationStream SingleBackRain = new SpriteAnimationStream();
    SpriteAnimationStream SingleBackSnow = new SpriteAnimationStream();
    //SpriteAnimationStream SingleRainStar = new SpriteAnimationStream();
    JLabel combo_thunder = null;
    JLabel score_thunder = null;
    JLabel tips_thunder = null;
    JLabel combo_rain = null;
    JLabel score_rain = null;
    JLabel tips_rain = null;
    JLabel combo_snow = null;
    JLabel score_snow = null;
    JLabel tips_snow = null;
    JLabel Next_thunder = null;
    JLabel Next_rain = null;
    JLabel Next_snow = null;
    Image Background1;
    Image Background2;
    Image Background3;
    FPSMonitor fpsMonitor = new FPSMonitor();
    ShapeFactory shapeFactory = new ShapeFactory();
    Ground ground = new Ground();
    GamePanel gamePanel = new GamePanel();
    Controller controller = new Controller(shapeFactory, ground, gamePanel);
    public Thread thread;
    private boolean hasDisposed = false;    //GameOverPanel
    private BufferedImage Background;
    SpriteAnimationStream WinDemo_1P = new SpriteAnimationStream();
    SpriteAnimationStream WinDemo_2P = new SpriteAnimationStream();

    //next方块
    public Image ground_water = null;
    public Image ground_wood = null;
    public Image ground_thunder = null;
    public Image ground_fire = null;
    public Image ground_earth = null;

    //连击次数
//    SpriteAnimationStream combo1 = new SpriteAnimationStream();
//    SpriteAnimationStream combo2 = new SpriteAnimationStream();
//    SpriteAnimationStream combo3 = new SpriteAnimationStream();
//    SpriteAnimationStream combo4 = new SpriteAnimationStream();
    public Image combo1 = null;
    public Image combo2 = null;
    public Image combo3 = null;
    public Image combo4 = null;

    public Font font = new Font("微软雅黑",Font.BOLD,70);


    public SingleModelPanel() throws IOException {
        tetris.util.Global.WIDTH = 9;
        tetris.util.Global.HEIGHT = 16;
        Global.ISPKONLINE = false;
        this.setOpaque(false);
        this.setLayout(null);
            try {
                //读取combo
                combo1 = ImageIO.read(new File("ProjectResource//ComboDemo//1Combo//110.png"));
                combo2 = ImageIO.read(new File("ProjectResource//ComboDemo//2Combo//210.png"));
                combo3 = ImageIO.read(new File("ProjectResource//ComboDemo//3Combo//310.png"));
                combo4 = ImageIO.read(new File("ProjectResource//ComboDemo//4Combo//410.png"));
                //读取预测方块
                try {
                    ground_water = ImageIO.read(new File("ProjectResource//water//groundwater0000.png"));
                } catch (IOException ex) {
                    System.out.println("ground's IOimage error");
                }
                try {
                    ground_wood = ImageIO.read(new File("ProjectResource//wood//groundwood0000.png"));
                } catch (IOException ex) {
                    System.out.println("ground's IOimage error");
                }
                try {
                    ground_thunder = ImageIO.read(new File("ProjectResource//thunder//groundthunder0000.png"));
                } catch (IOException ex) {
                    System.out.println("ground's IOimage error");
                }
                try {
                    ground_fire = ImageIO.read(new File("ProjectResource//fire//groundfire0000.png"));
                } catch (IOException ex) {
                    System.out.println("ground's IOimage error");
                }
                try {
                    ground_earth = ImageIO.read(new File("ProjectResource//earth//groundearth0000.png"));
                } catch (IOException ex) {
                    System.out.println("ground's IOimage error");
                }

                this.setBackground(Color.BLACK);
                Background1 = ImageIO.read(new File("ProjectResource\\Background\\SinglePanelBCThunder.jpg"));
                Background2 = ImageIO.read(new File("ProjectResource\\Background\\SinglePanelBCRain.jpg"));
                Background3 = ImageIO.read(new File("ProjectResource\\Background\\SinglePanelBCSnow.jpg"));
                SingleBackThunder.Create("ProjectResource\\AnimationEffects\\SingleThunder\\ThunderLeavesDemo%04d.png", 130, 20, 35);
                SingleBackRain.Create("ProjectResource\\AnimationEffects\\SingleRain\\RainDemo%02d.png", 40, 1, 25);
                SingleBackSnow.Create("ProjectResource\\AnimationEffects\\SingleSnow\\SnowDemo%04d.png", 60, 60, 25);
                combo_thunder = new JLabel(new ImageIcon("ProjectResource\\Icon\\ComoboForThunder.png"));
                score_thunder = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreForThunder.png"));
                tips_thunder = new JLabel(new ImageIcon("ProjectResource\\Icon\\TipsForThunder.png"));
                Next_thunder = new JLabel(new ImageIcon("ProjectResource\\Icon\\NextForThunder.png"));

                combo_rain = new JLabel(new ImageIcon("ProjectResource\\Icon\\ComoboForRain.png"));
                score_rain = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreForRain.png"));
                tips_rain = new JLabel(new ImageIcon("ProjectResource\\Icon\\TipsForRain.png"));
                Next_rain = new JLabel(new ImageIcon("ProjectResource\\Icon\\NextForRain.png"));

                combo_snow = new JLabel(new ImageIcon("ProjectResource\\Icon\\ComoboForSnow.png"));
                score_snow = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreForSnow.png"));
                tips_snow = new JLabel(new ImageIcon("ProjectResource\\Icon\\TipsForSnow.png"));
                Next_snow = new JLabel(new ImageIcon("ProjectResource\\Icon\\NextForSnow.png"));

                combo_thunder.setOpaque(false);
                combo_thunder.setBounds(630, 300, 300, 100);
                score_thunder.setOpaque(false);
                score_thunder.setBounds(840, 0, 350, 200);
                tips_thunder.setOpaque(false);
                tips_thunder.setBounds(870, 650, 350, 200);
                Next_thunder.setOpaque(false);
                Next_thunder.setBounds(500, 45, 350, 100);

                combo_rain.setOpaque(false);
                combo_rain.setBounds(30, 650, 300, 100);
                score_rain.setOpaque(false);
                score_rain.setBounds(870, 00, 350, 200);
                tips_rain.setOpaque(false);
                tips_rain.setBounds(900, 650, 350, 200);
                Next_rain.setOpaque(false);
                Next_rain.setBounds(90, 45, 350, 100);

                combo_snow.setOpaque(false);
                combo_snow.setBounds(30, 650, 300, 100);
                score_snow.setOpaque(false);
                score_snow.setBounds(-10, 100, 350, 200);
                tips_snow.setOpaque(false);
                tips_snow.setBounds(960, 0, 350, 200);
                Next_snow.setOpaque(false);
                Next_snow.setBounds(910, 100, 350, 200);

                if (Global.BACKGROUNDSTYLE == 1) {
                    this.add(combo_thunder);
                    this.add(score_thunder);
                    this.add(tips_thunder);
                    this.add(Next_thunder);
                } else if (Global.BACKGROUNDSTYLE == 2) {
                    this.add(score_rain);
                    this.add(combo_rain);
                    this.add(tips_rain);
                    this.add(Next_rain);
                } else if (Global.BACKGROUNDSTYLE == 3) {
                    this.add(score_snow);
                    this.add(combo_snow);
                    this.add(tips_snow);
                    this.add(Next_snow);
                }

//            new Thread(new PaintThread()).start();
                setFocusable(true);
            } catch (IOException ex) {
                System.out.print("Image not found");
            }
            switch(Global.BACKGROUNDSTYLE){
                case 1:
                    gamePanel.setBounds(76, 72 - tetris.util.Global.CELL_SIZE * 3 - 30, gamePanel.getWidth() + 10, gamePanel.getHeight() + 35);
                    break;
                case 2:
                    gamePanel.setBounds(427, 72 - tetris.util.Global.CELL_SIZE * 3 - 30, gamePanel.getWidth() + 10, gamePanel.getHeight() + 35);
                    break;
                case 3:
                    gamePanel.setBounds(440, 72 - tetris.util.Global.CELL_SIZE * 3 - 30, gamePanel.getWidth() + 10, gamePanel.getHeight() + 35);
                    break;
            }    
            this.add(gamePanel);
            gamePanel.addKeyListener(controller);
            this.addKeyListener(controller);
//		frame.setUndecorated(true);
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(d.width, d.height);
            controller.newGame();
        try {
            Background = ImageIO.read(new File("ProjectResource\\Background\\GameOverPanelBC.jpg"));
            //  SingleBackRain.Create("ProjectResource\\AnimationEffects\\SingleRain\\RainDemo%02d.png", 40, 1, 25);
            //   score_snow = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreForSnow.png"));

            WinDemo_1P.Create("ProjectResource\\AnimationEffects\\WinDemo\\1PWinDemo\\1PWinDemo%02d.png", 46, 3, 25);
            WinDemo_2P.Create("ProjectResource\\AnimationEffects\\WinDemo\\2PWinDemo\\2PWinDemo%02d.png", 47, 2, 25);
            //  combo_thunder.setOpaque(false);
            //  combo_thunder.setBounds(530, 690, 300, 100);
            //  score_thunder.setOpaque(false);

            fpsMonitor.Reset();
            thread = new Thread(new PaintThread());
            thread.start();
            setFocusable(true);
        } catch (IOException ex) {
            System.out.print("Image not found");
        }
//        }
//                new Thread(new PaintThread()).start();
    }

    public void paintComponent(Graphics g) {
        System.gc();
        super.paintComponent(g);
        fpsMonitor.Update();
        g.setFont(font);
        if (!hasDisposed) {
            if (Global.BACKGROUNDSTYLE == 1) {
                g.drawImage(Background1, 0, 0, 1280, 800, this);//加载背景图片
                for (int i = 0; i < 3; i++) {
                    switch (tetris.util.Global.forecast[i][0]) {
                        case 1:
                            g.drawImage(ground_thunder, 650, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;

                        case 2:
                            g.drawImage(ground_wood, 650, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 3:
                            g.drawImage(ground_water, 650, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 4:
                            g.drawImage(ground_fire, 650, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 5:
                            g.drawImage(ground_earth, 650, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        //			case 6:g.setColor(Color.pink);break;
                        default:
                            g.setColor(Color.BLACK);
                            break;
                    }
                }
                //玩家连续消去个数
                switch(tetris.util.Global.combo){
                    case 1:
                        g.drawImage(combo1, 700, 370, 100, 100, null);
                        break;
                    case 2:
                        g.drawImage(combo2, 700, 370, 100, 100, null);
                        break;
                    case 3:
                        g.drawImage(combo3, 700, 370, 100, 100, null);
                        break;
                    case 4:
                        g.drawImage(combo4, 700, 370, 100, 100, null);
                        break;
                }

                g.drawString(tetris.util.Global.score + "", 930, 230);
                SingleBackThunder.Update(fpsMonitor.GetTimeElapse());
                SingleBackThunder.Draw(g, 0, 0, 585, 790);
            } else if (Global.BACKGROUNDSTYLE == 2) {
                g.drawImage(Background2, 0, 0, this);//加载背景图片
                for (int i = 0; i < 3; i++) {
                    switch (tetris.util.Global.forecast[i][0]) {
                        case 1:
                            g.drawImage(ground_thunder, 250, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;

                        case 2:
                        g.drawImage(ground_wood, 250, 150 + i * tetris.util.Global.CELL_SIZE,
                                tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 3:
                            g.drawImage(ground_water, 250, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 4:
                            g.drawImage(ground_fire, 250, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 5:
                            g.drawImage(ground_earth, 250, 150 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        //			case 6:g.setColor(Color.pink);break;
                        default:
                            g.setColor(Color.BLACK);
                            break;
                    }
                }
                //玩家连续消去个数
                switch(tetris.util.Global.combo){
                    case 1:
                        g.drawImage(combo1, 170, 500, 100, 100, null);
                        break;
                    case 2:
                        g.drawImage(combo2, 170, 500, 100, 100, null);
                        break;
                    case 3:
                        g.drawImage(combo3, 170, 500, 100, 100, null);
                        break;
                    case 4:
                        g.drawImage(combo4, 170, 500, 100, 100, null);
                        break;
                }

                //用户得分
                g.drawString(tetris.util.Global.score + "", 970, 230);
                SingleBackRain.Update(fpsMonitor.GetTimeElapse());
                SingleBackRain.Draw(g, 360, 0, 585, 790);
            } else if (Global.BACKGROUNDSTYLE == 3) {
                g.drawImage(Background3, 0, 0, 1280, 800, this);//加载背景图片
                for (int i = 0; i < 3; i++) {
                    switch (tetris.util.Global.forecast[i][0]) {
                        case 1:
                            g.drawImage(ground_thunder, 1100, 250 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;

                        case 2:
                        g.drawImage(ground_wood, 1100, 250 + i * tetris.util.Global.CELL_SIZE,
                                tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 3:
                            g.drawImage(ground_water, 1100, 250 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 4:
                            g.drawImage(ground_fire, 1100, 250 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        case 5:
                            g.drawImage(ground_earth, 1100, 250 + i * tetris.util.Global.CELL_SIZE,
                                    tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                            break;
                        //			case 6:g.setColor(Color.pink);break;
                        default:
                            g.setColor(Color.BLACK);
                            break;
                    }
                }
                //玩家连续消去个数
                switch(tetris.util.Global.combo){
                    case 1:
                        g.drawImage(combo1, 170, 500, 100, 100, null);
                        break;
                    case 2:
                        g.drawImage(combo2, 170, 500, 100, 100, null);
                        break;
                    case 3:
                        g.drawImage(combo3, 170, 500, 100, 100, null);
                        break;
                    case 4:
                        g.drawImage(combo4, 170, 500, 100, 100, null);
                        break;
                }
                g.drawString(tetris.util.Global.score + "",160 , 320);
                SingleBackSnow.Update(fpsMonitor.GetTimeElapse());
                SingleBackSnow.Draw(g, 330, 0, 630, 800);
            }
        } else {
            g.drawImage(Background, 0, 0, 1280, 800, this);//加载背景图片
            g.drawString(tetris.util.Global.score + "", 910, 280);
        }
    }

    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                if (!hasDisposed && controller.isGameOver() ) {
                    SingleModelPanel.this.removeAll();
                    clear();
                    clearGame();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clear() {
        Background1 = null;
        Background2 = null;
        Background3 = null;
        SingleBackThunder = null;
        SingleBackRain = null;
        SingleBackSnow = null;
        hasDisposed = true;
    }
    private void clearGame(){
        gamePanel = null;
        shapeFactory = null;
        controller = null;
        ground = null;
    }
}
