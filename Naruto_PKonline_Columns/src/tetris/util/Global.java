package tetris.util;

import java.awt.Graphics;

public class Global {

	public static final int CELL_SIZE=50;

    public static final int NUMBER_OF_COLORS=5;

	public static final int WIDTH=9;
	public static final int HEIGHT=16;

    public static final int MIN=3;//最小的消去数

    public static boolean REMOVE=false;
   
//    public static int H_HEIGHT=
    public static int COLOR_COLUMNS=0;
    public static int COLOR_BAR=0;
    public static int COLOR_R2L=0;
    public static int COLOR_L2R=0;

    public static Graphics g;

    public static boolean COLUMNS_IS_CHANGE = false;
    public static int X_COLUMNS = 0;
    public static int Y_COLUMNS = 0;
    public static int HEIGHT_COLUMNS = 0;

    public static boolean BAR_IS_CHANGE=false;
    public static int X_BAR=0;
    public static int Y_BAR=0;
    public static int WIDTH_BAR=0;

    public static boolean L2R_IS_CHANGE=false;
    public static int X_L2R=0;
    public static int Y_L2R=0;

    public static boolean R2L_IS_CHANGE=false;
    public static int X_R2L=0;
    public static int Y_R2L=0;

    public static final int FOR_CIRCLE=10;
    public static int COUNTER=0;

    public static final int FREQUENCY = 50;

}
