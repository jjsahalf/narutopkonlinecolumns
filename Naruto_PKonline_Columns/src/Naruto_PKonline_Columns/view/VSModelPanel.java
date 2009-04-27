//This is the singlemodel panel
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimation;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VSModelPanel extends JPanel {

    SpriteAnimationStream VSBack1 = new SpriteAnimationStream();
    SpriteAnimationStream VSBack2 = new SpriteAnimationStream();
    SpriteAnimationStream VSBack3 = new SpriteAnimationStream();
    SpriteAnimationStream VSBack4 = new SpriteAnimationStream();
    SpriteAnimationStream VSBack5 = new SpriteAnimationStream();
    SpriteAnimationStream VSBack1_b = new SpriteAnimationStream();
    SpriteAnimationStream VSBack2_b = new SpriteAnimationStream();
    SpriteAnimationStream VSBack3_b = new SpriteAnimationStream();
    SpriteAnimationStream VSBack4_b = new SpriteAnimationStream();
    SpriteAnimationStream VSBack5_b = new SpriteAnimationStream();
    SpriteAnimationStream VSUnderDemo = new SpriteAnimationStream();
    SpriteAnimation Arrow = new SpriteAnimation();
    int J_Rool;
    int count_for_demo;
    JLabel Icon1 = null;
    JLabel Icon2 = null;
    JLabel Icon3 = null;
    JLabel Icon4 = null;
    JLabel Icon5 = null;
    JLabel P1 = null;
    JLabel P2 = null;
    FPSMonitor fpsMonitor = new FPSMonitor();

    public VSModelPanel() {
//        this.setFocusable(true);
//        this.requestFocus();
        this.setOpaque(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        Icon1 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Naruto.png"));
        Icon2 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Sasuke.png"));
        Icon3 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Kakasi.png"));
        Icon4 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Sakura.png"));
        Icon5 = new JLabel(new ImageIcon("ProjectResource\\Icon\\Itachi.png"));
        P1 = new JLabel(new ImageIcon("ProjectResource\\Icon\\1p.jpg"));
        P2 = new JLabel(new ImageIcon("ProjectResource\\Icon\\2p.jpg"));

        Icon1.setOpaque(false);
        Icon1.setBounds(115, 650, 370, 75);
        Icon2.setOpaque(false);
        Icon2.setBounds(265, 650, 370, 75);
        Icon3.setOpaque(false);
        Icon3.setBounds(415, 650, 370, 75);
        Icon4.setOpaque(false);
        Icon4.setBounds(565, 650, 370, 75);
        Icon5.setOpaque(false);
        Icon5.setBounds(715, 650, 370, 75);

        P1.setOpaque(false);
        P1.setBounds(55, 700, 70, 70);
        P1.setVisible(true);

        this.add(Icon1);
        this.add(Icon2);
        this.add(Icon3);
        this.add(Icon4);
        this.add(Icon5);
        this.add(P1);
        this.add(P2);

        VSBack1.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 6, 1, 6);
        VSBack2.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 5, 7, 5);
        VSBack3.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 5, 12, 5);
        VSBack4.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 5, 17, 5);
        VSBack5.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 4, 22, 4);
        VSBack1_b.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 6, 6, 6);
        VSBack2_b.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 6, 11, 5);
        VSBack3_b.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 6, 16, 5);
        VSBack4_b.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 6, 21, 5);
        VSBack5_b.Create("ProjectResource\\AnimationEffects\\VSBackDemo\\VSModelBC%02d.png", 5, 25, 4);
        Arrow.Create("ProjectResource\\AnimationEffects\\TheArrow2Demo\\Arrow%04d.png", 10, 1, 5);
        VSUnderDemo.Create("ProjectResource\\AnimationEffects\\VSUnderDemo\\VSUnder%02d.png", 20, 1, 15);
        fpsMonitor.Reset();
        new Thread(new PaintThread()).start();
        setFocusable(true);
    }

    //响应MainGamePanel中的键盘监听，设置玩家选择的角色
    public void selectRole(int keyCode, int roleNumber){
            if (keyCode == 1) {
                    if (J_Rool > 1) {
                        J_Rool -= 1;
                        count_for_demo = 1;
                    }
                    //在全局变量中保存用户所选择的角色;0代表第一个玩家选择角色，1代表第二个玩家选择角色
                    if(roleNumber == 0)
                        Global.TEMPFIRSTPLAYERROLE = J_Rool;
                    else
                        Global.TEMPSECONDPLAYERROLE =  J_Rool;
                }
                if (keyCode == 2) {
                    if (J_Rool < 5) {
                        J_Rool += 1;
                        count_for_demo = 0;
                    }
                    if(roleNumber == 0)
                        Global.TEMPFIRSTPLAYERROLE = J_Rool;
                    else
                        Global.TEMPSECONDPLAYERROLE =  J_Rool;
                }
        }

    public void paint(Graphics g) {
        super.paint(g);
        fpsMonitor.Update();
        g.drawString(String.format("%d", fpsMonitor.GetFPS()), 100, 100);

         VSUnderDemo.Update(fpsMonitor.GetTimeElapse());
         VSUnderDemo.Draw(g, 0, 550,1280,300);
        switch (J_Rool) {
            case 1:
                if (count_for_demo == 0) {
                    VSBack1_b.StopDraw = false;
                    VSBack1_b.timeElapse = 0;
                    if (VSBack1.StopDraw == false) {
                        VSBack1.Update(fpsMonitor.GetTimeElapse());
                    }
                    VSBack1.One_Draw(g, 50, 0, 1160, 600);
                } else if (count_for_demo == 1) {
                    VSBack2.StopDraw = false;
                    VSBack2.timeElapse = 0;
                    if (VSBack2_b.StopDraw == false) {
                        VSBack2_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    VSBack2_b.One_Draw_back(g, 50, 0, 1160, 600);
                }
                Arrow.Update(fpsMonitor.GetTimeElapse());
                Arrow.Draw(g, 255, 720);
                break;
            case 2:
                if (count_for_demo == 0) {
                    VSBack2_b.StopDraw = false;
                    VSBack2_b.timeElapse = 0;
                    if (VSBack2.StopDraw == false) {
                        VSBack2.Update(fpsMonitor.GetTimeElapse());
                    }
                    VSBack2.One_Draw(g, 50, 0, 1160, 600);
                } else if (count_for_demo == 1) {
                    VSBack3.StopDraw = false;
                    VSBack3.timeElapse = 0;
                    if (VSBack3_b.StopDraw == false) {
                        VSBack3_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    VSBack3_b.One_Draw_back(g, 50, 0, 1160, 600);
                }
                Arrow.Update(fpsMonitor.GetTimeElapse());
                Arrow.Draw(g, 405, 720);
                break;
            case 3:
                if (count_for_demo == 0) {
                    VSBack3_b.StopDraw = false;
                    VSBack3_b.timeElapse = 0;
                    if (VSBack3.StopDraw == false) {
                        VSBack3.Update(fpsMonitor.GetTimeElapse());
                    }
                    VSBack3.One_Draw(g, 50, 0, 1160, 600);
                } else if (count_for_demo == 1) {
                    VSBack4.StopDraw = false;
                    VSBack4.timeElapse = 0;
                    if (VSBack4_b.StopDraw == false) {
                        VSBack4_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    VSBack4_b.One_Draw_back(g, 50, 0, 1160, 600);
                }
                Arrow.Update(fpsMonitor.GetTimeElapse());
                Arrow.Draw(g, 555, 720);
                break;
            case 4:
                if (count_for_demo == 0) {
                    VSBack4_b.StopDraw = false;
                    VSBack4_b.timeElapse = 0;
                    if (VSBack4.StopDraw == false) {
                        VSBack4.Update(fpsMonitor.GetTimeElapse());
                    }
                    VSBack4.One_Draw(g, 50, 0, 1160, 600);
                } else if (count_for_demo == 1) {
                    VSBack5.StopDraw = false;
                    VSBack5.timeElapse = 0;
                    if (VSBack5_b.StopDraw == false) {
                        VSBack5_b.Update_back(fpsMonitor.GetTimeElapse());
                    }
                    VSBack5_b.One_Draw_back(g, 50, 0, 1160, 600);
                }
                Arrow.Update(fpsMonitor.GetTimeElapse());
                Arrow.Draw(g, 715, 720);
                break;
            case 5:
                if (count_for_demo == 0) {
                    VSBack5_b.StopDraw = false;
                    VSBack5_b.timeElapse = 0;
                    if (VSBack5.StopDraw == false) {
                        VSBack5.Update(fpsMonitor.GetTimeElapse());
                    }
                    VSBack5.One_Draw(g, 50, 0, 1160, 600);
                }
                Arrow.Update(fpsMonitor.GetTimeElapse());
                Arrow.Draw(g, 865, 720);
                break;
        }
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
}
