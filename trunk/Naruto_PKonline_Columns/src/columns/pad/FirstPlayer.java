package columns.pad;

import Naruto_PKonline_Columns.GraphicsControl.FPSMonitor;
import Naruto_PKonline_Columns.GraphicsControl.Global;
import Naruto_PKonline_Columns.GraphicsControl.SpriteAnimationStream;
import columns.client.ColumnsClient;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.controller.Controller;
import tetris.entities.Ground;
import tetris.entities.ShapeFactory;
import tetris.view.GamePanel;

/**
 *
 * @author SHY
 */
public class FirstPlayer extends JPanel {

    //游戏结束面板控件
    FPSMonitor fpsMonitor = new FPSMonitor();
    private BufferedImage Background1;
    public ColumnsClient columnsClient;    //游戏面板
    private BufferedImage Background;
    JLabel Icon1 = null;
    JLabel Icon2 = null;
    JLabel Icon3 = null;
    JLabel Icon4 = null;
    JLabel Icon5 = null;
    //下一个方块
    public Image ground_water = null;
    public Image ground_wood = null;
    public Image ground_thunder = null;
    public Image ground_fire = null;
    public Image ground_earth = null;
    private Image combo1 = null;
    private Image combo2 = null;
    private Image combo3 = null;
    private Image combo4 = null;
    JLabel Next_1P = null;
//    JLabel Next_2P = null;
    JLabel SixStar1 = null;
    JLabel SixStar2 = null;
    JLabel Score_1P = null;
    JLabel Score_2P = null;
    JLabel Combo_1P = null;
    JLabel Combo_2P = null;
    SpriteAnimationStream WinDemo_1P = new SpriteAnimationStream();
    SpriteAnimationStream WinDemo_2P = new SpriteAnimationStream();
    SpriteAnimationStream Fire_1P = new SpriteAnimationStream();
    SpriteAnimationStream Fire_2P = new SpriteAnimationStream();    //游戏版块
    private Font font = new Font("微软雅黑", Font.BOLD, 50);
    private int temp1PScore = 0;
    private int temp2PScore = 0;
    //本机游戏模块
    ShapeFactory shapeFactory;
    Ground ground;
    public GamePanel gamePanel;
    public Controller controller;
    private Thread thread;    //网络对方游戏模块
    ShapeFactory shapeFactoryNet;
    Ground groundNet;
    GamePanel gamePanelNet;
    Controller controllerNet;
    private int times = 1;
    public boolean hasDisposed = false;
    private int winPlayer = 1;

    public FirstPlayer(ColumnsClient columnsClient) throws IOException {

        this.setOpaque(false);
        this.setLayout(null);
        this.setFocusable(true);
        this.columnsClient = columnsClient;
        columnsClient.isGameOver = false;
        combo1 = ImageIO.read(new File("ProjectResource//ComboDemo//1Combo//110.png"));
        combo2 = ImageIO.read(new File("ProjectResource//ComboDemo//2Combo//210.png"));
        combo3 = ImageIO.read(new File("ProjectResource//ComboDemo//3Combo//310.png"));
        combo4 = ImageIO.read(new File("ProjectResource//ComboDemo//4Combo//410.png"));
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
//        Next_2P = new JLabel(new ImageIcon("ProjectResource\\Icon\\NextFor2P.png"));
        Fire_2P.Create("ProjectResource\\AnimationEffects\\2pFire\\2pFire%04d.png", 30, 30, 25);
        Fire_1P.Create("ProjectResource\\AnimationEffects\\1pFire\\1pFire%04d.png", 30, 30, 25);
        WinDemo_1P.Create("ProjectResource\\AnimationEffects\\WinDemo\\1PWinDemo\\1PWinDemo%02d.png", 47, 3, 25);
        WinDemo_2P.Create("ProjectResource\\AnimationEffects\\WinDemo\\2PWinDemo\\2PWinDemo%02d.png", 47, 2, 25);
        Score_1P.setOpaque(false);
        Score_1P.setBounds(350, 230, 370, 75);
        Score_2P.setOpaque(false);
        Score_2P.setBounds(550, 230, 370, 75);
        Combo_1P.setOpaque(false);
        Combo_1P.setBounds(350, 450, 370, 75);
        Combo_2P.setOpaque(false);
        Combo_2P.setBounds(550, 450, 370, 75);
        Next_1P.setOpaque(false);
        Next_1P.setBounds(450, 10, 370, 75);
//        Next_2P.setOpaque(false);
//        Next_2P.setBounds(550, 10, 370, 75);

        SixStar1.setOpaque(false);
        SixStar1.setBounds(100, 550, 300, 300);
        SixStar2.setOpaque(false);
        SixStar2.setBounds(875, 550, 300, 300);

        this.add(SixStar1);
        this.add(SixStar2);
        this.add(Score_1P);
        this.add(Score_2P);
        this.add(Combo_1P);
        this.add(Combo_2P);
        this.add(Next_1P);
//        this.add(Next_2P);
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
//        //第二个玩家的角色
//        switch (Global.SECONDPLAYERROLE) {
//            case 1:
//                Icon1.setOpaque(false);
//                Icon1.setBounds(1165, 700, 75, 75);
//                this.add(Icon1);
//                break;
//            case 2:
//                Icon2.setOpaque(false);
//                Icon2.setBounds(1165, 700, 75, 75);
//                this.add(Icon2);
//                break;
//            case 3:
//                Icon3.setOpaque(false);
//                Icon3.setBounds(1165, 700, 75, 75);
//                this.add(Icon3);
//                break;
//            case 4:
//                Icon4.setOpaque(false);
//                Icon4.setBounds(1165, 700, 75, 75);
//                this.add(Icon4);
//                break;
//            case 5:
//                Icon5.setOpaque(false);
//                Icon5.setBounds(1165, 700, 75, 75);
//                this.add(Icon5);
//                break;
//        }
        Background = ImageIO.read(new File("ProjectResource\\Background\\VSGamePanelBC.jpg"));
        //游戏结束面板控件的实例化
        Background1 = ImageIO.read(new File("ProjectResource\\Background\\GameOverPanelBC.jpg"));
        //读取下一个方块
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
        shapeFactory = new ShapeFactory();
        ground = new Ground();
        gamePanel = new GamePanel();
        controller = new Controller(shapeFactory, ground, gamePanel, columnsClient);
        controller.isNeedToSendMovement = true;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d.width, d.height);
        gamePanel.setBounds(50, -100, gamePanel.getWidth() + 10, gamePanel.getHeight() + 35);
        this.add(gamePanel);
        gamePanel.addKeyListener(controller);
        this.addKeyListener(controller);
        shapeFactoryNet = new ShapeFactory();
        shapeFactoryNet.isSecondPlayer = true;
        groundNet = new Ground();
        groundNet.isSecondPlayer = true;
        gamePanelNet = new GamePanel(true);
        gamePanelNet.setBounds(900, -100, gamePanel.getWidth() + 10, gamePanel.getHeight() + 35);
        controllerNet = new Controller(shapeFactoryNet, groundNet, gamePanelNet, columnsClient);
        controllerNet.isSecondPlayer = true;
        this.add(gamePanelNet);
        controller.newGame();
        PaintThread newThread = new PaintThread();
        thread = new Thread(newThread);
        thread.start();
    }

    public void paint(Graphics g) {
        System.gc();
        if (!hasDisposed) {
            g.drawImage(Background, 0, 0, 1280, 800, this);//加载背景图片
            super.paint(g);
            g.setFont(font);
            g.setColor(Color.red);
            Fire_1P.Update(fpsMonitor.GetTimeElapse());
            Fire_2P.Update(fpsMonitor.GetTimeElapse());
            //显示玩家一下一个方块
            for (int i = 0; i < 3; i++) {
                switch (tetris.util.Global.forecast[i][0]) {
                    case 1:
                        g.drawImage(ground_thunder, 600, 90 + i * tetris.util.Global.CELL_SIZE,
                                tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                        break;

                    case 2:
                        g.drawImage(ground_wood, 600, 90 + i * tetris.util.Global.CELL_SIZE,
                                tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                        break;
                    case 3:
                        g.drawImage(ground_water, 600, 90 + i * tetris.util.Global.CELL_SIZE,
                                tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                        break;
                    case 4:
                        g.drawImage(ground_fire, 600, 90 + i * tetris.util.Global.CELL_SIZE,
                                tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                        break;
                    case 5:
                        g.drawImage(ground_earth, 600, 90 + i * tetris.util.Global.CELL_SIZE,
                                tetris.util.Global.CELL_SIZE, tetris.util.Global.CELL_SIZE, null);
                        break;
                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
            }
            //右边玩家的蓄力图标
            int temp2 = tetris.util.Global.score2P - temp2PScore;
            if (temp2 >= 90 && temp2 < 180) {
                Fire_2P.Draw(g, 955, 660, 50, 50);
            } else if (temp2 >= 180 && temp2 < 270) {
                Fire_2P.Draw(g, 955, 660, 50, 50);
                Fire_2P.Draw(g, 975, 620, 50, 50);
            } else if (temp2 >= 270 && temp2 < 360) {
                Fire_2P.Draw(g, 955, 660, 50, 50);
                Fire_2P.Draw(g, 975, 620, 50, 50);
                Fire_2P.Draw(g, 1030, 620, 50, 50);
            } else if (temp2 >= 360 && temp2 < 450) {
                Fire_2P.Draw(g, 955, 660, 50, 50);
                Fire_2P.Draw(g, 975, 620, 50, 50);
                Fire_2P.Draw(g, 1030, 620, 50, 50);
                Fire_2P.Draw(g, 1050, 660, 50, 50);
            } else if (temp2 >= 450 && temp2 < 540) {
                Fire_2P.Draw(g, 955, 660, 50, 50);
                Fire_2P.Draw(g, 975, 620, 50, 50);
                Fire_2P.Draw(g, 1030, 620, 50, 50);
                Fire_2P.Draw(g, 1050, 660, 50, 50);
                Fire_2P.Draw(g, 1030, 705, 50, 50);
            } else if (temp2 >= 540) {
                Fire_2P.Draw(g, 955, 660, 50, 50);
                Fire_2P.Draw(g, 975, 620, 50, 50);
                Fire_2P.Draw(g, 1030, 620, 50, 50);
                Fire_2P.Draw(g, 1050, 660, 50, 50);
                Fire_2P.Draw(g, 1030, 705, 50, 50);
                Fire_2P.Draw(g, 975, 705, 50, 50);
                temp2PScore = tetris.util.Global.score2P;
            }
            //左边玩家的蓄力图标
            int temp = tetris.util.Global.score - temp1PScore;
            if (temp >= 90 && temp < 180) {
                Fire_1P.Draw(g, 190, 640, 50, 50);
            } else if (temp >= 180 && temp < 270) {
                Fire_1P.Draw(g, 190, 640, 50, 50);
                Fire_1P.Draw(g, 230, 615, 50, 50);
            } else if (temp >= 270 && temp < 360) {
                Fire_1P.Draw(g, 190, 640, 50, 50);
                Fire_1P.Draw(g, 230, 615, 50, 50);
                Fire_1P.Draw(g, 270, 640, 50, 50);
            } else if (temp >= 360 && temp < 450) {
                Fire_1P.Draw(g, 190, 640, 50, 50);
                Fire_1P.Draw(g, 230, 615, 50, 50);
                Fire_1P.Draw(g, 270, 640, 50, 50);
                Fire_1P.Draw(g, 270, 685, 50, 50);
            } else if (temp >= 450 && temp < 540) {
                Fire_1P.Draw(g, 190, 640, 50, 50);
                Fire_1P.Draw(g, 230, 615, 50, 50);
                Fire_1P.Draw(g, 270, 640, 50, 50);
                Fire_1P.Draw(g, 270, 685, 50, 50);
                Fire_1P.Draw(g, 230, 705, 50, 50);
            } else if (temp >= 540) {
                Fire_1P.Draw(g, 190, 640, 50, 50);
                Fire_1P.Draw(g, 230, 615, 50, 50);
                Fire_1P.Draw(g, 270, 640, 50, 50);
                Fire_1P.Draw(g, 270, 685, 50, 50);
                Fire_1P.Draw(g, 230, 705, 50, 50);
                Fire_1P.Draw(g, 190, 685, 50, 50);
                temp1PScore = tetris.util.Global.score;
            }
            //玩家连续消去个数
            switch (tetris.util.Global.combo) {
                case 1:
                    g.drawImage(combo1, 430, 550, 100, 100, null);
                    break;
                case 2:
                    g.drawImage(combo2, 430, 550, 100, 100, null);
                    break;
                case 3:
                    g.drawImage(combo3, 430, 550, 100, 100, null);
                    break;
                case 4:
                    g.drawImage(combo4, 430, 550, 100, 100, null);
                    break;
            }
            g.drawString(tetris.util.Global.score + "", 500, 350);
            //玩家连续消去个数
            switch (tetris.util.Global.combo2) {
                case 1:
                    g.drawImage(combo1, 700, 550, 100, 100, null);
                    break;
                case 2:
                    g.drawImage(combo2, 700, 550, 100, 100, null);
                    break;
                case 3:
                    g.drawImage(combo3, 700, 550, 100, 100, null);
                    break;
                case 4:
                    g.drawImage(combo4, 700, 550, 100, 100, null);
                    break;
            }
            g.drawString(tetris.util.Global.score2P + "", 740, 350);
        } else if (hasDisposed) {
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

//    public static void main(String[] args) throws IOException {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1500, 780);
//        frame.add(new FirstPlayer(new ColumnsClient()));
//        frame.setVisible(true);
//    }
    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                //强制判断服务器是否返回给客户端对手的新方块
                if (!(columnsClient.shape.equals(""))) {
                    if (times == 1) {
                        try {
                            controllerNet.newGame();
                            times = 2;
                        } catch (IOException ex) {
                            Logger.getLogger(FirstPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                //强制判断服务器是否返回给客户端对手的新动作
                if (!(columnsClient.movementNum == 0)) {
                    controllerNet.setPeerPlayerMovement(columnsClient.getControlMsg());
                }
                if (!hasDisposed && controller.isGameOver()) {
                    if (tetris.util.Global.score > tetris.util.Global.score2P) {
                        winPlayer = 1;
                    } //玩家2胜利
                    else if (tetris.util.Global.score < tetris.util.Global.score2P) {
                        winPlayer = 2;
                    } //玩家1胜利
                    else if (tetris.util.Global.score == tetris.util.Global.score2P) {
                        winPlayer = 3;
                    }
                    FirstPlayer.this.removeAll();
                    columnsClient.sendGameOver();
                    clear();
                    clearGame();
                }
                if (!hasDisposed && columnsClient.isGameOver) {
                    if (tetris.util.Global.score > tetris.util.Global.score2P) {
                        winPlayer = 1;
                        if(!controller.isGameOver())
                            gamePanel.shape.pause();
                    } //玩家2胜利
                    else if (tetris.util.Global.score < tetris.util.Global.score2P) {
                        winPlayer = 2;
                        if(!controller.isGameOver())
                            gamePanel.shape.pause();
                    } //玩家1胜利
                    else if (tetris.util.Global.score == tetris.util.Global.score2P) {
                        winPlayer = 3;
                        if(!controller.isGameOver())
                            gamePanel.shape.pause();
                    }
                    FirstPlayer.this.removeAll();
                    clear();
                    clearGame();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearGame() {
        shapeFactory = null;
        shapeFactoryNet = null;
        gamePanel = null;
        gamePanelNet = null;
        ground = null;
        groundNet = null;
        controller = null;
        controllerNet = null;
    }

    private void clear() {
        SixStar1 = null;
        SixStar2 = null;
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
}
