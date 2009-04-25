//This is the singlemodel panel
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
    public boolean isGameOver = false;
    public boolean hasDisposed = false;

    public SingleModelPanel() throws IOException {
        Global.ISPKONLINE = false;
        this.setOpaque(true);
        this.setLayout(null);
        if(!hasDisposed){
            try {
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
            Next_snow.setBounds(830, 450, 350, 200);

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
        }
        fpsMonitor.Reset();
        this.setSize(gamePanel.getWidth() + 10, gamePanel.getHeight() + 35);
        gamePanel.setBounds(427, 72 - tetris.util.Global.CELL_SIZE * 3 - 30, gamePanel.getWidth() + 10, gamePanel.getHeight() + 35);
        this.add(gamePanel);
        gamePanel.addKeyListener(controller);
        this.addKeyListener(controller);
//		frame.setUndecorated(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d.width, d.height);
        controller.newGame();
        PaintThread newThread = new PaintThread();
        thread = new Thread(newThread);
        thread.start();
//                new Thread(new PaintThread()).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        fpsMonitor.Update();
        if (!hasDisposed) {
            if (Global.BACKGROUNDSTYLE == 1) {
                g.drawImage(Background1, 0, 0, 1280, 800, this);//加载背景图片
                SingleBackThunder.Update(fpsMonitor.GetTimeElapse());
                SingleBackThunder.Draw(g, 0, 0, 585, 790);
            } else if (Global.BACKGROUNDSTYLE == 2) {
                g.drawImage(Background2, 0, 0, this);//加载背景图片
                SingleBackRain.Update(fpsMonitor.GetTimeElapse());
                SingleBackRain.Draw(g, 360, 0, 585, 790);
            } else if (Global.BACKGROUNDSTYLE == 3) {
                g.drawImage(Background3, 0, 0, 1280, 800, this);//加载背景图片
                SingleBackSnow.Update(fpsMonitor.GetTimeElapse());
                SingleBackSnow.Draw(g, 330, 0, 630, 800);
            }
        }
        g.drawString(String.format("%d", fpsMonitor.GetFPS()), 100, 100);
    }

    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                if (controller.isGameOver() && !hasDisposed) {
                    SingleModelPanel.this.removeAll();
                    clear();
                    gameOverPanel = new GameOverPanel();
                    SingleModelPanel.this.add(gameOverPanel);
                    repaint();
//                    thread.stop();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clear() {
        Background1 = null;
        Background2 = null;
        Background3 = null;
        SingleBackThunder = null;
        SingleBackRain = null;
        SingleBackSnow = null;
        hasDisposed = true;
    }
}
