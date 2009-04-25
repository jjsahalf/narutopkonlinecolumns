//This is the singlemodel panel
package Naruto_PKonline_Columns.view;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

    int J_Option = 1;
    int J_MusicStyle = 1;
    int J_Dif = 1;
    int J_Turn = 1;
    int J_Music = 1;
    int count_for_option = 0;
    SpriteAnimationStream OptionBCDemo = new SpriteAnimationStream();
    FPSMonitor fpsMonitor = new FPSMonitor();
    JLabel BackgoundSwitch = null;
    JLabel Electronic = null;
    JLabel Rain = null;
    JLabel Snow = null;
    JLabel Difficulty = null;
    JLabel Music = null;
    JLabel ElectronicMusic = null;
    JLabel Blues = null;
    JLabel SoulMusic = null;
    JLabel Fighting = null;
    JLabel MusicTurn = null;
    JLabel On = null;
    JLabel Off = null;
    JLabel Return = null;
    JLabel D_Number[] = new JLabel[9];
    JLabel Arrow1 = null;
    JLabel Arrow2 = null;
    JLabel Arrow3 = null;
    JLabel Arrow2_1 = null;
    JLabel Arrow3_1 = null;
    JLabel Arrow2_2 = null;
    JLabel Arrow3_2 = null;
    JLabel Arrow2_3 = null;
    JLabel Arrow3_3 = null;

    public OptionPanel() {
        this.setFocusable(true);
        this.setOpaque(false);
        this.setLayout(null);
        OptionBCDemo.Create("ProjectResource\\AnimationEffects\\OptionBackDemo\\OptionDemo%02d.png", 25, 1, 30);
        BackgoundSwitch = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\backgroundimage.png"));
        Difficulty = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Difficulty.png"));
        Electronic = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\ElectronicExplosion.png"));
        Rain = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Rain.png"));
        Snow = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Snow.png"));
        Music = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Music Switch.png"));
        ElectronicMusic = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\ElectronicMusic.png"));
        Blues = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Blues.png"));
        SoulMusic = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\SoulMusic.png"));
        Fighting = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Fighting.png"));
        MusicTurn = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\MusicTurn.png"));
        On = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\On.png"));
        Off = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Off.png"));
        Return = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Return.png"));
        D_Number[0] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\1.png"));
        D_Number[1] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\2.png"));
        D_Number[2] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\3.png"));
        D_Number[3] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\4.png"));
        D_Number[4] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\5.png"));
        D_Number[5] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\6.png"));
        D_Number[6] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\7.png"));
        D_Number[7] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\8.png"));
        D_Number[8] = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\9.png"));
        Arrow1 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow.png"));
        Arrow2 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow1.png"));
        Arrow3 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow2.png"));
        Arrow2_1 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow1.png"));
        Arrow3_1 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow2.png"));
        Arrow2_2 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow1.png"));
        Arrow3_2 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow2.png"));
        Arrow2_3 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow1.png"));
        Arrow3_3 = new JLabel(new ImageIcon("ProjectResource\\IconForOption\\Arrow2.png"));

        BackgoundSwitch.setOpaque(false);
        BackgoundSwitch.setBounds(22, 270, 500, 100);
        Difficulty.setOpaque(false);
        Difficulty.setBounds(26, 185, 400, 100);
        Music.setOpaque(false);
        Music.setBounds(29, 100, 400, 100);
        ElectronicMusic.setOpaque(false);
        ElectronicMusic.setBounds(504, 100, 400, 100);
        Blues.setOpaque(false);
        Blues.setBounds(504, 100, 400, 100);
        Blues.setVisible(false);
        SoulMusic.setOpaque(false);
        SoulMusic.setBounds(504, 100, 400, 100);
        SoulMusic.setVisible(false);
        Fighting.setOpaque(false);
        Fighting.setBounds(504, 100, 400, 100);
        Fighting.setVisible(false);
        Electronic.setOpaque(false);
        Electronic.setBounds(468, 275, 400, 100);
        Rain.setOpaque(false);
        Rain.setBounds(465, 275, 400, 100);
        Rain.setVisible(false);
        Snow.setOpaque(false);
        Snow.setBounds(465, 275, 400, 100);
        Snow.setVisible(false);
        MusicTurn.setOpaque(false);
        MusicTurn.setBounds(39, 355, 200, 100);
        On.setOpaque(false);
        On.setBounds(305, 359, 200, 100);
        Off.setOpaque(false);
        Off.setBounds(310, 357, 200, 100);
        Off.setVisible(false);
        Return.setOpaque(false);
        Return.setBounds(79, 440, 200, 100);
        D_Number[0].setOpaque(false);
        D_Number[0].setBounds(530, 200, 50, 55);
        D_Number[0].setVisible(true);
        D_Number[1].setOpaque(false);
        D_Number[1].setBounds(540, 200, 50, 55);
        D_Number[1].setVisible(false);
        D_Number[2].setOpaque(false);
        D_Number[2].setBounds(540, 200, 50, 55);
        D_Number[2].setVisible(false);
        D_Number[3].setOpaque(false);
        D_Number[3].setBounds(540, 200, 50, 55);
        D_Number[3].setVisible(false);
        D_Number[4].setOpaque(false);
        D_Number[4].setBounds(540, 200, 50, 55);
        D_Number[4].setVisible(false);
        D_Number[5].setOpaque(false);
        D_Number[5].setBounds(540, 200, 50, 55);
        D_Number[5].setVisible(false);
        D_Number[6].setOpaque(false);
        D_Number[6].setBounds(540, 200, 50, 55);
        D_Number[6].setVisible(false);
        D_Number[7].setOpaque(false);
        D_Number[7].setBounds(540, 200, 50, 55);
        D_Number[7].setVisible(false);
        D_Number[8].setOpaque(false);
        D_Number[8].setBounds(540, 200, 50, 55);
        D_Number[8].setVisible(false);
        Arrow1.setOpaque(false);
        Arrow1.setBounds(20, 123, 100, 50);
        Arrow2.setOpaque(false);
        Arrow2.setBounds(285, 190, 370, 75);
        Arrow3.setOpaque(false);
        Arrow3.setBounds(485, 194, 370, 75);
        Arrow2_1.setOpaque(false);
        Arrow2_1.setBounds(308, 110, 370, 75);
        Arrow3_1.setOpaque(false);
        Arrow3_1.setBounds(732, 114, 370, 75);
        Arrow2_2.setOpaque(false);
        Arrow2_2.setBounds(285, 285, 370, 75);
        Arrow3_2.setOpaque(false);
        Arrow3_2.setBounds(685, 289, 370, 75);
        Arrow2_3.setOpaque(false);
        Arrow2_3.setBounds(135, 369, 370, 75);
        Arrow3_3.setOpaque(false);
        Arrow3_3.setBounds(315, 373, 370, 75);

        this.add(BackgoundSwitch);
        this.add(Difficulty);
        this.add(Music);
        this.add(ElectronicMusic);
        this.add(Blues);
        this.add(SoulMusic);
        this.add(Fighting);
        this.add(Electronic);
        this.add(Rain);
        this.add(Snow);
        this.add(MusicTurn);
        this.add(Return);
        this.add(On);
        this.add(Off);
        this.add(D_Number[0]);
        this.add(D_Number[1]);
        this.add(D_Number[2]);
        this.add(D_Number[3]);
        this.add(D_Number[4]);
        this.add(D_Number[5]);
        this.add(D_Number[6]);
        this.add(D_Number[7]);
        this.add(D_Number[8]);
        this.add(Arrow1);
        this.add(Arrow2);
        this.add(Arrow3);
        this.add(Arrow2_1);
        this.add(Arrow3_1);
        this.add(Arrow2_2);
        this.add(Arrow3_2);
        this.add(Arrow2_3);
        this.add(Arrow3_3);

        fpsMonitor.Reset();
        new Thread(new PaintThread()).start();
        setFocusable(true);
    }

    public void setOption(int keyCode){
        if (keyCode == 1) {    //UpKey
                    if (J_Option != 0) {
                        if (J_Option == 1) {
                            count_for_option = 2;
                            J_Option = 5;
                        } else {
                            J_Option -= 1;
                        }
                    }
                }
                if (keyCode == 2) { //DownKey
                    if (J_Option < 6) {
                        if (J_Option == 5) {
                            count_for_option = 1;
                            J_Option = 1;
                        } else {
                            J_Option += 1;
                        }
                    }
                }
                if (keyCode == 3) {   //LeftKey
                    if (J_Option == 1) {
                        if (J_MusicStyle != 1) {
                            J_MusicStyle -= 1;
                        }
                    }
                    if (J_Option == 2) {
                        if (J_Dif != 1) {
                            J_Dif -= 1;
                        }
                    } else if (J_Option == 4) {
                        if (J_Turn != 1) {
                            J_Turn = 1;
                        }
                    } else if (J_Option == 3) {
                        if (J_Music != 1) {
                            J_Music -= 1;
                        }
                    }
                }
                if (keyCode == 4) {    //RightKey
                    if (J_Option == 1) {
                        if (J_MusicStyle != 4) {
                            J_MusicStyle += 1;
                        }
                    }
                    if (J_Option == 2) {
                        if (J_Dif != 9) {
                            J_Dif += 1;
                        }
                    } else if (J_Option == 4) {
                        if (J_Turn != 2) {
                            J_Turn = 2;
                        }
                    } else if (J_Option == 3) {
                        if (J_Music != 3) {
                            J_Music += 1;
                        }
                    }
                }
                setBounce(J_Option, count_for_option);
                setBounce1(J_Option, J_MusicStyle);
                setBounce2(J_Option, J_Dif);
                setBounce3(J_Option, J_Turn);
                setBounce4(J_Option, J_Music);
    }

    private void setBounce(int J_Option, int count_for_option) {
                Arrow1.setBounds(20, 123 + (J_Option - 1) * 85, 100, 50);
            }

    private void setBounce1(int J_Option, int J_MusicStyle) {
                if (J_Option == 1) {
                    if (J_MusicStyle == 1) {
                        ElectronicMusic.setVisible(true);
                        Blues.setVisible(false);
                    } else if (J_MusicStyle == 2) {
                        ElectronicMusic.setVisible(false);
                        Blues.setVisible(true);
                        SoulMusic.setVisible(false);
                    } else if (J_MusicStyle == 3) {
                        Blues.setVisible(false);
                        SoulMusic.setVisible(true);
                        Fighting.setVisible(false);
                    } else if (J_MusicStyle == 4) {
                        SoulMusic.setVisible(false);
                        Fighting.setVisible(true);
                    }
                }
                Global.MUSICSTYLE = J_MusicStyle;
            }

    private void setBounce2(int J_Option, int J_Dif) {
                if (J_Option == 2) {
                    if (J_Dif == 1) {
                        D_Number[J_Dif].setVisible(false);
                        D_Number[J_Dif - 1].setVisible(true);
                    } else if (J_Dif == 9) {
                        D_Number[J_Dif - 2].setVisible(false);
                        D_Number[J_Dif - 1].setVisible(true);
                    } else {
                        D_Number[J_Dif - 2].setVisible(false);
                        D_Number[J_Dif - 1].setVisible(true);
                        D_Number[J_Dif].setVisible(false);
                    }
                }
                Global.DIFFICULTY = J_Dif;
            }

    private void setBounce3(int J_Option, int J_Turn) {
                if (J_Option == 4) {
                    if (J_Turn == 1) {
                        On.setVisible(true);
                        Off.setVisible(false);
                    } else if (J_Turn == 2) {
                        On.setVisible(false);
                        Off.setVisible(true);
                    }
                }
                Global.MUSICON = true;
            }

    private void setBounce4(int J_Option, int J_Music) {
                if (J_Option == 3) {
                    if (J_Music == 1) {
                        Electronic.setVisible(true);
                        Rain.setVisible(false);
                    } else if (J_Music == 2) {
                        Electronic.setVisible(false);
                        Rain.setVisible(true);
                        Snow.setVisible(false);
                    } else if (J_Music == 3) {
                        Rain.setVisible(false);
                        Snow.setVisible(true);
                    }
                }
                Global.BACKGROUNDSTYLE = J_Music;
            }

    //判断用户是否选择了Return标签
    public boolean Return(){
        return (J_Option == 5);
    }

    public void paint(Graphics g) {
        OptionBCDemo.Update(fpsMonitor.GetTimeElapse());
        OptionBCDemo.Draw(g, 0, 0, 1280, 800);
        fpsMonitor.Update();
        g.drawString(String.format("%d", fpsMonitor.GetFPS()), 100, 100);
        super.paint(g);
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
