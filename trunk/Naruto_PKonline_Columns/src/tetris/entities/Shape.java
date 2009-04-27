package tetris.entities;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import tetris.listener.ShapeListener;
import tetris.util.Global;

public class Shape {

    Image block1= null;
	public ShapeListener listener;

	public int body[][]=new int [3][1];
	public int left;
	public int top;
    public int pt;

    boolean life;
    boolean swift;
    boolean isPause;
    boolean isMoveDownDirectly;

	public static final int LEFT=1;
	public static final int RIGHT=2;
	public static final int DOWN=3;
    public static final int DOWN_ONE_CELL=4;

    public Thread thread;
    public ShapeDriver shapedriver;

    public Image shape_water=null;
    public Image shape_wood=null;
    public Image shape_thunder=null;
    public Image shape_fire=null;
    public Image shape_earth=null;

    public boolean isSecondPlayer = false;

    public void init(){
        life=true;
        swift=false;
        isPause=false;
        isMoveDownDirectly=false;
        pt=0;
        try {
            shape_water = ImageIO.read(new File("ProjectResource//water//water0000.png"));
        } catch (IOException ex) {
           System.out.println("Image IO error");
        }
        try {
            shape_wood = ImageIO.read(new File("ProjectResource//wood//wood0000.png"));
        } catch (IOException ex) {
            System.out.println("Image IO error");
        }
        try {
            shape_thunder = ImageIO.read(new File("ProjectResource//thunder//thunder0000.png"));
        } catch (IOException ex) {
           System.out.println("Image IO error");
        }
        try {
            shape_fire = ImageIO.read(new File("ProjectResource//fire//fire0000.png"));
        } catch (IOException ex) {
            System.out.println("Image IO error");
        }
        try {
            shape_earth = ImageIO.read(new File("ProjectResource//earth//earth0000.png"));
        } catch (IOException ex) {
            System.out.println("Image IO error");
        }
    }

	public void moveLeft(){
		left--;
	}

	public void moveRight(){
		left++;
	}

	public void moveDown(){
        pt+=5;
        if(pt%Global.CELL_SIZE==0){
            top++;
        }
	}

	public void rotate(){
		int temp;
		temp=body[2][0];
		body[2][0]=body[1][0];
		body[1][0]=body[0][0];
		body[0][0]=temp;
	}

	public void drawMe(Graphics g){
		for(int row=0;row<3 ;row++)
		{
			switch(body[row][0])
			{
                case 1:
                    g.drawImage(shape_thunder, left * Global.CELL_SIZE, (row+top) * Global.CELL_SIZE + pt%50,
                            Global.CELL_SIZE, Global.CELL_SIZE, null);
                    break;
                case 2:
                    g.drawImage(shape_wood, left * Global.CELL_SIZE, (row+top) * Global.CELL_SIZE + pt%50,
                            Global.CELL_SIZE, Global.CELL_SIZE, null);
                    break;
                case 3:
                    g.drawImage(shape_water, left * Global.CELL_SIZE, (row+top) * Global.CELL_SIZE + pt%50,
                            Global.CELL_SIZE, Global.CELL_SIZE, null);
                    break;
                case 4:
                    g.drawImage(shape_fire, left * Global.CELL_SIZE, (row+top) * Global.CELL_SIZE + pt%50,
                            Global.CELL_SIZE, Global.CELL_SIZE, null);
                    break;
                case 5:
                    g.drawImage(shape_earth, left * Global.CELL_SIZE, (row+top) * Global.CELL_SIZE + pt%50,
                            Global.CELL_SIZE, Global.CELL_SIZE, null);
                    break;
                default:
                    g.setColor(Color.BLACK);
                    break;
			}
		}
	}

    public void moveDownDirectly(){
        isMoveDownDirectly=true;
    }

    public void moveDownOneCell(){
        top++;
    }

    public void pause(){
        isPause=!isPause;
        if(isPause){
            thread.suspend();
        }
        else{
            synchronized(Shape.this){
                thread.resume();
            }
        }
    }

	private class ShapeDriver implements Runnable{

		public void run() {
			while(listener.isShapeMoveDownable(Shape.this)){
                if (!isMoveDownDirectly) {
                    try {
                        if(isSecondPlayer){
                            Thread.sleep(45);
                        }else
                            Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                moveDown();
                listener.shapeMoveDown(Shape.this);

            }
		}
	}

	public Shape() {
		new Thread(new ShapeDriver()).start();
        init();
	}

    public Shape(ShapeListener listener) {
        this.addListener(listener);
        try {
            shapedriver = new ShapeDriver();
        } catch (Exception e) {
            shapedriver = new ShapeDriver();
        }
        try {
            thread = new Thread(shapedriver);

        } catch (Exception e) {
            thread =new Thread(shapedriver);
        }
        try {
            thread.start();
        } catch (Exception e) {
            System.out.println("start error");
            thread.start();
        }
        init();
	}
	public void addListener(ShapeListener l){
		if(l!=null){
			this.listener=l;
		}
	}



	public void setBody(int body[][]){
		this.body=body;
	}

	public int getLeft()
	{
		return left;
	}

	public int getTop()
	{
		return top;
	}

}
