//进入界面
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
    //StartPanel里的属性

    Image Background;
    Image COLUMNS;
    JLabel single_model = null;
    JLabel vs_model = null;
    JLabel onlinepk_model = null;
    JLabel option = null;
    JLabel exit = null;
    JLabel TheIconforSingle = new JLabel();
    JLabel TheIconforVS = new JLabel();
    JLabel TheIconforOnlinePK = new JLabel();
    JLabel TheIconforOption = new JLabel();
    JLabel TheIconforExit = new JLabel();
    FPSMonitor fpsMonitor = new FPSMonitor();
    int count_for_demo = 0;
    int count_for_demo2 = 0;//用来判断是不是从5到1的!
    int x_for_demo = 600;
    int y_for_demo = -50;//标题动画的路径
    int J_Model = 0;
//    SpriteAnimation LogoDemo = new SpriteAnimation();
//    SpriteAnimation SingleModel_Demo = new SpriteAnimation();
//    SpriteAnimation VS_Demo = new SpriteAnimation();
//    SpriteAnimation OnlinePK_Demo = new SpriteAnimation();
//    SpriteAnimation Option_Demo = new SpriteAnimation();
//    SpriteAnimation Exit_Demo = new SpriteAnimation();
//    SpriteAnimation Arrow = new SpriteAnimation();//箭头
//    SpriteAnimationStream BackSquare = new SpriteAnimationStream();//箭头
    SpriteAnimationStream LogoDemo = new SpriteAnimationStream();
    SpriteAnimationStream SingleModel_Demo = new SpriteAnimationStream();
    SpriteAnimationStream VS_Demo = new SpriteAnimationStream();
    SpriteAnimationStream OnlinePK_Demo = new SpriteAnimationStream();
    SpriteAnimationStream Option_Demo = new SpriteAnimationStream();
    SpriteAnimationStream Exit_Demo = new SpriteAnimationStream();
    SpriteAnimationStream Arrow = new SpriteAnimationStream();//箭头
    SpriteAnimationStream BackSquare = new SpriteAnimationStream();//箭头

    StartPanel() {
        this.setOpaque(false);

        //加载资源
        try {
            Background = ImageIO.read(new File("ProjectResource\\Background\\StartPanel.jpg"));
            LogoDemo.Create("ProjectResource\\AnimationEffects\\LogoDemo\\COLUMNS%02d.png", 90, 1, 17);
            SingleModel_Demo.Create("ProjectResource\\AnimationEffects\\TheSingleModelDemo\\TheSingleModel%02d.png", 45, 1, 25);
            VS_Demo.Create("ProjectResource\\AnimationEffects\\TheVSModelDemo\\TheVSModel%02d.png", 45, 1, 25);
            OnlinePK_Demo.Create("ProjectResource\\AnimationEffects\\TheOnlinePkModelDemo\\TheOnlinePKModel %02d.png", 45, 1, 25);
            Option_Demo.Create("ProjectResource\\AnimationEffects\\OptionDemo\\Option%02d.png", 45, 1, 25);
            Exit_Demo.Create("ProjectResource\\AnimationEffects\\ExitDemo\\EXIT %02d.png", 45, 1, 25);
            Arrow.Create("ProjectResource\\AnimationEffects\\TheArrowDemo\\Arrow%04d.png", 60, 1, 25);
            BackSquare.Create("ProjectResource\\AnimationEffects\\BackSquare2\\BackSquare%02d.png",50, 1, 25);
            single_model = new JLabel(new ImageIcon("ProjectResource\\Background\\TheSingleModel.png"));
            vs_model = new JLabel(new ImageIcon("ProjectResource\\Background\\TheVSModel.png"));
            onlinepk_model = new JLabel(new ImageIcon("ProjectResource\\Background\\TheOnlinePKModel.png"));
            option = new JLabel(new ImageIcon("ProjectResource\\Background\\Option.png"));
            exit = new JLabel(new ImageIcon("ProjectResource\\Background\\EXIT.png"));
            TheIconforSingle = new JLabel(new ImageIcon("ProjectResource\\Icon\\SingleModel.png"));
            TheIconforVS = new JLabel(new ImageIcon("ProjectResource\\Icon\\VSModel.png"));
            TheIconforOnlinePK = new JLabel(new ImageIcon("ProjectResource\\Icon\\OnlinePKModel.png"));
            TheIconforOption = new JLabel(new ImageIcon("ProjectResource\\Icon\\OptionModel.png"));
            TheIconforExit = new JLabel(new ImageIcon("ProjectResource\\Icon\\ExitModel.png"));
        } catch (Exception ex) {
            System.out.println("Error loading image");
        }

        //选项标签
        single_model.setOpaque(false);
        single_model.setBounds(115, 150, 370, 75);
        vs_model.setOpaque(false);
        vs_model.setBounds(115, 240, 370, 75);
        onlinepk_model.setOpaque(false);
        onlinepk_model.setBounds(115, 330, 370, 75);
        option.setOpaque(false);
        option.setBounds(115, 420, 370, 75);
        exit.setOpaque(false);
        exit.setBounds(115, 510, 370, 75);
        TheIconforSingle.setOpaque(false);
        TheIconforSingle.setBounds(700, 450, 450, 450);
        TheIconforSingle.setVisible(false);
        TheIconforVS.setOpaque(false);
        TheIconforVS.setBounds(700, 420, 450, 450);
        TheIconforVS.setVisible(false);
        TheIconforOnlinePK.setOpaque(false);
        TheIconforOnlinePK.setBounds(700, 420, 450, 450);
        TheIconforOnlinePK.setVisible(false);
        TheIconforOption.setOpaque(false);
        TheIconforOption.setBounds(700, 420, 450, 450);
        TheIconforOption.setVisible(false);
        TheIconforExit.setOpaque(false);
        TheIconforExit.setBounds(700, 420, 450, 450);
        TheIconforExit.setVisible(false);

        this.setLayout(null);
        this.add(single_model);
        this.add(vs_model);
        this.add(onlinepk_model);
        this.add(option);
        this.add(exit);
        this.add(TheIconforSingle);
        this.add(TheIconforVS);
        this.add(TheIconforOnlinePK);
        this.add(TheIconforOption);
        this.add(TheIconforExit);
        //开始线程
        new Thread(new PaintThread()).start();
    }

    //响应MainGamePanel的键盘监听，实现对全局变量的改变，控制下一步应该显示的子面板
    public void ControlStartPanel(int keyCode){
        if (keyCode == 1) {
                    if (J_Model < 6) {
                        if (J_Model == 5) {
                            count_for_demo2 = 1;
                            J_Model = 1;
                        } else {
                            J_Model += 1;
                        }
                    }
                    Global.SELECTED_FRAM = J_Model;
                }
                if (keyCode == 2) {
                    if (J_Model != 0) {
                        if (J_Model == 1) {
                            count_for_demo2 = 2;
                            J_Model = 5;
                        } else {
                            J_Model -= 1;
                        }
                    }
                    Global.SELECTED_FRAM = J_Model;
                }
        fpsMonitor.Reset();
          }

    @SuppressWarnings("empty-statement")
    public void paint(Graphics g) {
        fpsMonitor.Update();
        g.drawImage(Background, 0, 0, this);//加载背景图片
//        g.drawString(String.format("%d", fpsMonitor.GetFPS()), 100, 100);
        //播放大标题动画
        LogoDemo.Update(fpsMonitor.GetTimeElapse());
        LogoDemo.Draw(g, x_for_demo, y_for_demo, 540, 380);
        BackSquare.Update(fpsMonitor.GetTimeElapse());
        BackSquare.Draw(g, 0, 0, 600, 800);
        switch (J_Model) {
            case 1:
                 {
                    TheIconforExit.setVisible(false);
                    TheIconforSingle.setVisible(true);
                    TheIconforVS.setVisible(false);
                    exit.setVisible(true);
                    single_model.setVisible(false);
                    vs_model.setVisible(true);
                    SingleModel_Demo.Update(fpsMonitor.GetTimeElapse());
                    SingleModel_Demo.Draw(g, 149, 150);
                    Arrow.Update(fpsMonitor.GetTimeElapse());
                    Arrow.Draw(g, -12, 130);
                }
                break;
            case 2:
                 {
                    TheIconforSingle.setVisible(false);
                    TheIconforVS.setVisible(true);
                    TheIconforOnlinePK.setVisible(false);
                    single_model.setVisible(true);
                    vs_model.setVisible(false);
                    onlinepk_model.setVisible(true);
                    VS_Demo.Update(fpsMonitor.GetTimeElapse());
                    VS_Demo.Draw(g, 162, 240);
                    Arrow.Update(fpsMonitor.GetTimeElapse());
                    Arrow.Draw(g, 2, 220);

                }
                break;
            case 3:
                 {
                    TheIconforVS.setVisible(false);
                    TheIconforOnlinePK.setVisible(true);
                    TheIconforOption.setVisible(false);
                    vs_model.setVisible(true);
                    onlinepk_model.setVisible(false);
                    option.setVisible(true);
                    OnlinePK_Demo.Update(fpsMonitor.GetTimeElapse());
                    OnlinePK_Demo.Draw(g, 99, 330);
                    Arrow.Update(fpsMonitor.GetTimeElapse());
                    Arrow.Draw(g, -22, 310);
                }
                break;
            case 4:
                 {
                    TheIconforOnlinePK.setVisible(false);
                    TheIconforOption.setVisible(true);
                    TheIconforExit.setVisible(false);
                    onlinepk_model.setVisible(true);
                    option.setVisible(false);
                    exit.setVisible(true);
                    Option_Demo.Update(fpsMonitor.GetTimeElapse());
                    Option_Demo.Draw(g, 227, 420);
                    Arrow.Update(fpsMonitor.GetTimeElapse());
                    Arrow.Draw(g, 65, 400);
                }
                break;
            case 5:
                 {
                    TheIconforOption.setVisible(false);
                    TheIconforExit.setVisible(true);
                    TheIconforSingle.setVisible(false);
                    single_model.setVisible(true);
                    option.setVisible(true);
                    exit.setVisible(false);
                    Exit_Demo.Update(fpsMonitor.GetTimeElapse());
                    Exit_Demo.Draw(g, 248, 511);
                    Arrow.Update(fpsMonitor.GetTimeElapse());
                    Arrow.Draw(g, 90, 490);
                }
                break;
        }

        super.paint(g);
    }

    //执行动画的线程
    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



