package tetris.view;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.swing.JPanel;

import tetris.Ani.FPSMonitor;
import tetris.Ani.SpriteAnimationStream;
import tetris.entities.Ground;
import tetris.entities.Shape;
import tetris.util.Global;

public class GamePanel extends JPanel implements ControllerListener {

    private AudioClip sound_thunder;
    public Player backplayer;
    public Shape shape;
    private Ground ground;
    FPSMonitor fpsMonitor = new FPSMonitor();
    SpriteAnimationStream Fire = new SpriteAnimationStream();
    SpriteAnimationStream Water = new SpriteAnimationStream();
    SpriteAnimationStream Earth = new SpriteAnimationStream();
    SpriteAnimationStream Thunder = new SpriteAnimationStream();
    SpriteAnimationStream Wood = new SpriteAnimationStream();
    SpriteAnimationStream Clear = new SpriteAnimationStream();

    SpriteAnimationStream thunder_v = new SpriteAnimationStream();
    SpriteAnimationStream wood_v = new SpriteAnimationStream();
    SpriteAnimationStream water_v = new SpriteAnimationStream();
    SpriteAnimationStream fire_v = new SpriteAnimationStream();
    SpriteAnimationStream earth_v = new SpriteAnimationStream();

    SpriteAnimationStream thunder_h = new SpriteAnimationStream();
    SpriteAnimationStream wood_h = new SpriteAnimationStream();
    SpriteAnimationStream water_h = new SpriteAnimationStream();
    SpriteAnimationStream fire_h = new SpriteAnimationStream();
    SpriteAnimationStream earth_h = new SpriteAnimationStream();

    SpriteAnimationStream thunder_l2r = new SpriteAnimationStream();
    SpriteAnimationStream wood_l2r = new SpriteAnimationStream();
    SpriteAnimationStream water_l2r= new SpriteAnimationStream();
    SpriteAnimationStream fire_l2r = new SpriteAnimationStream();
    SpriteAnimationStream earth_l2r = new SpriteAnimationStream();

    SpriteAnimationStream thunder_r2l = new SpriteAnimationStream();
    SpriteAnimationStream wood_r2l = new SpriteAnimationStream();
    SpriteAnimationStream water_r2l= new SpriteAnimationStream();
    SpriteAnimationStream fire_r2l = new SpriteAnimationStream();
    SpriteAnimationStream earth_r2l = new SpriteAnimationStream();

    SpriteAnimationStream thunder_circle = new SpriteAnimationStream();
    SpriteAnimationStream wood_circle = new SpriteAnimationStream();
    SpriteAnimationStream water_circle= new SpriteAnimationStream();
    SpriteAnimationStream fire_circle = new SpriteAnimationStream();
    SpriteAnimationStream earth_circle = new SpriteAnimationStream();

    public boolean isSecondPlayer = false;

    public int x_random = 0;
    public int y_random = 0;

    //这个方法用于循环播音乐
    public void controllerUpdate(ControllerEvent e) {
        // TODO Auto-generated method stub
        if (e instanceof EndOfMediaEvent) {
            //System.out.println("play music");
            backplayer.setMediaTime(new Time(0));
            backplayer.start();
        }
        return;
    }

    public void PlayMusic() {
        try {
            backplayer = Manager.createPlayer(new MediaLocator(("file:ProjectResource\\bgsounds\\beijing3.mp3")));
        } catch (java.io.IOException e2) {
            System.out.println(e2 + "IOException");
            return;
        } catch (NoPlayerException e2) {
            System.out.println("backplayer is null.");
            return;
        }
        if (backplayer == null) {
            System.out.println("backplayer is null.");
            return;
        }
        //System.out.println("play music");
 //       backplayer.addControllerListener(this);
//		backplayer.start();
    }

    public void display(Shape shape, Ground ground) {
        this.shape = shape;
        if(isSecondPlayer){
            shape.isSecondPlayer = true;
        }
        this.ground = ground;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        Global.g = g;
        fpsMonitor.Update();
        g.setColor(new Color(0xcfcfcf));
        if (shape != null && ground != null && shape.listener != null) {
           

            Fire.Update(fpsMonitor.GetTimeElapse());
            Water.Update(fpsMonitor.GetTimeElapse());
            Thunder.Update(fpsMonitor.GetTimeElapse());
            Earth.Update(fpsMonitor.GetTimeElapse());
            Wood.Update(fpsMonitor.GetTimeElapse());
            for (int row = 0; row < 3; row++) {
                switch (shape.body[row][0]) {
                    case 1:
                        Thunder.Draw(g, shape.left * Global.CELL_SIZE + 5, (row + shape.top) * Global.CELL_SIZE + shape.pt % 50 + 2, Global.CELL_SIZE - 5, Global.CELL_SIZE - 5);
                        break;
                    case 2:
                        Wood.Draw(g, shape.left * Global.CELL_SIZE, (row + shape.top) * Global.CELL_SIZE + shape.pt % 50, Global.CELL_SIZE - 5, Global.CELL_SIZE - 5);
                        break;
                    case 3:
                        Water.Draw(g, shape.left * Global.CELL_SIZE + 4, (row + shape.top) * Global.CELL_SIZE + shape.pt % 50 + 2, Global.CELL_SIZE - 5, Global.CELL_SIZE - 5);
                        break;
                    case 4:
                        Fire.Draw(g, shape.left * Global.CELL_SIZE + 2, (row + shape.top) * Global.CELL_SIZE + shape.pt % 50 - 3, Global.CELL_SIZE - 5, Global.CELL_SIZE - 5);
                        break;
                    case 5:
                        Earth.Draw(g, shape.left * Global.CELL_SIZE + 3, (row + shape.top) * Global.CELL_SIZE + shape.pt % 50, Global.CELL_SIZE - 5, Global.CELL_SIZE - 5);
                        break;
                }

            }


            shape.drawMe(g);
            ground.drawMe(g);

        }
        if (Global.COLUMNS_IS_CHANGE) {
      //      sound_thunder.play();
            sound_thunder.loop();
            switch(Global.COLOR_COLUMNS){
                case 1:
                   thunder_v.Update(fpsMonitor.GetTimeElapse());
                    for(int i=0;i<Global.HEIGHT_COLUMNS;i++){
                        thunder_v.One_Draw(g, Global.X_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5), Global.Y_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5-i)), (int) (Global.CELL_SIZE * 2),
                            (int) (Global.CELL_SIZE * 2));
                   }
  //                 thunder_circle.Update(fpsMonitor.GetTimeElapse());
   //                thunder_circle.One_Draw(g,Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE*5,Global.CELL_SIZE*5);
                    break;
                case 2:
                    wood_v.Update(fpsMonitor.GetTimeElapse());
                    for(int i=0;i<Global.HEIGHT_COLUMNS;i++){
                        wood_v.One_Draw(g, Global.X_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5), Global.Y_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5-i)), (int) (Global.CELL_SIZE * 2),
                                (int) (Global.CELL_SIZE * 2));
                    }
                    break;
                case 3:
                    water_v.Update(fpsMonitor.GetTimeElapse());
                    for(int i=0;i<Global.HEIGHT_COLUMNS;i++){
                    water_v.One_Draw(g, Global.X_COLUMNS * Global.CELL_SIZE-(int)(Global.CELL_SIZE*0.5), Global.Y_COLUMNS * Global.CELL_SIZE-(int)(Global.CELL_SIZE*(0.5-i)),(int)(Global.CELL_SIZE*2),
                            (int)( Global.CELL_SIZE*2));
                    }
                    break;
                case 4:
                    fire_v.Update(fpsMonitor.GetTimeElapse());
                    for (int i = 0; i < Global.HEIGHT_COLUMNS; i++) {
                        fire_v.One_Draw(g, Global.X_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5), Global.Y_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5 - i)), (int) (Global.CELL_SIZE * 2),
                                (int) (Global.CELL_SIZE * 2));
                    }
                    break;
                case 5:
                    earth_v.Update(fpsMonitor.GetTimeElapse());
                    for (int i = 0; i < Global.HEIGHT_COLUMNS; i++) {
                        earth_v.One_Draw(g, Global.X_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5), Global.Y_COLUMNS * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5-i)), (int) (Global.CELL_SIZE * 2),
                                (int) (Global.CELL_SIZE * 2));
                    }
                    break;
            }

  //              Clear.Update(fpsMonitor.GetTimeElapse());
 //               Clear.One_Draw(g, Global.X_COLUMNS*Global.CELL_SIZE, Global.Y_COLUMNS*Global.CELL_SIZE
  //                      ,Global.CELL_SIZE , Global.HEIGHT_COLUMNS*Global.CELL_SIZE);
            }
        if(!Global.COLUMNS_IS_CHANGE){
            Clear.IsDrawAgain = true;
            thunder_v.IsDrawAgain=true;
            wood_v.IsDrawAgain=true;
            water_v.IsDrawAgain=true;
            fire_v.IsDrawAgain=true;
            earth_v.IsDrawAgain=true;
        }
        if(Global.BAR_IS_CHANGE){
            switch(Global.COLOR_BAR){
                case 1:
                    thunder_h.Update(fpsMonitor.GetTimeElapse());
                    for(int i=0;i<Global.HEIGHT_COLUMNS;i++){
                        thunder_h.One_Draw(g, Global.X_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5-i) ), Global.Y_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5),
                                (int) (Global.CELL_SIZE * 2), (int) (Global.CELL_SIZE * 2));
                    }
                    break;
                case 2:
                    wood_h.Update(fpsMonitor.GetTimeElapse());
                    for (int i = 0; i < Global.WIDTH_BAR; i++) {
                        wood_h.One_Draw(g, Global.X_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5-i) ), Global.Y_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5),
                                (int) (Global.CELL_SIZE * 2), (int) (Global.CELL_SIZE * 2));
                    }
                    break;
                case 3:
                    water_h.Update(fpsMonitor.GetTimeElapse());
                    for(int i=0;i<Global.WIDTH_BAR;i++){
                        water_h.One_Draw(g, Global.X_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5-i) ), Global.Y_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5),
                                (int) (Global.CELL_SIZE * 2), (int) (Global.CELL_SIZE * 2));
                    }
                    break;
                case 4:
                    fire_h.Update(fpsMonitor.GetTimeElapse());
                    for (int i = 0; i < Global.WIDTH_BAR; i++) {
                        fire_h.One_Draw(g, Global.X_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * (0.5 - i)), Global.Y_BAR * Global.CELL_SIZE - (int) (Global.CELL_SIZE * 0.5),
                                (int) (Global.CELL_SIZE * 2), (int) (Global.CELL_SIZE * 2));
                    }
                    break;
                case 5:
                    earth_h.Update(fpsMonitor.GetTimeElapse());
                    earth_h.One_Draw(g, Global.X_BAR * Global.CELL_SIZE-(int)(Global.CELL_SIZE*0.25*Global.MIN), Global.Y_BAR * Global.CELL_SIZE-(int)(Global.CELL_SIZE*0.25),
                            (int)(Global.WIDTH_BAR * Global.CELL_SIZE*1.5), (int)(Global.CELL_SIZE*1.5));
                    break;
            }
//            Clear.Update(fpsMonitor.GetTimeElapse());
//            Clear.One_Draw(g, Global.X_BAR*Global.CELL_SIZE, Global.Y_BAR*Global.CELL_SIZE,
//                    Global.WIDTH_BAR*Global.CELL_SIZE, Global.CELL_SIZE);
        }
        if(!Global.BAR_IS_CHANGE){
            Clear.IsDrawAgain = true;
            thunder_h.IsDrawAgain = true;
            wood_h.IsDrawAgain = true;
            water_h.IsDrawAgain = true;
            fire_h.IsDrawAgain = true;
            earth_h.IsDrawAgain = true;
        }
        if(Global.L2R_IS_CHANGE){
            switch (Global.COLOR_L2R) {
                case 1:
                    thunder_l2r.Update(fpsMonitor.GetTimeElapse());
                    thunder_l2r.One_Draw(g, Global.X_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25), Global.Y_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 2:
                    wood_l2r.Update(fpsMonitor.GetTimeElapse());
                    wood_l2r.One_Draw(g, Global.X_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25), Global.Y_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 3:
                    water_l2r.Update(fpsMonitor.GetTimeElapse());
                    water_l2r.One_Draw(g, Global.X_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25), Global.Y_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 4:
                    fire_l2r.Update(fpsMonitor.GetTimeElapse());
                    fire_l2r.One_Draw(g, Global.X_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25), Global.Y_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 5:
                    earth_l2r.Update(fpsMonitor.GetTimeElapse());
                    earth_l2r.One_Draw(g, Global.X_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25), Global.Y_L2R - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;

            }
  //           Clear.Update(fpsMonitor.GetTimeElapse());
 //            Clear.One_Draw(g, Global.X_L2R, Global.Y_L2R,
  //                  Global.MIN*Global.CELL_SIZE, Global.MIN*Global.CELL_SIZE);
        }
        if(!Global.L2R_IS_CHANGE){
            Clear.IsDrawAgain=true;
            thunder_l2r.IsDrawAgain = true;
            wood_l2r.IsDrawAgain = true;
            water_l2r.IsDrawAgain = true;
            fire_l2r.IsDrawAgain = true;
            earth_l2r.IsDrawAgain = true;

        }
        if(Global.R2L_IS_CHANGE){
            switch (Global.COLOR_R2L) {
                case 1:
                    thunder_r2l.Update(fpsMonitor.GetTimeElapse());
                    thunder_r2l.One_Draw(g, Global.X_R2L - (int) (Global.CELL_SIZE * Global.MIN ), Global.Y_R2L - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 2:
                    wood_r2l.Update(fpsMonitor.GetTimeElapse());
                    wood_r2l.One_Draw(g, Global.X_R2L - (int) (Global.CELL_SIZE * Global.MIN ), Global.Y_R2L - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 3:
                    water_r2l.Update(fpsMonitor.GetTimeElapse());
                    water_r2l.One_Draw(g, Global.X_R2L - (int) (Global.CELL_SIZE * Global.MIN ), Global.Y_R2L - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 4:
                    fire_r2l.Update(fpsMonitor.GetTimeElapse());
                    fire_r2l.One_Draw(g, Global.X_R2L - (int) (Global.CELL_SIZE * Global.MIN ), Global.Y_R2L - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
                case 5:
                    earth_r2l.Update(fpsMonitor.GetTimeElapse());
                    earth_r2l.One_Draw(g, Global.X_R2L - (int) (Global.CELL_SIZE * Global.MIN ), Global.Y_R2L - (int) (Global.CELL_SIZE * Global.MIN * 0.25),
                            (int) (Global.MIN * Global.CELL_SIZE * 1.5), (int) (Global.MIN * Global.CELL_SIZE * 1.5));
                    break;
            }

        }
        if (!Global.R2L_IS_CHANGE) {
            Clear.IsDrawAgain = true;
            thunder_r2l.IsDrawAgain = true;
            wood_r2l.IsDrawAgain = true;
            water_r2l.IsDrawAgain = true;
            fire_r2l.IsDrawAgain = true;
            earth_r2l.IsDrawAgain = true;
        }

        
        Global.COUNTER++;
        if (Global.COUNTER % Global.FREQUENCY == 0) {
            x_random=new Random().nextInt(Global.WIDTH-2);
            y_random = new Random().nextInt(Global.HEIGHT);
            for (int columns = x_random; columns < x_random+3; columns++) {
                ground.isCircleExist[y_random][columns] = false;
            }
            thunder_circle.IsDrawAgain = true;
            wood_circle.IsDrawAgain = true;
            water_circle.IsDrawAgain = true;
            fire_circle.IsDrawAgain = true;
            earth_circle.IsDrawAgain = true;
        }
        if (Global.COUNTER % Global.FREQUENCY == 12) {
            for (int columns = x_random; columns < x_random+3; columns++) {
                ground.isCircleExist[y_random][columns] = true;
            }
        }
        if (Global.COUNTER % Global.FREQUENCY < 20 && Global.COUNTER % Global.FREQUENCY > 0) {
            for (int columns = x_random; columns < x_random+3; columns++) {

//            int x_random=1;//new Random().nextInt(Global.WIDTH);
//            int y_random=15;//new Random().nextInt(Global.HEIGHT);
                if (ground.obstacles[y_random][columns] != 0) {
                    switch (ground.obstacles[y_random][columns]) {
                        case 1:
                            thunder_circle.Update(fpsMonitor.GetTimeElapse());
                            thunder_circle.One_Draw(g, columns * Global.CELL_SIZE, y_random * Global.CELL_SIZE,
                                    Global.CELL_SIZE, Global.CELL_SIZE);
                            break;
                        case 2:
                            wood_circle.Update(fpsMonitor.GetTimeElapse());
                            wood_circle.One_Draw(g, columns * Global.CELL_SIZE, y_random * Global.CELL_SIZE,
                                    Global.CELL_SIZE, Global.CELL_SIZE);
                            break;
                        case 3:
                            water_circle.Update(fpsMonitor.GetTimeElapse());
                            water_circle.One_Draw(g, columns * Global.CELL_SIZE, y_random * Global.CELL_SIZE,
                                    Global.CELL_SIZE, Global.CELL_SIZE);
                            break;
                        case 4:
                            fire_circle.Update(fpsMonitor.GetTimeElapse());
                            fire_circle.One_Draw(g, columns * Global.CELL_SIZE, y_random * Global.CELL_SIZE,
                                    Global.CELL_SIZE, Global.CELL_SIZE);
                            break;
                        case 5:
                            earth_circle.Update(fpsMonitor.GetTimeElapse());
                            earth_circle.One_Draw(g, columns * Global.CELL_SIZE, y_random * Global.CELL_SIZE,
                                    Global.CELL_SIZE, Global.CELL_SIZE);
                            break;
                    }
                }
            }
        }
    }

    public GamePanel() {
//        Global.g=this.getGraphics();
        this.setSize(Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);
        try {
//        PlayMusic();
            sound_thunder = java.applet.Applet.newAudioClip(new URL("file:../AudioClip/makaay3.wav"));
        } catch (MalformedURLException ex) {
           System.out.println("aaaaaaaaaaaaa");
        }
        Fire.Create("Profile\\Ani\\Fire\\Fire%04d.png", 20, 40, 20);
        Water.Create("Profile\\Ani\\Water\\Water%04d.png", 20, 40, 20);
        Thunder.Create("Profile\\Ani\\Thunder\\Thunder%04d.png", 19, 41, 20);
        Earth.Create("Profile\\Ani\\Earth\\Earth%04d.png", 25, 35, 20);
        Wood.Create("Profile\\Ani\\Wood\\Wood%04d.png", 20, 40, 20);
        Clear.Create("Profile\\BlockDemo3\\Fire\\Fire_V\\Fire_V%04d.png", 45, 1, 50);

        thunder_v.Create("Profile\\BlockDemo4\\Thunder\\Thunder_S\\Thunder_S%04d.png", 50, 1, 50);
        wood_v.Create("Profile\\BlockDemo4\\Wood\\Wood_S\\Wood_S%04d.png", 60, 1, 50);
        water_v.Create("Profile\\BlockDemo4\\Water\\Water_S\\Water_S%04d.png", 50, 1, 50);
        fire_v.Create("Profile\\BlockDemo4\\Fire\\Fire_S\\Fire_S%04d.png", 40, 1, 30);
        earth_v.Create("Profile\\BlockDemo4\\Earth\\Earth_S\\Earth_S%04d.png", 50, 1, 50);

        thunder_h.Create("Profile\\BlockDemo4\\Thunder\\Thunder_S\\Thunder_S%04d.png", 50, 1, 50);
        wood_h.Create("Profile\\BlockDemo4\\Wood\\Wood_S\\Wood_S%04d.png", 60, 1, 50);
        water_h.Create("Profile\\BlockDemo4\\Water\\Water_S\\Water_S%04d.png", 50, 1, 50);
        fire_h.Create("Profile\\BlockDemo4\\Fire\\Fire_S\\Fire_S%04d.png", 40, 1, 30);
        earth_h.Create("Profile\\BlockDemo4\\Earth\\Earth_S\\Earth_S%04d.png", 50, 1, 50);

        thunder_l2r.Create("Profile\\BlockDemo4\\Thunder\\Thunder_D\\Thunder_D%04d.png", 45, 1, 30);
        wood_l2r.Create("Profile\\BlockDemo4\\Wood\\Wood_D\\Wood_D%04d.png", 65, 1, 30);
        water_l2r.Create("Profile\\BlockDemo3\\Water\\Water_D\\Water_D%04d.png", 50, 1, 50);
        fire_l2r.Create("Profile\\BlockDemo4\\Fire\\Fire_D\\Fire_D%04d.png", 60, 1, 35);
        earth_l2r.Create("Profile\\BlockDemo4\\Earth\\Earth_D\\Earth_S%04d.png", 31, 30, 30);

        thunder_r2l.Create("Profile\\BlockDemo4\\Thunder\\Thunder_D\\Thunder_D%04d.png", 45, 1, 30);
        wood_r2l.Create("Profile\\BlockDemo4\\Wood\\Wood_D\\Wood_D%04d.png", 65, 1, 30);
        water_r2l.Create("Profile\\BlockDemo3\\Water\\Water_D2\\Water_D%04d.png", 50, 1, 50);
        fire_r2l.Create("Profile\\BlockDemo4\\Fire\\Fire_D\\Fire_D%04d.png", 60, 1, 35);
        earth_r2l.Create("Profile\\BlockDemo4\\Earth\\Earth_D\\Earth_S%04d.png", 31, 30, 30);

        thunder_circle.Create("Profile\\Circle\\thunder\\thunder%04d.png", 20, 1, 10);
        wood_circle.Create("Profile\\Circle\\wood\\wood%04d.png", 20, 1,10);
        water_circle.Create("Profile\\Circle\\water\\water%04d.png", 20, 1,10);
        fire_circle.Create("Profile\\Circle\\fire\\fire%04d.png", 20, 1, 10);
        earth_circle.Create("Profile\\Circle\\earth\\earth%04d.png", 20,1, 10);
        fpsMonitor.Reset();
        new Thread(new PaintThread()).start();
        setFocusable(true);
    }

    //该构造函数可以根据是否是游戏对手的gamePanel而决定是否播放背景音乐
    public GamePanel(boolean isSecondPlayer){
        this.setSize(Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);

        Fire.Create("Profile\\Ani\\Fire\\Fire%04d.png", 20, 40, 20);
        Water.Create("Profile\\Ani\\Water\\Water%04d.png", 20, 40, 20);
        Thunder.Create("Profile\\Ani\\Thunder\\Thunder%04d.png", 19, 41, 20);
        Earth.Create("Profile\\Ani\\Earth\\Earth%04d.png", 25, 35, 20);
        Wood.Create("Profile\\Ani\\Wood\\Wood%04d.png", 20, 40, 20);
        Clear.Create("Profile\\BlockDemo3\\Fire\\Fire_V\\Fire_V%04d.png", 45, 1, 50);

        thunder_v.Create("Profile\\BlockDemo3\\Thunder\\Thunder_V\\Thunder_H%04d.png", 45, 1, 30);
        wood_v.Create("Profile\\BlockDemo3\\Wood\\Wood_V\\Wood_H%04d.png", 40, 1, 40);
        water_v.Create("Profile\\BlockDemo4\\Water\\Water_S\\Water_S%04d.png", 50, 1, 40);
        fire_v.Create("Profile\\BlockDemo3\\Fire\\Fire_V\\Fire_H%04d.png", 45, 1, 40);
        earth_v.Create("Profile\\BlockDemo4\\Earth\\Earth_S\\Earth_S%04d.png", 50, 1, 40);

        thunder_h.Create("Profile\\BlockDemo3\\Thunder\\Thunder_H\\Thunder_V%04d.png", 45, 1, 30);
        wood_h.Create("Profile\\BlockDemo3\\Wood\\Wood_H\\Wood_V%04d.png", 40, 1, 35);
        water_h.Create("Profile\\BlockDemo4\\Water\\Water_S\\Water_S%04d.png", 50, 1, 35);
        fire_h.Create("Profile\\BlockDemo3\\Fire\\Fire_H\\Fire_V%04d.png", 45, 1, 35);
        earth_h.Create("Profile\\BlockDemo4\\Earth\\Earth_S\\Earth_S%04d.png", 50, 1, 35);

        thunder_l2r.Create("Profile\\BlockDemo3\\Thunder\\Thunder_D\\Thunder_D%04d.png", 45, 1, 30);
        wood_l2r.Create("Profile\\BlockDemo3\\Wood\\Wood_D\\Wood_D%04d.png", 40, 1, 25);
        water_l2r.Create("Profile\\BlockDemo3\\Water\\Water_D\\Water_D%04d.png", 50, 1, 35);
        fire_l2r.Create("Profile\\BlockDemo3\\Fire\\Fire_D\\Fire_D%04d.png", 45, 1, 35);
        earth_l2r.Create("Profile\\BlockDemo4\\Earth\\Earth_D\\Earth_S%04d.png", 31, 30, 35);

        thunder_r2l.Create("Profile\\BlockDemo3\\Thunder\\Thunder_D2\\Thunder_D%04d.png", 45, 1, 30);
        wood_r2l.Create("Profile\\BlockDemo3\\Wood\\Wood_D2\\Wood_D%04d.png", 40, 1, 25);
        water_r2l.Create("Profile\\BlockDemo3\\Water\\Water_D2\\Water_D%04d.png", 50, 1, 30);
        fire_r2l.Create("Profile\\BlockDemo3\\Fire\\Fire_D2\\Fire_D%04d.png", 45, 1, 30);
        earth_r2l.Create("Profile\\BlockDemo4\\Earth\\Earth_D\\Earth_S%04d.png", 31, 30, 30);

        
        fpsMonitor.Reset();

        this.isSecondPlayer = isSecondPlayer;
                if(!isSecondPlayer){
                    PlayMusic();
                }
    }

    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
