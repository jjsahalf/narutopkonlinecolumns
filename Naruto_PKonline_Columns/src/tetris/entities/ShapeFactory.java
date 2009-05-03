package tetris.entities;

import java.io.IOException;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;
import tetris.listener.ShapeListener;
import tetris.util.Global;

public class ShapeFactory {

    public boolean isSecondPlayer = false;
    public int shapes[][] = new int[3][1];
    private static int lines = 0;
//    public int forecast[][] = new int[3][1];
    public ShapeFactory() {
//        if (!isSecondPlayer) {
        Global.forecast[0][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
        Global.forecast[1][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
        Global.forecast[2][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
//        } else if (isSecondPlayer) {
        Global.forecast2[0][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
        Global.forecast2[1][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
        Global.forecast2[2][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
    }

    public Shape getShape(ShapeListener listener) {
        try {
            if (!isSecondPlayer) {
                for (int i = 0; i < 3; i++) {
                    shapes[i][0] = Global.forecast[i][0];
                }
            } else if (isSecondPlayer) {
                for (int i = 0; i < 3; i++) {
                    shapes[i][0] = Global.forecast2[i][0];
                }
            }
            Shape shape;
            shape = new Shape(listener);
            if (!isSecondPlayer) {
                Global.forecast[0][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
                Global.forecast[1][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
                Global.forecast[2][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            } else if (isSecondPlayer) {
                Global.forecast2[0][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
                Global.forecast2[1][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
                Global.forecast2[2][0] = new Random().nextInt(Global.NUMBER_OF_COLORS) + 1;
            }
            shape.setBody(shapes);

            return shape;
        } catch (Exception ex) {
            int shapes[][] = new int[3][1];
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
    }

    public ShapeForReplay getReplayShape(ShapeListener listener) {
        ShapeForReplay shapeForReplay = new ShapeForReplay(listener);
        String shapeElements = "";
            if (Global.shapesReader != null) {
                try {
                    shapeElements = Global.shapesReader.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(ShapeFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.exit(0);
            }
        lines++;
        shapes[0][0] = Integer.parseInt(shapeElements.charAt(0) + "");
        shapes[1][0] = Integer.parseInt(shapeElements.charAt(2) + "");
        shapes[2][0] = Integer.parseInt(shapeElements.charAt(4) + "");
        shapeForReplay.setBody(shapes);
        return shapeForReplay;
    }

    public String getActions() {
        String shapeActions = "";
            try {
                shapeActions = Global.actionReader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ShapeFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        return shapeActions;
    }

    public String getShapeElements() {
        return shapes[0][0] + " " + shapes[1][0] + " " + shapes[2][0];
    }
}
