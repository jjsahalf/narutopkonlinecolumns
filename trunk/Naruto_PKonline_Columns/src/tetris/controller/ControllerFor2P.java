package tetris.controller;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;
import javax.media.Player;


import tetris.entities.Ground;
import tetris.entities.Shape;
import tetris.entities.ShapeFactory;
import tetris.entities.ShapeForReplay;
import tetris.listener.ShapeListener;
import tetris.view.GamePanel;

public class ControllerFor2P extends KeyAdapter implements ShapeListener {

    Player player;
    Component vc, cc;
    boolean first = true, loop = false;
    String currentDirectory;
    private Shape shape;
    private ShapeFactory shapeFactory;
    private Ground ground;
    private GamePanel gamePanel;
    private boolean isGameOver = false;

    @Override
    public void keyPressed(KeyEvent e) {
//        player.start();
        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
                shape.rotate();
                break;

            case KeyEvent.VK_A:
                if (ground.IsMoveable(shape, Shape.LEFT)) {
                    shape.moveLeft();
                }
                break;
            case KeyEvent.VK_D:
                if (ground.IsMoveable(shape, Shape.RIGHT)) {
                    shape.moveRight();
                }
                break;
            case KeyEvent.VK_S:
                if (ground.IsMoveable(shape, Shape.DOWN)) {
                    shape.moveDownDirectly();
                }
                break;
            case KeyEvent.VK_SPACE:
                shape.pause();
                break;
            case KeyEvent.VK_F:
                if (ground.IsMoveable(shape, Shape.DOWN)) {
                    shape.moveDownOneCell();
                }
                break;
        }
        gamePanel.display(shape, ground);
    }

    public void shapeMoveDown(Shape shape) {
        gamePanel.display(shape, ground);
    }
    //���е�ͼ�α���ϰ������Ȼ������������ж��Լ��ܷ�����
    public synchronized boolean isShapeMoveDownable(Shape shape) {
        if (shape == null) {
            System.out.println("shape is NULL");
            return true;
        }
        // TODO Auto-generated method stub
        if (ground.IsMoveable(shape, Shape.DOWN)) {
            return true;
        } else {
            ground.accept(this.shape);
            if (!ground.isFull()) {
                try {
                    this.shape = shapeFactory.getShape(this);
                } catch (Exception ex) {
                    System.out.println("notify error");
                }
            } else {
                isGameOver = true;
                shape.pause();
            }
            return false;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void newGame() throws IOException {
        shape = shapeFactory.getShape(this);
    }

    public ControllerFor2P(ShapeFactory shapeFactory,
            Ground ground, GamePanel gamePanel) {
        super();
        this.shapeFactory = shapeFactory;
        this.ground = ground;
        this.gamePanel = gamePanel;
        ground.isSecondPlayer = true;//保证双人模式游戏中一个玩家的动画不会出现在另一个玩家的面板上
    }

    public boolean isShapeMoveDownable(ShapeForReplay shapeForReplay) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void shapeMoveDown(ShapeForReplay shapeForReplay) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
