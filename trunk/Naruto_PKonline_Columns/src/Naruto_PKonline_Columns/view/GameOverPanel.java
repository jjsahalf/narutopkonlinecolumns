//This is the singlemodel panel
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverPanel extends JPanel {

    //SpriteAnimationStream SingleBackThunder = new SpriteAnimationStream();
    //Image Background1;
    FPSMonitor fpsMonitor = new FPSMonitor();
    private BufferedImage Background;
    JLabel YourScore = null;
    JLabel ScoreList = null;
    JLabel List = null;
    SpriteAnimationStream WinDemo_1P = new SpriteAnimationStream();
    SpriteAnimationStream WinDemo_2P = new SpriteAnimationStream();

    public Thread thread;

    public GameOverPanel() {
        try {
            this.setOpaque(false);
            this.setLayout(null);
            Background = ImageIO.read(new File("ProjectResource\\Background\\GameOverPanelBC.jpg"));
            //  SingleBackRain.Create("ProjectResource\\AnimationEffects\\SingleRain\\RainDemo%02d.png", 40, 1, 25);
            //   score_snow = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreForSnow.png"));

            YourScore = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreForGameOver.png"));
            ScoreList = new JLabel(new ImageIcon("ProjectResource\\Icon\\ScoreListForGameOver.png"));
            List = new JLabel(new ImageIcon("ProjectResource\\Icon\\ListForGameOver.png"));

            WinDemo_1P.Create("ProjectResource\\AnimationEffects\\WinDemo\\1PWinDemo\\1PWinDemo%02d.png", 47, 3, 25);
            WinDemo_2P.Create("ProjectResource\\AnimationEffects\\WinDemo\\2PWinDemo\\2PWinDemo%02d.png", 47, 2, 25);
            //  combo_thunder.setOpaque(false);
            //  combo_thunder.setBounds(530, 690, 300, 100);
            //  score_thunder.setOpaque(false);
            YourScore.setOpaque(false);
            YourScore.setBounds(830, 100, 400, 100);
            ScoreList.setOpaque(false);
            ScoreList.setBounds(830, 280, 350, 100);
            List.setOpaque(false);
            List.setBounds(710, 380, 250, 400);

            this.add(YourScore);
            this.add(ScoreList);
            this.add(List);


            fpsMonitor.Reset();
            thread = new Thread(new PaintThread());
            thread.start();
            setFocusable(true);
        } catch (IOException ex) {
            System.out.print("Image not found");
        }
    }

    public void paint(Graphics g) {

        fpsMonitor.Update();
        g.drawImage(Background, 0, 0, 1280, 800, this);//加载背景图片
        g.drawString(String.format("%d", fpsMonitor.GetFPS()), 100, 100);

        super.paint(g);
   //     if (!WinDemo_1P.StopDraw) {
   //         WinDemo_1P.Update(fpsMonitor.GetTimeElapse());
   //     }
  //      WinDemo_1P.One_Draw(g, 30, 400, 400, 400);

        if (!WinDemo_2P.StopDraw) {
            WinDemo_2P.Update(fpsMonitor.GetTimeElapse());
        }
        WinDemo_2P.One_Draw(g, 30, 400, 400, 400);
    }

    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static void main(String[] args){
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1280,800);
//        JPanel panel = new JPanel();
//        panel.setSize(1280,800);
//        panel.setLayout(null);
//        panel.setOpaque(false);
//        panel.add(new GameOverPanel());
//        frame.add(panel);
//        frame.setVisible(true);
//    }
}
