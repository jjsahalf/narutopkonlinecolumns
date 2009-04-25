package Naruto_PKonline_Columns.view;
//整个游戏的主Frame,连接各种Panel
//包括StartPanel,ChoosePanel,OnlinePanel,GamePanel

import Naruto_PKonline_Columns.GraphicsControl.Global;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.swing.JFrame;

public class MainGamePanel extends JFrame implements ControllerListener{

    private StartPanel startPanel;
    private SingleModelPanel singleModelPanel;
    private VSModelPanel vsModelPanel;
    private OptionPanel optionPanel;
    private static int roleTime = 0; //记录玩家在选择角色时按下Enter键的次数，基数代表玩家1，偶数代表玩家2选角色

    private Player anthem_player;

    public static void main(String[] args) {
        new MainGamePanel();
    }

    MainGamePanel()  {
        //全屏游戏的设置
         try {
            anthem_player = Manager.createPlayer(new MediaLocator(("file:AudioClip\\anthem.mp3")));
        } catch (java.io.IOException e2) {
            System.out.println(e2 + "IOException");
            return;
        } catch (NoPlayerException e2) {
            System.out.println("backplayer is null.");
            return;
        }
        if (anthem_player == null) {
            System.out.println("backplayer is null.");
            return;
        }
        anthem_player.addControllerListener(this);
        anthem_player.start();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int x = (int) screenSize.getWidth();
        int y = (int) screenSize.getHeight();
        setSize(x,y);
        setLocation(0, 0);//全屏游戏
        setResizable(false);//不能改变窗口的大小
        setUndecorated(true);
        //加载鼠标图像
        try {
            Cursor cursor = kit.createCustomCursor(
                    ImageIO.read(new File("ProjectResource\\Cursor\\Cursor.png")),
                    new Point(0,0), "Arrow");
            setCursor(cursor);
        } catch (IOException ex) {
            System.out.println("Error loading image");
        }
        //定义开始面板，即主界面
        startPanel = new StartPanel();
        this.add(startPanel);
        this.setVisible(true);
        //设置主界面聚焦
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_S){
                    anthem_player.stop();
                }

                if(e.getKeyCode() == KeyEvent.VK_UP){
                    startPanel.ControlStartPanel(2);
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    startPanel.ControlStartPanel(1);
                }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    switch(Global.SELECTED_FRAM){

                        //添加单人模式面板
                        case 1: MainGamePanel.this.remove(startPanel);
                    try {
//                        startPanel = null;
//                        System.gc();
                        singleModelPanel = new SingleModelPanel();
                    } catch (IOException ex) {
                        Logger.getLogger(MainGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        MainGamePanel.this.add(singleModelPanel);
                        MainGamePanel.this.setVisible(true);
                        MainGamePanel.this.setFocusable(false);
                        singleModelPanel.addKeyListener(new KeyAdapter(){
                        @Override
                            public void keyPressed(KeyEvent e){
                                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                                    MainGamePanel.this.setFocusable(true);
                                    MainGamePanel.this.remove(singleModelPanel);
                                    MainGamePanel.this.add(startPanel);
//                                    singleModelPanel.setFocusable(false);
 //                                   singleModelPanel.gamePanel.backplayer.stop();
                                try {
                                    singleModelPanel.gamePanel.shape.pause();
                                } catch (Exception ex) {
                                    Logger.getLogger(MainGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    singleModelPanel  = null;
                                    System.gc();
                                }
                            }
                        });
                        break;

                        //添加对战模式面板
                        case 2: MainGamePanel.this.remove(startPanel);
//                        startPanel = null;
//                        System.gc();
                        vsModelPanel = new VSModelPanel();
                        MainGamePanel.this.add(vsModelPanel);
                        MainGamePanel.this.setVisible(true);
                        MainGamePanel.this.setFocusable(false);
                        //对选择角色的面板进行键盘监听
                        vsModelPanel.addKeyListener(new KeyAdapter(){
                        @Override
                            public void keyPressed(KeyEvent e){
                                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                                    vsModelPanel.selectRole(1,roleTime%2);
                                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                                    vsModelPanel.selectRole(2, roleTime%2);
                                else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    roleTime ++;
                                    if(roleTime%2==1){
                                        Global.FIRSTPLAYERROLE = Global.TEMPFIRSTPLAYERROLE;
                                        vsModelPanel.P1.setVisible(false);
                                        vsModelPanel.P2.setOpaque(false);
                                        vsModelPanel.P2.setBounds(55, 700, 70, 70);
                                        vsModelPanel.P2.setVisible(true);
                                        vsModelPanel.repaint();
                                    }
                                    else{
                                        Global.SECONDPLAYERROLE = Global.TEMPSECONDPLAYERROLE;
                                        vsModelPanel.P2.setVisible(false);
                                        vsModelPanel.P1.setOpaque(false);
                                        vsModelPanel.P1.setBounds(55, 700, 70, 70);
                                        vsModelPanel.P1.setVisible(true);
                                        vsModelPanel.repaint();
                                    }
                                }
                                //返回上一级
                                else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                                    roleTime = 0;
                                    MainGamePanel.this.setFocusable(true);
                                    MainGamePanel.this.remove(vsModelPanel);
                                    MainGamePanel.this.add(startPanel);
//                                    vsModelPanel.setFocusable(false);
                                    vsModelPanel  = null;
                                    System.gc();
                                }
                            }
                        });
                        break;

                        case 3: MainGamePanel.this.remove(startPanel);
//                        tetris.util.Global.ISPKONLINE = true;

                        case 4: MainGamePanel.this.remove(startPanel);
                        optionPanel = new OptionPanel();
                        MainGamePanel.this.add(optionPanel);
                        MainGamePanel.this.setVisible(true);
                        MainGamePanel.this.setFocusable(false);
                        optionPanel.addKeyListener(new KeyAdapter(){
                            public void keyPressed(KeyEvent e){
                                if(e.getKeyCode() == KeyEvent.VK_UP){
                                    optionPanel.setOption(1);
                                }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                                    optionPanel.setOption(2);
                                }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                                    optionPanel.setOption(3);
                                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                                    optionPanel.setOption(4);
                                }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    if(optionPanel.Return()){
                                        MainGamePanel.this.setFocusable(true);
                                        MainGamePanel.this.remove(optionPanel);
                                        MainGamePanel.this.add(startPanel);
                                        optionPanel  = null;
                                        System.gc();
                                    }
                                }
                            }
                        });
                        break;

                        //退出游戏
                        case 5: System.exit(0);
                        break;
                    }
                }
                MainGamePanel.this.repaint();
            }
        });
    }

    public void controllerUpdate(ControllerEvent e) {
          if (e instanceof EndOfMediaEvent) {
            //System.out.println("play music");
            anthem_player.setMediaTime(new Time(0));
            anthem_player.start();
        }
        return;
    }

    }

