package tetris.entities;

import java.util.Random;

import tetris.listener.ShapeListener;
import tetris.util.Global;

public class ShapeFactory {
    public int shapes[][] = new int[3][1];
    public int forecast[][] = new int[3][1];
    
    public ShapeFactory(){
        forecast[0][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
        forecast[1][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
        forecast[2][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
 //        Global.IS_CHANGE=false;
    }
    public Shape getShape(ShapeListener listener) {
        try {
 
            for(int i=0;i<3;i++){
                shapes[i][0]=forecast[i][0];
            }
            //Shape shape=new Shape();
            Shape shape;
            shape = new Shape(listener);
            forecast[0][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            forecast[1][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            forecast[2][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            shape.setBody(shapes);

            return shape;
        } catch (Exception ex) {
            int shapes[][] = new int[3][1];
            System.out.println("ShapeFactory's RE＿getShape");
            //Shape shape=new Shape();
            Shape shape = null;
            try {
                shape = new Shape(listener);
            } catch (Exception ex1) {
               System.out.println("RE error");
            }
            shapes[0][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            shapes[1][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            shapes[2][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            shape.setBody(shapes);
            return shape;
        }
    //shape.addListener(listener);
    }

    public String getShapeElements(){
            return shapes[0][0] + " " + shapes[1][0] + " " + shapes[2][0];
        }
}
