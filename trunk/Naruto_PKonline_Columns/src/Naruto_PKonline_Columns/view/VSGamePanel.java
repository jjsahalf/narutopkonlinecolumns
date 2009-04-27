//This is the singlemodel panel
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.controller.Controller;
import tetris.controller.ControllerFor2P;
import tetris.entities.Ground;
import tetris.view.GamePanel;
import tetris.entities.ShapeFactory;

public class VSGamePanel extends JPanel {

    //SpriteAnimationStream SingleBackThunder = new SpriteAnimationStream();
    //Image Background1;
    FPSMonitor fpsMonitor = new FPSMonitor();
    private BufferedImage Background;
    JLabel Icon1 = null;
    JLabel Icon2 = null;
    JLabel Icon3 = null;
    JLabel Icon4 = null;
    JLabel Icon5 = null;
    JLabel SixStar1 = null;
    JLabel SixStar2 = null;
    JLabel Score_1P = null;
    JLabel Score_2P = null;
    JLabel Combo_1P = null;
    JLabel Combo_2P = null;
    JLabel Next_1P = null;
    JLabel Next_2P = null;
    SpriteAnimationStream Fire_1P = new SpriteAnimationStream();
    SpriteAnimationStream Fire_2P = new SpriteAnimationStream();    //游戏版块
    ShapeFactory shapeFactoryFirst;
    Ground groundFirst;
    GamePanel gamePanelFirst;
    Controller controllerFirst;
    ShapeFactory shapeFactorySecond;
    Ground groundSecond;
    GamePanel gamePanelSecond;
    ControllerFor2P controllerSecond;
    private boolean hasDisposed = false;    //结束游戏的面板
    private int winPlayer = 1;
    private BufferedImage Background1;
    SpriteAnimationStream WinDemo_1P = new SpriteAnimationStream();
    SpriteAnimationStream WinDemo_2P = new SpriteAnimationStream();
    private Font font = new Font("微软雅黑", Font.BOLD, 70);
    private int temp1PScore = 0;
    private int temp2PScore = 0;

    public VSGamePanel() {
        try {
            this.setFocusable(true);
            try {
                this.setOpaque(false);
                this.setLayout(null);
                Background = ImageIO.read(new File("ProjectResource\\Background\\VSGamePanelBC.jpg"));

                Icon1 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Naruto.png"));
                Icon2 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Sasuke.png"));
                Icon3 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Kakasi.png"));
                Icon4 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Sakura.png"));
                Icon5 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Itachi.png"));
                SixStar1 = new JLabel(new ImageIcon("ProjectResource\\Icon\\6Star2.png"));
                SixStar2 = new JLabel(new ImageIcon("ProjectResource\\Icon\\6Star1.png"));
                Score_1P = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreFor1P.png"));
                Score_2P = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreFor2P.png"));
                Combo_1P = new JLabel(new ImageIcon("ProjectResource\\Icon\\ComboFor1P.png"));
                Combo_2P = new JLabel(new ImageIcon("ProjectResource\\Icon\\ComboFor2P.png"));
                Next_1P = new JLabel(new ImageIcon("ProjectResource\\Icon\\NextFor1P.png"));
                Next_2P = new JLabel(new ImageIcon("ProjectResource\\Icon\\NextFor2P.png"));

                Fire_2P.Create("ProjectResource\\AnimationEffects\\2pFire\\2pFire%04d.png", 30, 30, 25);
                Fire_1P.Create("ProjectResource\\AnimationEffects\\1pFire\\1pFire%04d.png", 30, 30, 25);

                Score_1P.setOpaque(false);
                Score_1P.setBounds(715, 650, 370, 75);
                Score_2P.setOpaque(false);
                Score_2P.setBounds(715, 650, 370, 75);
                Combo_1P.setOpaque(false);
                Combo_1P.setBounds(715, 650, 370, 75);
                Combo_2P.setOpaque(false);
                Combo_2P.setBounds(715, 650, 370, 75);
                Next_1P.setOpaque(false);
                Next_1P.setBounds(715, 650, 370, 75);
                Next_2P.setOpaque(false);
                Next_2P.setBounds(715, 650, 370, 75);

                SixStar1.setOpaque(false);
                SixStar1.setBounds(100, 550, 300, 300);
                SixStar2.setOpaque(false);
                SixStar2.setBounds(875, 550, 300, 300);

                this.add(SixStar1);
                this.add(SixStar2);
                // this.add(Score_1P);
                // this.add(Score_2P);
                // this.add(Combo_1P);
                //  this.add(Combo_2P);
                //  this.add(Next_1P);
                //  this.add(Next_2P);
                //第一位玩家的角色
                switch (Global.FIRSTPLAYERROLE) {
                    case 1:
                        Icon1.setOpaque(false);
                        Icon1.setBounds(40, 700, 75, 75);
                        this.add(Icon1);
                        break;
                    case 2:
                        Icon2.setOpaque(false);
                        Icon2.setBounds(40, 700, 75, 75);
                        this.add(Icon2);
                        break;
                    case 3:
                        Icon3.setOpaque(false);
                        Icon3.setBounds(40, 700, 75, 75);
                        this.add(Icon3);
                        break;
                    case 4:
                        Icon4.setOpaque(false);
                        Icon4.setBounds(40, 700, 75, 75);
                        this.add(Icon4);
                        break;
                    case 5:
                        Icon5.setOpaque(false);
                        Icon5.setBounds(40, 700, 75, 75);
                        this.add(Icon5);
                        break;
                }

                //第二个玩家的角色
                switch (Global.SECONDPLAYERROLE) {
                    case 1:
                        Icon1.setOpaque(false);
                        Icon1.setBounds(1165, 700, 75, 75);
                        this.add(Icon1);
                        break;
                    case 2:
                        Icon2.setOpaque(false);
                        Icon2.setBounds(1165, 700, 75, 75);
                        this.add(Icon2);
                        break;
                    case 3:
                        Icon3.setOpaque(false);
                        Icon3.setBounds(1165, 700, 75, 75);
                        this.add(Icon3);
                        break;
                    case 4:
                        Icon4.setOpaque(false);
                        Icon4.setBounds(1165, 700, 75, 75);
                        this.add(Icon4);
                        break;
                    case 5:
                        Icon5.setOpaque(false);
                        Icon5.setBounds(1165, 700, 75, 75);
                        this.add(Icon5);
                        break;
                }
            } catch (IOException ex) {
                System.out.print("Image not found");
            }

            //第二个玩家
            shapeFactoryFirst = new ShapeFactory();
            groundFirst = new Ground();
            gamePanelFirst = new GamePanel();
            controllerFirst = new Controller(shapeFactoryFirst, groundFirst, gamePanelFirst);
            gamePanelFirst.setBounds(700, -100, gamePanelFirst.getWidth() + 10, gamePanelFirst.getHeight() + 35);
            gamePanelFirst.addKeyListener(controllerFirst);
            this.addKeyListener(controllerFirst);
            this.add(gamePanelFirst);

            //第一个玩家
            shapeFactorySecond = new ShapeFactory();
            groundSecond = new Ground();
            groundSecond.isSecondPlayer = true;
            gamePanelSecond = new GamePanel(true);
            controllerSecond = new ControllerFor2P(shapeFactorySecond, groundSecond, gamePanelSecond);
            gamePanelSecond.setBounds(50, -100, gamePanelFirst.getWidth() + 10, gamePanelFirst.getHeight() + 35);
            gamePanelSecond.addKeyListener(controllerSecond);
            this.addKeyListener(controllerSecond);
            this.add(gamePanelSecond);
            controllerFirst.newGame();
            controllerSecond.newGame();

            //游戏结束面板控件的实例化
            Background1 = ImageIO.read(new File("ProjectResource\\Background\\GameOverPanelBC.jpg"));
            //  SingleBackRain.Create("ProjectResource\\AnimationEffects\\SingleRain\\RainDemo%02d.png", 40, 1, 25);
            //   score_snow = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreForSnow.png"));


            WinDemo_1P.Create("ProjectResource\\AnimationEffects\\WinDemo\\1PWinDemo\\1PWinDemo%02d.png", 47, 3, 25);
            WinDemo_2P.Create("ProjectResource\\AnimationEffects\\WinDemo\\2PWinDemo\\2PWinDemo%02d.png", 47, 2, 25);
            fpsMonitor.Reset();
            new Thread(new PaintThread()).start();
        } catch (IOException ex) {
            Logger.getLogger(VSGamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void paint(Graphics g) {
        System.gc();
//        super.paintComponent(g);
        fpsMonitor.Update();
        if (!hasDisposed) {
            g.drawImage(Background, 0, 0, 1280, 800, this);//加载背景图片
            super.paint(g);
            Fire_1P.Update(fpsMonitor.GetTimeElapse());
            Fire_2P.Update(fpsMonitor.GetTimeElapse());
            //第二个玩家的蓄力图标
            int temp = tetris.util.Global.score - temp1PScore;
                if(temp >= 90 && temp < 180){
                    Fire_2P.Draw(g, 955, 660, 50, 50);
                }else if(temp >= 180 && temp < 270){
                    Fire_2P.Draw(g, 955, 660, 50, 50);
                    Fire_2P.Draw(g, 975, 620, 50, 50);
                }else if(temp >= 270 && temp < 360){
                    Fire_2P.Draw(g, 955, 660, 50, 50);
                    Fire_2P.Draw(g, 975, 620, 50, 50);
                    Fire_2P.Draw(g, 1030, 620, 50, 50);
                }else if(temp >= 360 && temp < 450){
                    Fire_2P.Draw(g, 955, 660, 50, 50);
                    Fire_2P.Draw(g, 975, 620, 50, 50);
                    Fire_2P.Draw(g, 1030, 620, 50, 50);
                    Fire_2P.Draw(g, 1050, 660, 50, 50);
                }else if(temp >= 450 && temp < 540){
                    Fire_2P.Draw(g, 955, 660, 50, 50);
                    Fire_2P.Draw(g, 975, 620, 50, 50);
                    Fire_2P.Draw(g, 1030, 620, 50, 50);
                    Fire_2P.Draw(g, 1050, 660, 50, 50);
                    Fire_2P.Draw(g, 1030, 705, 50, 50);
                }else if(temp >= 540){
                    Fire_2P.Draw(g, 955, 660, 50, 50);
                    Fire_2P.Draw(g, 975, 620, 50, 50);
                    Fire_2P.Draw(g, 1030, 620, 50, 50);
                    Fire_2P.Draw(g, 1050, 660, 50, 50);
                    Fire_2P.Draw(g, 1030, 705, 50, 50);
                    Fire_2P.Draw(g, 975, 705, 50, 50);
                    temp1PScore = tetris.util.Global.score;
                }
            //第二个玩家的蓄力图标
            int temp2 = tetris.util.Global.score2P - temp2PScore;
            if(temp2 >= 90 && temp2 < 180){
                Fire_1P.Draw(g, 190, 640, 50, 50);
            }else if(temp2 >= 180 && temp2 < 270){
                Fire_1P.Draw(g, 190, 640, 50, 50);
                    Fire_1P.Draw(g, 230, 615, 50, 50);
            }else if(temp2 >= 270 && temp2 < 360){
                Fire_1P.Draw(g, 190, 640, 50, 50);
                    Fire_1P.Draw(g, 230, 615, 50, 50);
                    Fire_1P.Draw(g, 270, 640, 50, 50);
            }else if(temp2 >= 360 && temp2 < 450){
                Fire_1P.Draw(g, 190, 640, 50, 50);
                    Fire_1P.Draw(g, 230, 615, 50, 50);
                    Fire_1P.Draw(g, 270, 640, 50, 50);
                    Fire_1P.Draw(g, 270, 685, 50, 50);
            }else if(temp2 >= 450 && temp2 < 540){
                Fire_1P.Draw(g, 190, 640, 50, 50);
                    Fire_1P.Draw(g, 230, 615, 50, 50);
                    Fire_1P.Draw(g, 270, 640, 50, 50);
                    Fire_1P.Draw(g, 270, 685, 50, 50);
                    Fire_1P.Draw(g, 230, 705, 50, 50);
            }else if(temp2 >= 540){
                Fire_1P.Draw(g, 190, 640, 50, 50);
                    Fire_1P.Draw(g, 230, 615, 50, 50);
                    Fire_1P.Draw(g, 270, 640, 50, 50);
                    Fire_1P.Draw(g, 270, 685, 50, 50);
                    Fire_1P.Draw(g, 230, 705, 50, 50);
                    Fire_1P.Draw(g, 190, 685, 50, 50);
                    temp2PScore = tetris.util.Global.score2P;
            }
        } else {
//            g.setFont(font);
            g.drawImage(Background1, 0, 0, 1280, 800, this);//加载背景图片
            super.paint(g);
            g.setFont(font);
            if (winPlayer == 1) {
                g.drawString(tetris.util.Global.score2P + " VS " + tetris.util.Global.score, 910, 260);
                if (!WinDemo_2P.StopDraw) {    //右面的玩家胜利
                    WinDemo_2P.Update(fpsMonitor.GetTimeElapse());
                }
                WinDemo_2P.One_Draw(g, 30, 400, 400, 400);
            } else if (winPlayer == 2) {
                g.drawString(tetris.util.Global.score2P + " VS " + tetris.util.Global.score, 910, 260);
                if (!WinDemo_1P.StopDraw) {    //左面的玩家胜利
                    WinDemo_1P.Update(fpsMonitor.GetTimeElapse());
                }
                WinDemo_1P.One_Draw(g, 30, 400, 400, 400);
            } else if (winPlayer == 3) {
                g.drawString(tetris.util.Global.score + " VS " + tetris.util.Global.score2P, 910, 260);
            }
        }
    }

    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                //将!hasDisposed放在前面可以保证在clearGame()函数执行之后不会再发生判断controllerFirst时为空的异常
                if (!hasDisposed && (controllerFirst.isGameOver() || controllerSecond.isGameOver()) ) {
                    if (tetris.util.Global.score > tetris.util.Global.score2P) {
                        winPlayer = 1;
                        gamePanelFirst.shape.pause();
                        gamePanelSecond.shape.pause();
                        clearGame();
                    } //玩家2胜利
                    else if (tetris.util.Global.score < tetris.util.Global.score2P) {
                        winPlayer = 2;
                        gamePanelFirst.shape.pause();
                        gamePanelSecond.shape.pause();
                        clearGame();
                    } //玩家1胜利
                    else if (tetris.util.Global.score == tetris.util.Global.score2P) {
                        winPlayer = 3;
                        gamePanelFirst.shape.pause();
                        gamePanelSecond.shape.pause();
                        clearGame();
                    }         //平局
                    VSGamePanel.this.removeAll();
                    clear();
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
        Fire_1P = null;
        Fire_2P = null;
        Background = null;
        Icon1 = null;
        Icon2 = null;
        Icon3 = null;
        Icon4 = null;
        Icon5 = null;
        hasDisposed = true;
    }

    private void clearGame() {
        gamePanelFirst = null;
        shapeFactoryFirst = null;
        groundFirst = null;
        controllerFirst = null;
        gamePanelSecond = null;
        shapeFactorySecond = null;
        groundSecond = null;
        controllerSecond = null;
    }
}
