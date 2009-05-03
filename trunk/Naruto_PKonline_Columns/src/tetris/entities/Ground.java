package tetris.entities;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.ControllerEvent;
import javax.media.NoPlayerException;
import tetris.util.Global;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Time;
import tetris.Ani.FPSMonitor;

public class Ground {

    int r2l_j, r2l_i;
    int l2r_j, l2r_i;
    public int[][] obstacles = new int[Global.HEIGHT][Global.WIDTH];
    private int[][] removeFlag = new int[Global.HEIGHT][Global.WIDTH];
    public boolean[][] isCircleExist=new boolean[Global.HEIGHT][Global.WIDTH];
    private int new_x;
    private int new_y;
    private final Timer timer = new Timer();
    FPSMonitor fpsMonitor = new FPSMonitor();
    public Image ground_water = null;
    public Image ground_wood = null;
    public Image ground_thunder = null;
    public Image ground_fire = null;
    public Image ground_earth = null;
    
    public Image ground_water_no_circle= null;
    public Image ground_wood_no_circle = null;
    public Image ground_thunder_no_circle = null;
    public Image ground_fire_no_circle = null;
    public Image ground_earth_no_circle = null;

    String fileName;
    Thread thread;


    private AudioClip sound_thunder;
    private AudioClip sound_wood;
    private AudioClip sound_water;
    private AudioClip sound_fire;
    private AudioClip sound_earth;

    private Player thunder_d_player;
    private Player water_s_player;
    private Player wood_s_player;

    public boolean isSecondPlayer = false;

    private class ThunderDListener implements ControllerListener{

        public void controllerUpdate(ControllerEvent e) {
            if (e instanceof EndOfMediaEvent) {
                thunder_d_player.setMediaTime(new Time(0));
            }
            return;
        }

    }
    private class WaterSListener implements ControllerListener{

        public void controllerUpdate(ControllerEvent e) {
            if (e instanceof EndOfMediaEvent) {
                water_s_player.setMediaTime(new Time(0));
            }
            return;
        }
    }

    private class WoodSListener implements ControllerListener{

        public void controllerUpdate(ControllerEvent e) {
            if (e instanceof EndOfMediaEvent) {
                wood_s_player.setMediaTime(new Time(0));
            }
            return;
        }
    }

    public void musicInit(){
        ThunderDListener thunder_d_listener=new ThunderDListener();
        WaterSListener water_s_listener=new WaterSListener();
        WoodSListener wood_s_listener=new  WoodSListener();
         try {
            thunder_d_player = Manager.createPlayer(new MediaLocator(("file:AudioClip\\thunder_d.wav")));
        } catch (java.io.IOException e2) {
            System.out.println(e2 + "IOException");
            return;
        } catch (NoPlayerException e2) {
            System.out.println("backplayer is null.");
            return;
        }
        if (thunder_d_player == null) {
            System.out.println("backplayer is null.");
            return;
        }
        thunder_d_player.addControllerListener(thunder_d_listener);

        try {
            water_s_player = Manager.createPlayer(new MediaLocator(("file:AudioClip\\water_s.wav")));
        } catch (java.io.IOException e2) {
            System.out.println(e2 + "IOException");
            return;
        } catch (NoPlayerException e2) {
            System.out.println("backplayer is null.");
            return;
        }
        if (water_s_player == null) {
            System.out.println("backplayer is null.");
            return;
        }
        water_s_player.addControllerListener(water_s_listener);

         try {
            wood_s_player = Manager.createPlayer(new MediaLocator(("file:AudioClip\\wood_s.wav")));
        } catch (java.io.IOException e2) {
            System.out.println(e2 + "IOException");
            return;
        } catch (NoPlayerException e2) {
            System.out.println("backplayer is null.");
            return;
        }
        if (wood_s_player == null) {
            System.out.println("backplayer is null.");
            return;
        }
        wood_s_player.addControllerListener(wood_s_listener);
    }


    public Ground() {

        Global.score = 0;
        Global.score2P = 0;
        musicInit();
        fileName = "Profile\\BlockDemo3\\Earth\\Earth_H\\Earth_H%04d.png";
        try {
            sound_thunder = java.applet.Applet.newAudioClip(new URL("file:../AudioClip/makaay3.wav"));
        } catch (MalformedURLException ex) {
            System.out.println("audio error");
        }
              try {
            sound_wood = java.applet.Applet.newAudioClip(new URL("file:../AudioClip/makaay3.wav"));
        } catch (MalformedURLException ex) {
            System.out.println("audio error");
        }
              try {
            sound_water = java.applet.Applet.newAudioClip(new URL("file:../AudioClip/makaay3.wav"));
        } catch (MalformedURLException ex) {
            System.out.println("audio error");
        }
              try {
            sound_fire = java.applet.Applet.newAudioClip(new URL("file:../AudioClip/makaay3.wav"));
        } catch (MalformedURLException ex) {
            System.out.println("audio error");
        }
              try {
            sound_earth = java.applet.Applet.newAudioClip(new URL("file:../AudioClip/makaay3.wav"));
        } catch (MalformedURLException ex) {
            System.out.println("audio error");
        }


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

         try {
            ground_water_no_circle = ImageIO.read(new File("ProjectResource//water//groundwater0001.png"));
        } catch (IOException ex) {
            System.out.println("ground's IOimage error");
        }
        try {
            ground_wood_no_circle = ImageIO.read(new File("ProjectResource//wood//groundwood0001.png"));
        } catch (IOException ex) {
            System.out.println("ground's IOimage error");
        }
        try {
            ground_thunder_no_circle = ImageIO.read(new File("ProjectResource//thunder//groundthunder0001.png"));
        } catch (IOException ex) {
            System.out.println("ground's IOimage error");
        }
        try {
            ground_fire_no_circle = ImageIO.read(new File("ProjectResource//fire//groundfire0001.png"));
        } catch (IOException ex) {
            System.out.println("ground's IOimage error");
        }
        try {
            ground_earth_no_circle = ImageIO.read(new File("ProjectResource//earth//groundearth0001.png"));
        } catch (IOException ex) {
            System.out.println("ground's IOimage error");
        }

        for(int row=0;row<Global.HEIGHT;row++){
            for(int columns=0;columns<Global.WIDTH;columns++){
                isCircleExist[row][columns]=true;
            }
        }


    }

    public void accept(Shape shape) {
        Global.combo = 0;
        Global.combo2 = 0;
        new_x = shape.getLeft();
        new_y = shape.getTop();
        for (int row = 0; row < 3; row++) {
            obstacles[shape.getTop() + row][shape.getLeft()] = shape.body[row][0];
        }

        //check1
        remove2();
        Global.actionPrint.println();
    }

    public boolean isFull() {
        boolean result = false;
        for (int columns = 0; columns < Global.WIDTH; columns++) {
            if (obstacles[2][columns] != 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void remove2() {
        dropDown();
        boolean c, b, l, r;
        for (int row = 0; row < Global.HEIGHT; row++) {
            for (int columns = 0; columns < Global.WIDTH; columns++) {
                removeFlag[row][columns] = 0;
            }
        }
        c = checkColumns();
        b = checkBar();
        l = checkDiagonal_l2r();
        r = RemoveDiagonal_r2l();
        for (int row = 0; row < Global.HEIGHT; row++) {
            for (int columns = 0; columns < Global.WIDTH; columns++) {
                if (removeFlag[row][columns] == 1) {
                    obstacles[row][columns] = 0;
                }
            }
        }

        if (c || b || l || r) {

            timer.schedule(new TimerTask() {

                public void run() {
                    dropDown();
                    remove2();
                }
            }, 500);

        }

    }

    public boolean checkColumns() {
        if (!isSecondPlayer) {
            Global.COLUMNS_IS_CHANGE = false;
        } else {
            Global.COLUMNS_IS_CHANGENet = false;
        }
        boolean isRemove = false;
        int first;
        int end;
        int remove_first = 0, remove_end = 0;
        boolean isChecked = false;
        //boolean isFinish = false;
        for (int columns = 0; columns < Global.WIDTH; columns++) {
            first = 0;
            end = 0;
            isChecked = false;
            //isFinish = false;
            remove_first = 0;
            remove_end = 0;
            for (int row = 0; row < Global.HEIGHT; row++) {
                //   && !isFinish; row++) {
                if (obstacles[row][columns] == 0) {
                    end++;
                    first = end;
                    continue;
                }
                if (!isChecked && row + 1 < Global.HEIGHT && obstacles[row][columns] != obstacles[row + 1][columns]) {
                    end++;
                    first = end;
                }
                if (isChecked && row + 1 < Global.HEIGHT && obstacles[row][columns] != obstacles[row + 1][columns]) {
                    remove_end = end;
                    break;
                }
                if (row + 1 < Global.HEIGHT && obstacles[row][columns] == obstacles[row + 1][columns]) {
                    end++;
                }
                if (end - first >= Global.MIN - 1) {
                    remove_first = first;
                    isChecked = true;
                }
                if (isChecked && row + 1 == Global.HEIGHT) {
                    remove_end = end;
                    break;
                }
            }
            if (remove_end - remove_first >= Global.MIN - 1) {
                isRemove = true;
                if (!isSecondPlayer) {
                    Global.COLUMNS_IS_CHANGE = true;
                    Global.COLOR_COLUMNS = obstacles[remove_first][columns];
                    audioOfRemoveForColumns();
                    if(!isSecondPlayer)
                        Global.combo++;
                    else if(isSecondPlayer)
                        Global.combo2 ++;
                    Global.Y_COLUMNS = remove_first;
                    Global.X_COLUMNS = columns;
                    Global.HEIGHT_COLUMNS = remove_end - remove_first + 1;
                    if(!isSecondPlayer)
                        Global.score = Global.score + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    else if(isSecondPlayer)
                        Global.score2P = Global.score2P + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    for (int i = remove_first; i <= remove_end; i++) {
                        removeFlag[i][columns] = 1;
                    }
                } else {
                    Global.COLUMNS_IS_CHANGENet = true;
                    Global.COLOR_COLUMNSNet = obstacles[remove_first][columns];
                    audioOfRemoveForColumns();
                    if(!isSecondPlayer)
                        Global.combo++;
                    else if(isSecondPlayer)
                        Global.combo2 ++;
                    Global.Y_COLUMNSNet = remove_first;
                    Global.X_COLUMNSNet = columns;
                    Global.HEIGHT_COLUMNSNet = remove_end - remove_first + 1;
                    if(!isSecondPlayer)
                        Global.score = Global.score + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    else if(isSecondPlayer)
                        Global.score2P = Global.score2P + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    for (int i = remove_first; i <= remove_end; i++) {
                        removeFlag[i][columns] = 1;
//                        Global.COLOR_COLUMNSNet = obstacles[i][columns];
                    }
                }
            }
        }
        return isRemove;
    }

    public void audioOfRemoveForColumns(){
        switch (Global.COLOR_COLUMNS) {
            case 1:
                thunder_d_player.start();
                break;
            case 2:
                wood_s_player.start();
                break;
            case 3:
                water_s_player.start();
                break;
            case 4:
                thunder_d_player.start();
                break;
            case 5:
                thunder_d_player.start();
                break;
        }
    }

    public void audioOfRemoveForBar(){
        switch (Global.COLOR_BAR) {
            case 1:
                thunder_d_player.start();
                break;
            case 2:
                wood_s_player.start();
                break;
            case 3:
                water_s_player.start();
                break;
            case 4:
                thunder_d_player.start();
                break;
            case 5:
                thunder_d_player.start();
                break;
        }
    }

    public boolean checkBar() {
        if (!isSecondPlayer) {
            Global.BAR_IS_CHANGE = false;
        } else {
            Global.BAR_IS_CHANGENet = false;
        }
        boolean isRemove = false;
        int first;
        int end;
        int remove_first = 0, remove_end = 0;
        boolean isChecked = false;
        //boolean isFinish = false;
        for (int row = 0; row < Global.HEIGHT; row++) {
            //&& !isFinish; row++) {//isFinish ---
            first = 0;
            end = 0;
            isChecked = false;
            //isFinish = false;
            remove_first = 0;
            remove_end = 0;
            for (int columns = 0; columns < Global.WIDTH; columns++) {

                if (!isChecked && obstacles[row][columns] == 0) {
                    end++;
                    first = end;
                    continue;
                }
                if (!isChecked && columns + 1 < Global.WIDTH && obstacles[row][columns] != obstacles[row][columns + 1]) {
                    end++;
                    first = end;

                }
                if (isChecked && columns + 1 < Global.WIDTH && obstacles[row][columns] != obstacles[row][columns + 1]) {
                    remove_end = end;
                    break;
                }
                if (columns + 1 < Global.WIDTH && obstacles[row][columns] == obstacles[row][columns + 1]) {
                    end++;
                }
                if (end - first >= Global.MIN - 1) {
                    remove_first = first;
                    isChecked = true;
                }
                if (isChecked && end + 1 == Global.WIDTH) {
                    remove_end = end;
                    break;
                }
            }
            if (remove_end - remove_first >= Global.MIN - 1) {
                isRemove = true;
                if(!isSecondPlayer)
                        Global.combo++;
                    else if(isSecondPlayer)
                        Global.combo2 ++;
                if (!isSecondPlayer) {
                    Global.BAR_IS_CHANGE = true;
                    Global.COLOR_BAR = obstacles[row][remove_first];
                    if(!isSecondPlayer)
                        Global.score = Global.score + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    else if(isSecondPlayer)
                        Global.score2P = Global.score2P + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    audioOfRemoveForBar();
                    Global.X_BAR = remove_first;
                    Global.Y_BAR = row;
                    Global.WIDTH_BAR = remove_end - remove_first + 1;
                } else {
                    Global.BAR_IS_CHANGENet = true;
                    Global.COLOR_BARNet = obstacles[row][remove_first];
                    if(!isSecondPlayer)
                        Global.score = Global.score + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    else if(isSecondPlayer)
                        Global.score2P = Global.score2P + (remove_end - remove_first + 1) * Global.BASE_SCORE;
                    audioOfRemoveForBar();
                    Global.X_BARNet = remove_first;
                    Global.Y_BARNet = row;
                    Global.WIDTH_BARNet = remove_end - remove_first + 1;
                }
                for (int i = remove_first; i <= remove_end; i++) {
                    removeFlag[row][i] = 1;
                }
            }
        }
        return isRemove;
    }

    public void audioOfRemoveForDiagonal() {
        switch (Global.COLOR_COLUMNS) {
            case 1:
//                        sound_thunder.play();
                thunder_d_player.start();
                break;
            case 2:
//                        sound_wood.play();
                thunder_d_player.start();
                break;
            case 3:
//                        sound_water.play();
                thunder_d_player.start();
                break;
            case 4:
                //                      sound_fire.play();
                thunder_d_player.start();
                break;
            case 5:
                //                     sound_earth.play();
                thunder_d_player.start();
                break;
        }
    }

    public boolean checkDiagonal_l2r() {
        if (!isSecondPlayer) {
            Global.L2R_IS_CHANGE = false;
        } else {
            Global.L2R_IS_CHANGENet = false;
        }
        boolean isRemove = false;
        for (int row = 0; row < Global.HEIGHT; row++) {
            for (int columns = 0; columns < Global.WIDTH; columns++) {
                if (row < Global.HEIGHT - Global.MIN + 1 && columns < Global.WIDTH - Global.MIN + 1) {
                    int k = 0;
                    for (k = 0; k < Global.MIN; k++) {
                        if (obstacles[row + k][columns + k] == 0) {
                            break;
                        }
                    }
                    if (k == Global.MIN) {
                        for (k = 0; k < Global.MIN; k++) {
                            if (obstacles[row][columns] != obstacles[row + k][columns + k]) {
                                break;
                            }
                        }
                        if (k == Global.MIN) {
                            isRemove = true;
                            audioOfRemoveForDiagonal();
                            if(!isSecondPlayer)
                        Global.combo++;
                    else if(isSecondPlayer)
                        Global.combo2 ++;
                            if(!isSecondPlayer){
                                Global.score = Global.MIN*Global.BASE_SCORE + Global.score;
                                Global.L2R_IS_CHANGE=true;
                            Global.COLOR_L2R=obstacles[row][columns];
                            Global.X_L2R = columns * Global.CELL_SIZE;
                            Global.Y_L2R = row * Global.CELL_SIZE;
                            }else if(isSecondPlayer){
                                Global.score2P = Global.MIN * Global.BASE_SCORE + Global.score2P;
                                Global.L2R_IS_CHANGENet=true;
                            Global.COLOR_L2RNet=obstacles[row][columns];
                            Global.X_L2RNet = columns * Global.CELL_SIZE;
                            Global.Y_L2RNet = row * Global.CELL_SIZE;
                            }
                            
                            for (k = 0; k < Global.MIN; k++) {
                                removeFlag[row + k][columns + k] = 1;
                            }
                        }
                    }
                }
            }
        }
        return isRemove;
    }

    public boolean RemoveDiagonal_r2l() {
        if (!isSecondPlayer) {
            Global.R2L_IS_CHANGE = false;
        } else {
            Global.R2L_IS_CHANGENet = false;
        }
        boolean isRemove = false;
        for (int row = 0; row < Global.HEIGHT; row++) {
            for (int columns = 0; columns < Global.WIDTH; columns++) {
                int k = 0;
                if (row < Global.HEIGHT - Global.MIN + 1 && columns >= Global.MIN - 1) {
                    for (k = 0; k < Global.MIN; k++) {
                        if (obstacles[row + k][columns - k] == 0) {
                            break;
                        }
                    }
                    if (k == Global.MIN) {
                        for (k = 0; k < Global.MIN; k++) {
                            if (obstacles[row][columns] != obstacles[row + k][columns - k]) {
                                break;
                            }
                        }
                        if (k == Global.MIN) {
                            isRemove = true;
                            audioOfRemoveForDiagonal();
                            if(!isSecondPlayer)
                        Global.combo++;
                    else if(isSecondPlayer)
                        Global.combo2 ++;
                            if(!isSecondPlayer)
                                Global.score = Global.MIN*Global.BASE_SCORE + Global.score;
                            else if(isSecondPlayer){
                                Global.score2P = Global.MIN * Global.BASE_SCORE + Global.score2P;
                            }
                            if (!isSecondPlayer) {
                                Global.R2L_IS_CHANGE = true;
                                Global.COLOR_R2L = obstacles[row][columns];
                                Global.X_R2L = columns * Global.CELL_SIZE;
                                Global.Y_R2L = row * Global.CELL_SIZE;
                            } else {
                                Global.R2L_IS_CHANGENet = true;
                                Global.COLOR_R2LNet = obstacles[row][columns];
                                Global.X_R2LNet = columns * Global.CELL_SIZE;
                                Global.Y_R2LNet = row * Global.CELL_SIZE;
                            }
                            for (k = 0; k < Global.MIN; k++) {
                                removeFlag[row + k][columns - k] = 1;
                            }
                        }
                    }
                }
            }
        }
        return isRemove;
    }


    public void dropDown() {
        int row, columns;
        boolean isFirstZero;
        boolean isBeyond;
        boolean isFinish;
        int theRowOfFirstZero = 10;
        int theFirstNonZero = 10, theLastNonZero = 10;
        for (columns = 0; columns < Global.WIDTH; columns++) {
            isFirstZero = true;
            isBeyond = false;
            isFinish = false;
            for (row = Global.HEIGHT - 1; row >= 0 && (!isFinish); row--) {
                if (obstacles[row][columns] == 0 && isFirstZero) {
                    theRowOfFirstZero = row;
                    isFirstZero = false;
                }
                if (obstacles[row][columns] != 0 && isFirstZero == false && isBeyond == false) {
                    theFirstNonZero = row;
                    isBeyond = true;
                }
                if (obstacles[row][columns] == 0 && isFirstZero == false && isBeyond == true) {
                    theLastNonZero = row + 1;
                    isFinish = true;
                }
            }
            int storeNonZero = theFirstNonZero;
            if (theRowOfFirstZero > theFirstNonZero) {
                for (int base = theRowOfFirstZero; base >= theRowOfFirstZero - (theFirstNonZero - theLastNonZero); base--, storeNonZero--) {
                    try {
                        obstacles[base][columns] = obstacles[storeNonZero][columns];
                    } catch (Exception e) {
                        System.out.println(base);
                        System.out.println(storeNonZero);
                        System.out.println(theFirstNonZero);
                        System.out.println(theLastNonZero);
                        System.out.println(theRowOfFirstZero);
                    }
                    obstacles[storeNonZero][columns] = 0;
                }
            }
        }
    }

    public void drawMe(Graphics g) {
        for (int x = 0; x < Global.HEIGHT; x++) {
            for (int y = 0; y < Global.WIDTH; y++) {
                if (obstacles[x][y] != 0) {
                    switch (obstacles[x][y]) {
                        case 1:

                            if (isCircleExist[x][y]) {
                                g.drawImage(ground_thunder, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            } else {
                                g.drawImage(ground_thunder_no_circle, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            }
                            break;

                        case 2:

                            if (isCircleExist[x][y]) {
                                g.drawImage(ground_wood, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            } else {
                                g.drawImage(ground_wood_no_circle, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            }

                            break;
                        case 3:

                            if (isCircleExist[x][y]) {
                                g.drawImage(ground_water, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            } else {
                                g.drawImage(ground_water_no_circle, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            }
                            break;
                        case 4:

                            if (isCircleExist[x][y]) {
                                g.drawImage(ground_fire, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            } else {
                                g.drawImage(ground_fire_no_circle, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            }

                            break;
                        case 5:

                            if (isCircleExist[x][y]) {
                                g.drawImage(ground_earth, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            } else {
                                g.drawImage(ground_earth_no_circle, y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                                        Global.CELL_SIZE, Global.CELL_SIZE, null);
                            }
                            break;
                        //			case 6:g.setColor(Color.pink);break;
                        default:
                            g.setColor(Color.BLACK);
                            break;
                    }
                //                  g.fill3DRect(y * Global.CELL_SIZE, x * Global.CELL_SIZE,
                //                         Global.CELL_SIZE, Global.CELL_SIZE, true);
                }
            }
        }
    }

    public boolean IsMoveable(Shape shape, int action) {
        int left = shape.getLeft();
        int top = shape.getTop();
        switch (action) {
            case Shape.LEFT:
                left--;
                break;
            case Shape.RIGHT:
                left++;
                break;
            case Shape.DOWN:
//                if(isSecondPlayer)
//                    top += 2;
//                else
//                    top++;
                top++;
                break;
        }
        for (int row = 0; row < 3; row++) {
            if (top + row >= Global.HEIGHT || left < 0 || left >= Global.WIDTH || obstacles[top + row][left] != 0) {
                return false;
            }
        }
        return true;
    }
}
