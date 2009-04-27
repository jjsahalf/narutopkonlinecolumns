package tetris.controller;

import columns.client.ColumnsClient;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Player;

import tetris.entities.Ground;
import tetris.entities.Shape;
import tetris.entities.ShapeFactory;
import tetris.listener.ShapeListener;
import tetris.view.GamePanel;

public class Controller extends KeyAdapter implements ShapeListener {

    Player player;
    Component vc, cc;
    boolean first = true, loop = false;
    String currentDirectory;
    public Shape shape;
    private ShapeFactory shapeFactory;
    private Ground ground;
    private GamePanel gamePanel;
    private int times = 1;
    private ColumnsClient columnsClient;
    public boolean isSecondPlayer = false;
    public boolean isNeedToSendMovement = false;
    private boolean isGameOver = false;

    public Controller(ShapeFactory shapeFactory,
            Ground ground, GamePanel gamePanel) {
        super();
        this.shapeFactory = shapeFactory;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }

    //联网对战时的构造函数;
    public Controller(ShapeFactory shapeFactory,
            Ground ground, GamePanel gamePanel, ColumnsClient columnsClient) {
        super();
        this.columnsClient = columnsClient;
        this.shapeFactory = shapeFactory;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }


    public void keyPressed(KeyEvent e) {
        if (!isSecondPlayer) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (times % 2 == 1) {
                        shape.rotate();
                    }
                    if (isNeedToSendMovement) {
                        columnsClient.sendControlMsg(1);
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if ((times % 2 == 1) && ground.IsMoveable(shape, Shape.LEFT)) {
                        shape.moveLeft();
                    }
                    if (isNeedToSendMovement) {
                        columnsClient.sendControlMsg(2);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if ((times % 2 == 1) && ground.IsMoveable(shape, Shape.RIGHT)) {
                        shape.moveRight();
                    }
                    if (isNeedToSendMovement) {
                        columnsClient.sendControlMsg(3);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if ((times % 2 == 1) && ground.IsMoveable(shape, Shape.DOWN)) {
                        shape.moveDownDirectly();
                    }
                    if (isNeedToSendMovement) {
                        columnsClient.sendControlMsg(4);
                    }
                    break;
                case KeyEvent.VK_SPACE:
//                    if (times % 2 == 1) {
//                        gamePanel.backplayer.stop();
//                        gamePanel.backplayer.deallocate();
//                    } else {
//                        gamePanel.backplayer.start();
//                    }
                    shape.pause();
                    times++;
                    if (isNeedToSendMovement) {
                        columnsClient.sendControlMsg(5);
                    }
                    break;
                case KeyEvent.VK_CONTROL:
                    if (ground.IsMoveable(shape, Shape.DOWN)) {
                        shape.moveDownOneCell();
                    }
                    break;
            }
            gamePanel.display(shape, ground);
        }
    }

    public void shapeMoveDown(Shape shape) {
        gamePanel.display(shape, ground);
    }

    //获得对从服务器得到的动作的响应
    public void setPeerPlayerMovement(int movementNum) {
        if (isSecondPlayer && shape != null) {
            if (movementNum == 1) {
                if (times % 2 == 1) {
                    shape.rotate();
                }
            } else if (movementNum == 2) {
                if ((times % 2 == 1) && ground.IsMoveable(shape, Shape.LEFT)) {
                    shape.moveLeft();
                }
            } else if (movementNum == 3) {
                if ((times % 2 == 1) && ground.IsMoveable(shape, Shape.RIGHT)) {
                    shape.moveRight();
                }
            } else if (movementNum == 4) {
                if ((times % 2 == 1) && ground.IsMoveable(shape, Shape.DOWN)) {
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
            }
            gamePanel.display(shape, ground);
        }
    }

    public synchronized boolean isShapeMoveDownable(Shape shape) {
        if (!isSecondPlayer && shape == null) {
            return true;
        }
        // TODO Auto-generated method stub
        if (shape != null && ground.IsMoveable(shape, Shape.DOWN)) {
            return true;
        } else if (shape != null) {
            ground.accept(this.shape);
            if (Naruto_PKonline_Columns.GraphicsControl.Global.ISPKONLINE == true && !ground.isFull()) {
                if (!ground.isFull() && !isSecondPlayer) {
                    try {
                        this.shape = shapeFactory.getShape(this);
                        columnsClient.sendShape(shapeFactory.getShapeElements());
                    } catch (Exception ex) {
                    }
                } else if (!ground.isFull() && isSecondPlayer) {
                    try {
//                        this.shape = getShape(columnsClient.getShape());
                        boolean stop = false;
                        while (!stop) {
                            this.shape = getShape(columnsClient.getShape());
                            Thread.sleep(1);
                            if (this.shape != null) {
                                stop = true;
                            }
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (!ground.isFull()) {
                this.shape = shapeFactory.getShape(this);
            } else if (ground.isFull()) {  //游戏结束
//                gamePanel.backplayer.stop();
//                gamePanel.backplayer.deallocate();
                isGameOver = true;
                shape.pause(); 
            }
            return false;
        }
        return false;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void newGame() throws IOException {
        if (Naruto_PKonline_Columns.GraphicsControl.Global.ISPKONLINE == false) {
            shape = shapeFactory.getShape(this);
        } else if (Naruto_PKonline_Columns.GraphicsControl.Global.ISPKONLINE == true && !isSecondPlayer) {
            shape = shapeFactory.getShape(this);
            columnsClient.sendShape(shapeFactory.getShapeElements());
        } else if (Naruto_PKonline_Columns.GraphicsControl.Global.ISPKONLINE == true && isSecondPlayer) {
            shape = getShape(columnsClient.getShape());
        }
    }

    //将传递过来的代表shape的字符串组合成shape
    public Shape getShape(String shapeElements) throws IOException {
        if (!shapeElements.equals("")) {
            String[] results = shapeElements.split(" ");
            shape = new Shape(this);
            shape.isSecondPlayer = true;
            int shapes[][] = new int[3][1];
            for (int i = 0; i < results.length; i++) {
                shapes[i][0] = Integer.parseInt(results[i]);
            }
            shape.setBody(shapes);
        } else {
            shape = null;
        }
        return shape;
    }
}
