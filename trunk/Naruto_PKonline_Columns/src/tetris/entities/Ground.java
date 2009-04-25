package tetris.entities;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    int combo;

    public boolean isSecondPlayer = false;

    private AudioClip sound_thunder;
    private AudioClip sound_wood;
    private AudioClip sound_water;
    private AudioClip sound_fire;
    private AudioClip sound_earth;

    private Player thunder_d_player;
    private Player water_s_player;
    private Player wood_s_player;


    private class ThunderDListener implements ControllerListener{

        public void controllerUpdate(ControllerEvent e) {
            if (e instanceof EndOfMediaEvent) {
                //System.out.println("play music");
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

    public Ground()  {
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
        combo = 0;
        new_x = shape.getLeft();
        new_y = shape.getTop();
        for (int row = 0; row < 3; row++) {
            obstacles[shape.getTop() + row][shape.getLeft()] = shape.body[row][0];
        }

        //check1


        remove2();
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
        Global.COLUMNS_IS_CHANGE=false;
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
                    //    System.out.println("为零:");
                    end++;
                    first = end;
                    //       System.out.println(end + " " + first);
                    continue;
                }
                if (!isChecked && row + 1 < Global.HEIGHT && obstacles[row][columns] != obstacles[row + 1][columns]) {
                    //       System.out.println("不等:");
                    end++;
                    first = end;
                //        System.out.println(end + " " + first);
                }
                if (isChecked && row + 1 < Global.HEIGHT && obstacles[row][columns] != obstacles[row + 1][columns]) {
                    remove_end = end;
                    //  isFinish = true;
                    break;
                }
                if (row + 1 < Global.HEIGHT && obstacles[row][columns] == obstacles[row + 1][columns]) {
                    //        System.out.println("等:");
                    end++;
                //        System.out.println(end);
                }
                if (end - first >= Global.MIN - 1) {
                    remove_first = first;
                    isChecked = true;
                }
                if (isChecked && row + 1 == Global.HEIGHT) {
                    remove_end = end;
                    break;

                //isFinish = true;
                }
            }
            if (remove_end - remove_first >= Global.MIN - 1) {
                isRemove = true;
                Global.COLUMNS_IS_CHANGE=true;
                Global.COLOR_COLUMNS=obstacles[remove_first][columns];
                audioOfRemoveForColumns();
                combo++;
                Global.Y_COLUMNS = remove_first;
                Global.X_COLUMNS = columns;
                Global.HEIGHT_COLUMNS=remove_end-remove_first+1;

                for (int i = remove_first; i <= remove_end; i++) {
                    removeFlag[i][columns] = 1;
 //                   Global.COLOR_COLUMNS = obstacles[i][columns];
                }
            }
        }
        //   System.out.println(isRemove);
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
        Global.BAR_IS_CHANGE=false;
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
                    //        System.out.println("为零:");
                    end++;
                    first = end;
                    //        System.out.println(end + " " + first);
                    continue;
                }
                if (!isChecked && columns + 1 < Global.WIDTH && obstacles[row][columns] != obstacles[row][columns + 1]) {
                    //     System.out.println("不等:");
                    end++;
                    first = end;
                //     System.out.println(end + " " + first);

                }
                if (isChecked && columns + 1 < Global.WIDTH && obstacles[row][columns] != obstacles[row][columns + 1]) {
                    remove_end = end;
                    //isFinish = true;
                    break;
                }
                if (columns + 1 < Global.WIDTH && obstacles[row][columns] == obstacles[row][columns + 1]) {
                    //     System.out.println("等:");
                    end++;
                //     System.out.println(end);
                }
                if (end - first >= Global.MIN - 1) {
                    //  System.out.println("确定可疑消:");
                    remove_first = first;
                    isChecked = true;
                }
                if (isChecked && end + 1 == Global.WIDTH) {
                    //    System.out.println("到头了:");
                    remove_end = end;
                    //isFinish = true;
                    break;
                }
            }
            if (remove_end - remove_first >= Global.MIN - 1) {
                isRemove = true;
                combo++;
                Global.BAR_IS_CHANGE = true;
                Global.COLOR_BAR = obstacles[row][remove_first];
                audioOfRemoveForBar();
                Global.X_BAR=remove_first;
                Global.Y_BAR=row;
                Global.WIDTH_BAR=remove_end-remove_first+1;
                for (int i = remove_first; i <= remove_end; i++) {
                    removeFlag[row][i] = 1;
                }
            }
        }
        //  System.out.println(isRemove);
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
        Global.L2R_IS_CHANGE=false;
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
                            combo++;
                            Global.L2R_IS_CHANGE=true;
                            Global.COLOR_L2R=obstacles[row][columns];
                            Global.X_L2R = columns * Global.CELL_SIZE;
                            Global.Y_L2R = row * Global.CELL_SIZE;
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
        Global.R2L_IS_CHANGE=false;
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
                            combo++;
                            Global.R2L_IS_CHANGE=true;
                            Global.COLOR_R2L=obstacles[row][columns];
                            Global.X_R2L=columns*Global.CELL_SIZE;
                            Global.Y_R2L=row*Global.CELL_SIZE;
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

    public boolean removeColumns() {
        boolean columns_result;
//        int color=obstacles[new_y][new_x];
        int columns_first = new_y;
        int columns_end = new_y;
        for (int columns = new_y; columns <= Global.HEIGHT - 1; columns++) {
//            if(first>new_y+2){
//                break;
//            }
            if (columns_end - columns_first < 2) {
                if (columns_end + 1 < Global.HEIGHT && columns_end + 1 <= 11 && obstacles[columns_end][new_x] == obstacles[columns_end + 1][new_x]) {
                    columns_end++;
                } else {
                    if (columns_end + 1 <= 11) {
                        columns_end++;
                    }
                    columns_first = columns_end;
                }
            } else {
                if (columns_end + 1 < Global.HEIGHT && obstacles[columns_end][new_x] == obstacles[columns_end + 1][new_x]) {
                    columns_end++;
                } else {
                    break;
                }
            }
        }
//        columns_first=first;
        //       columns_end=end;

        if (columns_end - columns_first >= 2) {
            for (int i = columns_first; i <= columns_end; i++) {
                obstacles[i][new_x] = 0;
            }
        }
        if (columns_end - columns_first >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeBar() {
        boolean bar_result = false;
        for (int row = new_y; row <= new_y + 2; row++) {
            int bar_first = new_x;
            int bar_end = new_x;
            /*            if(new_x+1<Global.WIDTH){
            if(obstacles[row][new_x+1]==0){
            break;
            }
            }
            if(new_x-1>=0){
            if(obstacles[row][new_x-1]==0){
            break;
            }
            }*/
            while (bar_first - 1 >= 0 && obstacles[row][bar_first - 1] == obstacles[row][bar_first]) {
                bar_first--;
            }
            while (bar_end + 1 < Global.WIDTH && obstacles[row][bar_end + 1] == obstacles[row][bar_end]) {
                bar_end++;
            }

            if (bar_end - bar_first >= 2) {
                for (int i = bar_first; i <= bar_end; i++) {
                    obstacles[row][i] = 0;
                }
                bar_result = true;
            }
        }
        return bar_result;
    }

    public boolean removeDiagonalFromRight2Left() {
        boolean r2l_result = false;
        int r2l_first_x;
        int r2l_first_y;
        int r2l_end_x;
        int r2l_end_y;
        for (int row = new_y; row <= new_y + 2; row++) {
            r2l_first_x = new_x;
            r2l_first_y = row;
            r2l_end_x = new_x;
            r2l_end_y = row;
            while (r2l_first_x - 1 >= 0 && r2l_first_y + 1 < Global.HEIGHT && obstacles[r2l_first_y][r2l_first_x] == obstacles[r2l_first_y + 1][r2l_first_x - 1]) {
                r2l_first_x--;
                r2l_first_y++;
            }
            while (r2l_end_x + 1 < Global.WIDTH && r2l_end_y - 1 >= 0 && obstacles[r2l_end_y][r2l_end_x] == obstacles[r2l_end_y - 1][r2l_end_x + 1]) {
                r2l_end_x++;
                r2l_end_y--;
            }
            if (r2l_end_x - r2l_first_x >= 2) {
                int j = r2l_first_x;
                for (int i = r2l_first_y; i >= r2l_end_y; i--, j++) {
                    obstacles[i][j] = 0;
                }
                r2l_result = true;
            }
        }
        return r2l_result;
    }

    public boolean removeDiagonalFromLeft2Right() {
        boolean l2r_result = false;
        int l2r_first_x;
        int l2r_first_y;
        int l2r_end_x;
        int l2r_end_y;
        for (int row = new_y; row <= new_y + 2; row++) {
            l2r_first_x = new_x;
            l2r_first_y = row;
            l2r_end_x = new_x;
            l2r_end_y = row;
            while (l2r_first_x - 1 >= 0 && l2r_first_y - 1 >= 0 && obstacles[l2r_first_y][l2r_first_x] == obstacles[l2r_first_y - 1][l2r_first_x - 1]) {
                l2r_first_x--;
                l2r_first_y--;
            }
            while (l2r_end_x + 1 < Global.WIDTH && l2r_end_y + 1 < Global.HEIGHT && obstacles[l2r_end_y][l2r_end_x] == obstacles[l2r_end_y + 1][l2r_end_x + 1]) {
                l2r_end_x++;
                l2r_end_y++;
            }
            if (l2r_end_x - l2r_first_x >= 2) {
                int j = l2r_first_x;
                for (int i = l2r_first_y; i <= l2r_end_y; i++, j++) {
                    obstacles[i][j] = 0;
                }
                l2r_result = true;
            }
        }
        return l2r_result;
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

    public boolean removeAll() {

        boolean result = false;

//remove columns-------------------------------------------------------------------------------------------------
        int columns_first = new_y;
        int columns_end = new_y;
        for (int columns = new_y; columns <= Global.HEIGHT - 1; columns++) {
//            if(first>new_y+2){
//                break;
//            }
            if (columns_end - columns_first < 2) {
                if (columns_end + 1 < Global.HEIGHT && columns_end + 1 <= 11 && obstacles[columns_end][new_x] == obstacles[columns_end + 1][new_x]) {
                    columns_end++;
                } else {
                    if (columns_end + 1 <= 11) {
                        columns_end++;
                    }
                    columns_first = columns_end;
                }
            } else {
                if (columns_end + 1 < Global.HEIGHT && obstacles[columns_end][new_x] == obstacles[columns_end + 1][new_x]) {
                    columns_end++;
                } else {
                    break;
                }
            }
        }
//        columns_first=first;
        //       columns_end=end;

        /*        if(columns_end-columns_first>=2) {
        result=true;
        for(int i=columns_first;i<=columns_end;i++){
        obstacles[i][new_x]=0;
        }
        }*/


//remove bar ----------------------------------------------------------------------------------------------------
        int bar_first = new_x;
        int bar_end = new_x;
        for (int row = new_y; row <= new_y + 2; row++) {

            /*            if(new_x+1<Global.WIDTH){
            if(obstacles[row][new_x+1]==0){
            break;
            }
            }
            if(new_x-1>=0){
            if(obstacles[row][new_x-1]==0){
            break;
            }
            }*/
            while (bar_first - 1 >= 0 && obstacles[row][bar_first - 1] == obstacles[row][bar_first]) {
                bar_first--;
            }
            while (bar_end + 1 < Global.WIDTH && obstacles[row][bar_end + 1] == obstacles[row][bar_end]) {
                bar_end++;
            }

        /*            if(bar_end-bar_first>=2){
        for(int i=bar_first;i<=bar_end;i++){
        obstacles[row][i]=0;
        }
        result=true;
        }*/
        }

        //remove diagonal r2l-----------------------------------------------------------------------------------------
        int r2l_first_x = 0;
        int r2l_first_y = 0;
        int r2l_end_x = 0;
        int r2l_end_y = 0;
        for (int row = new_y; row <= new_y + 2; row++) {
            r2l_first_x = new_x;
            r2l_first_y = row;
            r2l_end_x = new_x;
            r2l_end_y = row;
            while (r2l_first_x - 1 >= 0 && r2l_first_y + 1 < Global.HEIGHT && obstacles[r2l_first_y][r2l_first_x] == obstacles[r2l_first_y + 1][r2l_first_x - 1]) {
                r2l_first_x--;
                r2l_first_y++;
            }
            while (r2l_end_x + 1 < Global.WIDTH && r2l_end_y - 1 >= 0 && obstacles[r2l_end_y][r2l_end_x] == obstacles[r2l_end_y - 1][r2l_end_x + 1]) {
                r2l_end_x++;
                r2l_end_y--;
            }
        /*            if(r2l_end_x-r2l_first_x>=2){
        int j=r2l_first_x;
        for(int i=r2l_first_y;i>=r2l_end_y;i--,j++){
        obstacles[i][j]=0;
        }
        result=true;
        }*/
        }


//remove l2r---------------------------------------------------------------------------------------------------
        int l2r_first_x = 0;
        int l2r_first_y = 0;
        int l2r_end_x = 0;
        int l2r_end_y = 0;
        for (int row = new_y; row <= new_y + 2; row++) {
            l2r_first_x = new_x;
            l2r_first_y = row;
            l2r_end_x = new_x;
            l2r_end_y = row;
            while (l2r_first_x - 1 >= 0 && l2r_first_y - 1 >= 0 && obstacles[l2r_first_y][l2r_first_x] == obstacles[l2r_first_y - 1][l2r_first_x - 1]) {
                l2r_first_x--;
                l2r_first_y--;
            }
            while (l2r_end_x + 1 < Global.WIDTH && l2r_end_y + 1 < Global.HEIGHT && obstacles[l2r_end_y][l2r_end_x] == obstacles[l2r_end_y + 1][l2r_end_x + 1]) {
                l2r_end_x++;
                l2r_end_y++;
            }
        /*            if(l2r_end_x-l2r_first_x>=2){
        int j=l2r_first_x;
        for(int i=l2r_first_y;i<=l2r_end_y;i++,j++){
        obstacles[i][j]=0;
        }
        result=true;
        }*/
        }

//set zero------------------------------------------------------------------------------------------------

        if (columns_end - columns_first >= 2) {
            result = true;
            for (int i = columns_first; i <= columns_end; i++) {
                obstacles[i][new_x] = 0;
            }
        }
        for (int row = new_y; row <= new_y + 2; row++) {
            if (bar_end - bar_first >= 2) {
                for (int i = bar_first; i <= bar_end; i++) {
                    obstacles[row][i] = 0;
                }
                result = true;
            }
        }

        if (r2l_end_x - r2l_first_x >= 2) {
            int j = r2l_first_x;
            for (int i = r2l_first_y; i >= r2l_end_y; i--, j++) {
                obstacles[i][j] = 0;
            }
            result = true;
        }

        if (l2r_end_x - l2r_first_x >= 2) {
            int j = l2r_first_x;
            for (int i = l2r_first_y; i <= l2r_end_y; i++, j++) {
                obstacles[i][j] = 0;
            }
            result = true;
        }

        return result;
    }





    public void drawMe(Graphics g) {
        //System.out.println("Ground's drawMe");
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

/*    public void controllerUpdate(ControllerEvent e) {
         if (e instanceof EndOfMediaEvent) {
            //System.out.println("play music");
            thunder_d_player.setMediaTime(new Time(0));
            water_s_player.setMediaTime(new Time(0));
        }
        return;
    }*/

}
