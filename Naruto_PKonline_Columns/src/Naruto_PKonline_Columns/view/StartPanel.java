//进入界面
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
    //StartPanel里的属性
    FPSMonitor fpsMonitor = new FPSMonitor();
    private BufferedImage Background;
    private BufferedImage Background_down1;
    private BufferedImage Background_down2;
    private BufferedImage Background_down3;
    private BufferedImage Background_down4;
    private BufferedImage Background_down5;
    private BufferedImage Background_down6;
    SpriteAnimationStream GlowSpereDemo = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelLight = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Single = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_VS = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Online = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Option = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Replay = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Exit = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Single_b = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_VS_b = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Online_b = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Option_b = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Replay_b = new SpriteAnimationStream();
    SpriteAnimationStream MainPanelBCDemo_Exit_b = new SpriteAnimationStream();
    int count_for_demo = 0;
    int count_for_demo2 = 1;
    int J_Model = 1;

    StartPanel() {
//        this.setFocusable(true);
        try {
            this.setLayout(null);
            this.setBackground(Color.BLACK);
            Background = ImageIO.read(new File("ProjectResource\\Background\\MainBCBook.jpg"));
            Background_down1 = ImageIO.read(new File("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo001.jpg"));
            Background_down2 = ImageIO.read(new File("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo021.jpg"));
            Background_down3 = ImageIO.read(new File("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo041.jpg"));
            Background_down4 = ImageIO.read(new File("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo061.jpg"));
            Background_down5 = ImageIO.read(new File("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo081.jpg"));
            Background_down6 = ImageIO.read(new File("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo101.jpg"));
            GlowSpereDemo.Create("ProjectResource\\AnimationEffects\\GlowSpereDemo\\GlowSphereDemo%03d.jpg", 250, 1, 25);
            MainPanelBCDemo.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 125, 1, 25);
            MainPanelLight.Create("ProjectResource\\AnimationEffects\\MainPanelLight\\MainPanelLight%02d.png", 50, 1, 25);

            MainPanelBCDemo_Single.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 1, 25);
            MainPanelBCDemo_VS.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 22, 25);
            MainPanelBCDemo_Online.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 21, 41, 25);
            MainPanelBCDemo_Option.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 61, 25);
            MainPanelBCDemo_Replay.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 81, 25);
            MainPanelBCDemo_Exit.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 101, 25);

            MainPanelBCDemo_Single_b.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 21, 21, 25);
            MainPanelBCDemo_VS_b.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 41, 25);
            MainPanelBCDemo_Online_b.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 61, 25);
            MainPanelBCDemo_Option_b.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 81, 25);
            MainPanelBCDemo_Replay_b.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 101, 25);
            MainPanelBCDemo_Exit_b.Create("ProjectResource\\AnimationEffects\\MainPanelBCDemo\\MainPanelBCDemo%03d.jpg", 22, 121, 25);

            fpsMonitor.Reset();
            new Thread(new PaintThread()).start();
//            setFocusable(true);
        } catch (IOException ex) {
            System.out.print("Image not found");
        }
    }

    //响应MainGamePanel的键盘监听，实现对全局变量的改变，控制下一步应该显示的子面板
    public void ControlStartPanel(int keyCode) {
        if (keyCode == 1) {
            if (J_Model < 7) {
                if (J_Model == 6) {
                    count_for_demo = 1;
                    count_for_demo2 = 1;
                    J_Model = 1;
                } else {
                    J_Model += 1;
                    count_for_demo2 = 1;
                    count_for_demo = 1;
                }
            }
            Global.SELECTED_FRAM = J_Model;
        }
        if (keyCode == 2) {
            if (J_Model != 0) {
                if (J_Model == 1) {
                    count_for_demo = 2;
                    count_for_demo2 = 2;
                    J_Model = 6;
                } else {
                    J_Model -= 1;
                    count_for_demo2 = 2;
                    count_for_demo = 2;
                }
            }
            Global.SELECTED_FRAM = J_Model;
        }
        fpsMonitor.Reset();
    }

    @SuppressWarnings("empty-statement")
    public void paint(Graphics g) {
        super.paint(g);
        fpsMonitor.Update();
        g.drawImage(Background, 0, 0, 1280, 300, this);//加载背景图片
        g.drawString(String.format("%d", fpsMonitor.GetFPS()), 100, 100);
        GlowSpereDemo.Update(fpsMonitor.GetTimeElapse());
        GlowSpereDemo.Draw(g, 480, 20, 320, 240);
        MainPanelLight.Update(fpsMonitor.GetTimeElapse());
        MainPanelLight.Draw(g, 0, -50, 1280, 400);
        //MainPanelBCDemo.Update(fpsMonitor.GetTimeElapse());
        //MainPanelBCDemo.Draw(g, 0, 280, 1280, 500);
        switch (J_Model) {
            case 1:
                MainPanelBCDemo_Exit_b.StopDraw = false;
                MainPanelBCDemo_Exit_b.timeElapse = 0;
                MainPanelBCDemo_Single.StopDraw = false;
                MainPanelBCDemo_Single.timeElapse = 0;
                MainPanelBCDemo_VS_b.StopDraw = false;
                MainPanelBCDemo_VS_b.timeElapse = 0;
                MainPanelBCDemo_Replay.StopDraw = false;
                MainPanelBCDemo_Replay.timeElapse = 0;
                if (count_for_demo2 == 1) {
                    if (count_for_demo == 1) {
                        if (!MainPanelBCDemo_Exit.StopDraw) {
                            MainPanelBCDemo_Exit.Update(fpsMonitor.GetTimeElapse());
                        }
                        MainPanelBCDemo_Exit.IsDrawAgain = true;
                        MainPanelBCDemo_Exit.One_Draw(g, 0, 280, 1280, 500);
                    } else {
                        g.drawImage(Background_down1, 0, 280, 1280, 500, this);//加载背景图片
                    }
                } else if (count_for_demo2 == 2) {
                    if (!MainPanelBCDemo_Single_b.StopDraw) {
                        MainPanelBCDemo_Single_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Single_b.IsDrawAgain = true;
                    MainPanelBCDemo_Single_b.One_Draw_back(g, 0, 280, 1280, 500);
                }
                break;
            case 2:
                MainPanelBCDemo_Single_b.StopDraw = false;
                MainPanelBCDemo_Single_b.timeElapse = 0;
                MainPanelBCDemo_VS.StopDraw = false;
                MainPanelBCDemo_VS.timeElapse = 0;
                MainPanelBCDemo_Online_b.StopDraw = false;
                MainPanelBCDemo_Online_b.timeElapse = 0;
                MainPanelBCDemo_Exit.StopDraw = false;
                MainPanelBCDemo_Exit.timeElapse = 0;
                if (count_for_demo2 == 1) {
                    if (!MainPanelBCDemo_Single.StopDraw) {
                        MainPanelBCDemo_Single.Update(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Single.IsDrawAgain = true;
                    MainPanelBCDemo_Single.One_Draw(g, 0, 280, 1280, 500);
                } else if (count_for_demo2 == 2) {
                    if (!MainPanelBCDemo_VS_b.StopDraw) {
                        MainPanelBCDemo_VS_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_VS_b.IsDrawAgain = true;
                    MainPanelBCDemo_VS_b.One_Draw_back(g, 0, 280, 1280, 500);
                }
                break;
            case 3:
                MainPanelBCDemo_VS_b.StopDraw = false;
                MainPanelBCDemo_VS_b.timeElapse = 0;
                MainPanelBCDemo_Online.StopDraw = false;
                MainPanelBCDemo_Online.timeElapse = 0;
                MainPanelBCDemo_Option_b.StopDraw = false;
                MainPanelBCDemo_Option_b.timeElapse = 0;
                MainPanelBCDemo_Single.StopDraw = false;
                MainPanelBCDemo_Single.timeElapse = 0;
                if (count_for_demo2 == 1) {
                    if (!MainPanelBCDemo_VS.StopDraw) {
                        MainPanelBCDemo_VS.Update(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_VS.IsDrawAgain = true;
                    MainPanelBCDemo_VS.One_Draw(g, 0, 280, 1280, 500);
                } else if (count_for_demo2 == 2) {
                    if (!MainPanelBCDemo_Online_b.StopDraw) {
                        MainPanelBCDemo_Online_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Online_b.IsDrawAgain = true;
                    MainPanelBCDemo_Online_b.One_Draw_back(g, 0, 280, 1280, 500);
                }
                break;
            case 4:
                MainPanelBCDemo_Online_b.StopDraw = false;
                MainPanelBCDemo_Online_b.timeElapse = 0;
                MainPanelBCDemo_Option.StopDraw = false;
                MainPanelBCDemo_Option.timeElapse = 0;
                MainPanelBCDemo_Replay_b.StopDraw = false;
                MainPanelBCDemo_Replay_b.timeElapse = 0;
                MainPanelBCDemo_VS.StopDraw = false;
                MainPanelBCDemo_VS.timeElapse = 0;
                if (count_for_demo2 == 1) {
                    if (!MainPanelBCDemo_Online.StopDraw) {
                        MainPanelBCDemo_Online.Update(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Online.IsDrawAgain = true;
                    MainPanelBCDemo_Online.One_Draw(g, 0, 280, 1280, 500);
                } else if (count_for_demo2 == 2) {
                    if (!MainPanelBCDemo_Option_b.StopDraw) {
                        MainPanelBCDemo_Option_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Option_b.IsDrawAgain = true;
                    MainPanelBCDemo_Option_b.One_Draw_back(g, 0, 280, 1280, 500);
                }
                break;
            case 5:
                MainPanelBCDemo_Option_b.StopDraw = false;
                MainPanelBCDemo_Option_b.timeElapse = 0;
                MainPanelBCDemo_Replay.StopDraw = false;
                MainPanelBCDemo_Replay.timeElapse = 0;
                MainPanelBCDemo_Exit_b.StopDraw = false;
                MainPanelBCDemo_Exit_b.timeElapse = 0;
                MainPanelBCDemo_Online.StopDraw = false;
                MainPanelBCDemo_Online.timeElapse = 0;
                if (count_for_demo2 == 1) {
                    if (!MainPanelBCDemo_Option.StopDraw) {
                        MainPanelBCDemo_Option.Update(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Option.IsDrawAgain = true;
                    MainPanelBCDemo_Option.One_Draw(g, 0, 280, 1280, 500);
                } else if (count_for_demo2 == 2) {
                    if (!MainPanelBCDemo_Replay_b.StopDraw) {
                        MainPanelBCDemo_Replay_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Replay_b.IsDrawAgain = true;
                    MainPanelBCDemo_Replay_b.One_Draw_back(g, 0, 280, 1280, 500);
                }
                break;
            case 6:
                MainPanelBCDemo_Replay_b.StopDraw = false;
                MainPanelBCDemo_Replay_b.timeElapse = 0;
                MainPanelBCDemo_Single_b.StopDraw = false;
                MainPanelBCDemo_Single_b.timeElapse = 0;
                MainPanelBCDemo_Option.StopDraw = false;
                MainPanelBCDemo_Option.timeElapse = 0;
                MainPanelBCDemo_Exit.StopDraw = false;
                MainPanelBCDemo_Exit.timeElapse = 0;
                if (count_for_demo2 == 1) {
                    if (!MainPanelBCDemo_Replay.StopDraw) {
                        MainPanelBCDemo_Replay.Update(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Replay.IsDrawAgain = true;
                    MainPanelBCDemo_Replay.One_Draw(g, 0, 280, 1280, 500);
                } else if (count_for_demo2 == 2) {
                    if (!MainPanelBCDemo_Exit_b.StopDraw) {
                        MainPanelBCDemo_Exit_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    MainPanelBCDemo_Exit_b.IsDrawAgain = true;
                    MainPanelBCDemo_Exit_b.One_Draw_back(g, 0, 280, 1280, 500);
                }
                break;
        }
    }

    //执行动画的线程
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
}



