package Naruto_PKonline_Columns.view;
//整个游戏的主Frame,连接各种Panel
//包括StartPanel,ChoosePanel,OnlinePanel,GamePanel
import Naruto_PKonline_Columns.GraphicsControl.Global;
import columns.client.ColumnsClient;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainGamePanel extends JFrame {

    private StartPanel startPanel;
    private SingleModelPanel singleModelPanel;
    private VSModelPanel vsModelPanel;
    private OptionPanel optionPanel;
    private GameOverPanel gameOverPanel;
    private VSGamePanel vsGamePanel;
    private static int roleTime = 0; //记录玩家在选择角色时按下Enter键的次数，基数代表玩家1，偶数代表玩家2选角色
    private ColumnsClient columnsClient;
    public Timer timer;

    public static void main(String[] args) {
        new MainGamePanel();
    }

    MainGamePanel() {
        //全屏游戏的设置
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int x = (int) screenSize.getWidth();
        int y = (int) screenSize.getHeight();
        setSize(x, y);
        setLocation(0, 0);//全屏游戏
        setResizable(false);//不能改变窗口的大小
        setUndecorated(true);
        //加载鼠标图像
        try {
            Cursor cursor = kit.createCustomCursor(
                    ImageIO.read(new File("ProjectResource\\Cursor\\Cursor.png")),
                    new Point(0, 0), "Arrow");
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
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    startPanel.ControlStartPanel(2);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    startPanel.ControlStartPanel(1);
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    switch (Global.SELECTED_FRAM) {

                        //添加单人模式面板
                        case 1:
                            MainGamePanel.this.remove(startPanel);
                            tetris.util.Global.WIDTH = 9;
                            tetris.util.Global.HEIGHT = 16;
                            tetris.util.Global.score = 0;
                            tetris.util.Global.score2P = 0;
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
                            singleModelPanel.addKeyListener(new KeyAdapter() {

                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                        MainGamePanel.this.setFocusable(true);
                                        MainGamePanel.this.remove(singleModelPanel);
                                        MainGamePanel.this.add(startPanel);
//                                    singleModelPanel.setFocusable(false);
//                                        singleModelPanel.gamePanel.backplayer.stop();
                                        if (singleModelPanel.gamePanel != null) {
                                            try {
                                                singleModelPanel.gamePanel.shape.pause();
                                                tetris.util.Global.shapesPrint.close();
                                                tetris.util.Global.actionPrint.close();
                                            } catch (Exception ex) {
                                                Logger.getLogger(MainGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        singleModelPanel = null;
                                        System.gc();
                                    }
                                }
                            });
                            break;

                        //添加对战模式面板
                        case 2:
                            MainGamePanel.this.remove(startPanel);
                            tetris.util.Global.WIDTH = 7;
                            tetris.util.Global.HEIGHT = 14;
                            tetris.util.Global.score = 0;
                            tetris.util.Global.score2P = 0;
//                        startPanel = null;
//                        System.gc();
                            vsModelPanel = new VSModelPanel();
                            MainGamePanel.this.add(vsModelPanel);
                            MainGamePanel.this.setVisible(true);
                            MainGamePanel.this.setFocusable(false);
                            //对选择角色的面板进行键盘监听
                            vsModelPanel.setFocusable(true);
                            vsModelPanel.addKeyListener(new KeyAdapter() {

                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                                        vsModelPanel.selectRole(1, roleTime % 2);
                                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                                        vsModelPanel.selectRole(2, roleTime % 2);
                                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        roleTime++;
                                        if (roleTime == 1) {
                                            Global.FIRSTPLAYERROLE = Global.TEMPFIRSTPLAYERROLE;
                                            vsModelPanel.P1.setVisible(false);
                                            vsModelPanel.P2.setOpaque(false);
                                            vsModelPanel.P2.setBounds(55, 700, 70, 70);
                                            vsModelPanel.P2.setVisible(true);
                                            vsModelPanel.repaint();
                                        } else if (roleTime == 2) {
                                            Global.SECONDPLAYERROLE = Global.TEMPSECONDPLAYERROLE;
                                            vsModelPanel.P2.setVisible(false);
                                            vsModelPanel.P1.setOpaque(false);
                                            vsModelPanel.P1.setBounds(55, 700, 70, 70);
                                            vsModelPanel.P1.setVisible(true);
                                            vsModelPanel.repaint();
                                            vsGamePanel = new VSGamePanel();
                                            MainGamePanel.this.setFocusable(false);
                                            MainGamePanel.this.remove(vsModelPanel);
                                            MainGamePanel.this.add(vsGamePanel);
                                            vsModelPanel.setFocusable(false);
                                            vsGamePanel.requestFocus();
                                            vsGamePanel.setFocusable(true);
                                            vsGamePanel.addKeyListener(new KeyAdapter() {

                                                public void keyPressed(KeyEvent e) {
                                                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                                        roleTime = 0;
                                                        MainGamePanel.this.setFocusable(true);
                                                        //防止玩家在游戏进行过程中跳出游戏
                                                        if (vsGamePanel.gamePanelFirst != null && vsGamePanel.gamePanelSecond != null) {
                                                            try {
                                                                vsGamePanel.gamePanelFirst.shape.pause();
                                                                vsGamePanel.gamePanelSecond.shape.pause();
                                                            } catch (Exception ex) {
                                                                Logger.getLogger(MainGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                        }
                                                        MainGamePanel.this.remove(vsGamePanel);
                                                        MainGamePanel.this.add(startPanel);
                                                        vsGamePanel = null;
                                                        System.gc();
                                                    }
                                                }
                                            });
                                        } else {
                                        }
                                    } //返回上一级
                                    else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                        roleTime = 0;
                                        MainGamePanel.this.setFocusable(true);
                                        MainGamePanel.this.remove(vsModelPanel);
                                        MainGamePanel.this.add(startPanel);
//                                    vsModelPanel.setFocusable(false);
                                        vsModelPanel = null;
                                        System.gc();
                                    }
                                }
                            });
                            break;

                        case 3:
                            tetris.util.Global.score = 0;
                            tetris.util.Global.score2P = 0;
                            tetris.util.Global.WIDTH = 7;
                            tetris.util.Global.HEIGHT = 14;
                            MainGamePanel.this.remove(startPanel);
                            Global.ISPKONLINE = true;
                            vsModelPanel = new VSModelPanel();
                            MainGamePanel.this.add(vsModelPanel);
                            MainGamePanel.this.setVisible(true);
                            MainGamePanel.this.setFocusable(false);
                            vsModelPanel.setFocusable(true);
                            //对选择角色的面板进行键盘监听
                            vsModelPanel.addKeyListener(new KeyAdapter() {

                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                                        vsModelPanel.selectRole(1, roleTime % 2);
                                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                                        vsModelPanel.selectRole(2, roleTime % 2);
                                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        Global.FIRSTPLAYERROLE = Global.TEMPFIRSTPLAYERROLE;
                                        vsModelPanel.repaint();
                                        try {
                                            columnsClient = new ColumnsClient();
                                        } catch (IOException ex) {
                                            Logger.getLogger(MainGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        vsModelPanel.setFocusable(false);
                                        MainGamePanel.this.remove(vsModelPanel);
                                        MainGamePanel.this.add(columnsClient);
                                        MainGamePanel.this.setVisible(true);
                                        columnsClient.userInputPad.contentInputted.requestFocus(true);
                                        //为了聊天时可以返回选人界面
                                        columnsClient.userInputPad.contentInputted.addKeyListener(new KeyAdapter() {

                                            @Override
                                            public void keyPressed(KeyEvent e) {
                                                //只有游戏结束时才可以退出
                                                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                                    MainGamePanel.this.remove(columnsClient);
                                                    MainGamePanel.this.add(vsModelPanel);
                                                    vsModelPanel.setFocusable(true);
                                                    vsModelPanel.requestFocus();
                                                }
                                            }
                                        });

//                                        columnsClient.connectButton.addMouseListener(new MouseAdapter() {
//
//                                            public void mousePressed(MouseEvent e) {
//                                            }
//                                        });

                                        columnsClient.exitGameButton.addMouseListener(new MouseAdapter() {

                                            public void mouseReleased(MouseEvent e) {
                                                if (e.getSource() == columnsClient.exitGameButton) {
                                                    try {
                                                        if (columnsClient.clientSocket != null) {
                                                            columnsClient.clientSocket.close();
                                                        }
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(MainGamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    MainGamePanel.this.setFocusable(true);
                                                    MainGamePanel.this.remove(columnsClient);
                                                    MainGamePanel.this.add(startPanel);
                                                    columnsClient = null;
                                                    System.gc();
                                                }
                                            }
                                        });
                                        columnsClient.joinGameButton.addMouseListener(new MouseAdapter() {
                                            //加入游戏的人通过该方法可以进入游戏界面
                                            public void mouseReleased(MouseEvent e) {
                                                if (e.getSource() == columnsClient.joinGameButton) {
                                                    timer = new Timer(2, new ActionListener() {

                                                        public void actionPerformed(ActionEvent e) {
                                                            if (columnsClient.canJoinGame) {
                                                                MainGamePanel.this.remove(columnsClient);
                                                                MainGamePanel.this.add(columnsClient.columnsClientThread.firstPlayer);
                                                                MainGamePanel.this.setVisible(true);
                                                                columnsClient.columnsClientThread.firstPlayer.addKeyListener(new KeyAdapter() {

                                                                    public void keyPressed(KeyEvent e) {
                                                                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                                                            MainGamePanel.this.remove(columnsClient.columnsClientThread.firstPlayer);
                                                                            MainGamePanel.this.repaint();
                                                                            MainGamePanel.this.add(columnsClient);
                                                                            MainGamePanel.this.setVisible(true);
                                                                            columnsClient.setVisible(true);
                                                                            columnsClient.userInputPad.contentInputted.requestFocus();
                                                                        }
                                                                    }
                                                                });
                                                                timer.stop();
                                                            }
                                                        }
                                                    });
                                                    timer.start();
                                                }
                                            }
                                        });
                                        columnsClient.createGameButton.addMouseListener(new MouseAdapter() {
                                            //创建游戏的人通过该方法可以即时进入游戏
                                            public void mouseReleased(MouseEvent e) {
                                                if (e.getSource() == columnsClient.createGameButton) {
                                                    timer = new Timer(2, new ActionListener() {

                                                        public void actionPerformed(ActionEvent e) {
                                                            if (columnsClient.canJoinGame) {
                                                                MainGamePanel.this.remove(columnsClient);
                                                                MainGamePanel.this.add(columnsClient.columnsClientThread.firstPlayer);
                                                                MainGamePanel.this.setVisible(true);
                                                                columnsClient.columnsClientThread.firstPlayer.addKeyListener(new KeyAdapter() {

                                                                    public void keyPressed(KeyEvent e) {
                                                                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                                                            MainGamePanel.this.remove(columnsClient.columnsClientThread.firstPlayer);
                                                                            MainGamePanel.this.repaint();
                                                                            MainGamePanel.this.add(columnsClient);
                                                                            MainGamePanel.this.setVisible(true);
                                                                            columnsClient.setVisible(true);
                                                                            columnsClient.userInputPad.contentInputted.requestFocus();
                                                                        }
                                                                    }
                                                                });
                                                                timer.stop();
                                                            }
                                                        }
                                                    });
                                                    timer.start();
                                                }
                                            }
                                        });
                                    } //返回上一级
                                    else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                        vsModelPanel.setFocusable(false);
                                        MainGamePanel.this.setFocusable(true);
                                        MainGamePanel.this.remove(vsModelPanel);
                                        MainGamePanel.this.add(startPanel);
                                        vsModelPanel = null;
                                        if(columnsClient != null){
                                            columnsClient = null;
                                        }
                                        System.gc();
                                    }
                                }
                            });
                            break;

                        case 4:
                            MainGamePanel.this.remove(startPanel);
                            optionPanel = new OptionPanel();
                            MainGamePanel.this.add(optionPanel);
                            MainGamePanel.this.setVisible(true);
                            MainGamePanel.this.setFocusable(false);
                            optionPanel.addKeyListener(new KeyAdapter() {

                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                                        optionPanel.setOption(1);
                                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                                        optionPanel.setOption(2);
                                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                                        optionPanel.setOption(3);
                                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                                        optionPanel.setOption(4);
                                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        if (optionPanel.Return()) {
                                            MainGamePanel.this.setFocusable(true);
                                            MainGamePanel.this.remove(optionPanel);
                                            MainGamePanel.this.add(startPanel);
                                            optionPanel = null;
                                            System.gc();
                                        }
                                    }
                                }
                            });
                            break;

                        //退出游戏
                        case 6:
                            System.exit(0);
                            break;
                    }
                }
                MainGamePanel.this.repaint();
            }
        });
    }
}

