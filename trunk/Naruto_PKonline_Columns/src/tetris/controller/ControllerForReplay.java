package tetris.controller;

import java.awt.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tetris.entities.GroundForReplay;
import tetris.entities.Shape;
import tetris.entities.ShapeFactory;
import tetris.entities.ShapeForReplay;
import tetris.listener.ShapeListener;
import tetris.util.Global;
import tetris.view.GamePanelForReplay;

public class ControllerForReplay implements ShapeListener {

    Component vc, cc;
    String currentDirectory;
    public ShapeForReplay shape;
    private ShapeFactory shapeFactory;
    private GroundForReplay ground;
    private GamePanelForReplay gamePanel;
    private int times = 1;
    private boolean isGameOver = false;
    private int movementNum = 0;
    private String shapeActions = "";

    public ControllerForReplay(ShapeFactory shapeFactory,
            GroundForReplay ground, GamePanelForReplay gamePanel) {
        super();
        this.shapeFactory = shapeFactory;
        this.ground = ground;
        this.gamePanel = gamePanel;
        try {
            Global.shapesFileReader = new java.io.FileReader(Global.shapesFile);
            Global.shapesReader = new java.io.BufferedReader(Global.shapesFileReader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControllerForReplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Global.actionFileReader = new java.io.FileReader(Global.actionFile);
            Global.actionReader = new java.io.BufferedReader(Global.actionFileReader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControllerForReplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void shapeMoveDown(ShapeForReplay shape) {
        gamePanel.display(shape, ground);
    }

    //获得replay的动作
    public void setPeerPlayerMovement(int movementNum) {
        if (shape != null) {
            if (movementNum == 1) {
                if (times % 2 == 1) {
                    shape.rotate();
                }
            } else if (movementNum == 2) {
                if ((times % 2 == 1) && ground.IsMoveable(shape, Shape.LEFT)) {
                    shape.moveLeft();
                }
            } else if (movementNum == 3) {
                if ((times % 2 == 1) && ground.IsMoveable(shape, ShapeForReplay.RIGHT)) {
                    shape.moveRight();
                }
            } else if (movementNum == 4) {
                if ((times % 2 == 1) && ground.IsMoveable(shape, ShapeForReplay.DOWN)) {
                    isShapeMoveDownable(shape);
                    shape.moveDownDirectly();
                }
            } else if (movementNum == 5) {
//                if (times % 2 == 1) {
//                    gamePanel.backplayer.stop();
//                } else {
//                    gamePanel.backplayer.start();
//                }
//                            player.stop();
//                            player.deallocate();
                shape.pause();
                times++;
            }else{

            }
            gamePanel.display(shape, ground);
        }
    }

    public synchronized boolean isShapeMoveDownable(ShapeForReplay shape) {
        if (shape == null) {
            char temp = getMovement();
            if(temp == '!'){

            }else if(temp != '!'){
                setPeerPlayerMovement(Integer.parseInt(temp + ""));
                while(getNextMovement() != '!'){
                    setPeerPlayerMovement(Integer.parseInt(getNextMovement() + ""));
                    movementNum ++;
                }
            }
            return true;
        }
        // TODO Auto-generated method stub
        if (shape != null && ground.IsMoveable(shape, ShapeForReplay.DOWN)) {
            char temp = getMovement();
            if(temp == '!'){

            }else if(temp != '!'){
                try{
                    setPeerPlayerMovement(Integer.parseInt(temp + ""));
                }catch(Exception e){

                }
                while(getNextMovement() != '!'){
                    try{
                        setPeerPlayerMovement(Integer.parseInt(getNextMovement() + ""));
                    }catch(Exception e){

                    }
                    movementNum ++;
                }
            }
            return true;
        } else if (shape != null) {
            ground.accept(this.shape);
            movementNum = 0;
            shapeActions = shapeFactory.getActions();
            this.shape = shapeFactory.getReplayShape(this);

//            if (Naruto_PKonline_Columns.GraphicsControl.Global.ISPKONLINE == true && !ground.isFull()) {
//                if (!ground.isFull()) {
//                    this.shape = shapeFactory.getReplayShape(this);
//                }
//            } else if (ground.isFull()) {  //游戏结束
////                gamePanel.backplayer.stop();
////                gamePanel.backplayer.deallocate();
//                isGameOver = true;
//                shape.pause();
//            }
            return false;
        }
        return false;
    }

    public char getMovement(){
        int temp = movementNum;
        movementNum ++;
//        if(shapeActions.charAt(temp) == ' ')
//            return 5;
//        else
//            return shapeActions.charAt(temp);
        try{
            System.out.print(shapeActions.charAt(movementNum) + " ");
            return shapeActions.charAt(temp);
        }catch(Exception e){
            return 0;
        }
        
    }

    public char getNextMovement(){
        try {
            System.out.println(shapeActions.charAt(movementNum));
            return shapeActions.charAt(movementNum);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void newGame() throws IOException {
        if (Naruto_PKonline_Columns.GraphicsControl.Global.ISPKONLINE == false) {
            shapeActions = shapeFactory.getActions();
            shape = shapeFactory.getReplayShape(this);
        }
    }

    public void shapeMoveDown(Shape shape) {}

    public boolean isShapeMoveDownable(Shape shape) {return false;}
}
