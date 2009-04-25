
package columns.pad;

import columns.client.ColumnsClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tetris.controller.Controller;
import tetris.entities.Ground;
import tetris.entities.ShapeFactory;
import tetris.view.GamePanel;

/**
 *
 * @author SHY
 */
public class FirstPlayer extends JFrame{

    public ColumnsClient columnsClient;

    //本机游戏模块
    ShapeFactory shapeFactory;
    Ground ground;
    GamePanel gamePanel;
    Controller controller;
    private Thread thread;

    //网络对方游戏模块
    ShapeFactory shapeFactoryNet;
    Ground groundNet;
    GamePanel gamePanelNet;
    Controller controllerNet;

    private int times = 1;

    public FirstPlayer(ColumnsClient columnsClient) throws IOException{
        this.columnsClient = columnsClient;
        shapeFactory=new ShapeFactory();
        ground=new Ground();
        gamePanel=new GamePanel();
        controller=new Controller(shapeFactory,ground,gamePanel,columnsClient);
        controller.isNeedToSendMovement = true;
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(2*(gamePanel.getWidth()+10),gamePanel.getHeight()+35);
        gamePanel.setBounds(20, 20, gamePanel.getWidth()+10, gamePanel.getHeight()+35);
        this.add(gamePanel);
	gamePanel.addKeyListener(controller);
	this.addKeyListener(controller);
        shapeFactoryNet = new ShapeFactory();
        groundNet = new Ground();
        groundNet.isSecondPlayer = true;
        gamePanelNet = new GamePanel(true);
        gamePanelNet.setBounds(gamePanel.getWidth()+40, 20, gamePanel.getWidth()+10, gamePanel.getHeight()+10);
        controllerNet = new Controller(shapeFactoryNet,groundNet,gamePanelNet,columnsClient);
        controllerNet.isSecondPlayer = true;
//        controllerNet.shape.addNum = 6;
//        gamePanelNet.shape.addNum = 6;
        this.add(gamePanelNet);
	controller.newGame();
        PaintThread newThread = new PaintThread();
        thread = new Thread(newThread);
        thread.start();
    }

    public void sendControlMsgToGame(){

    }


//    public static void main(String[] args) throws IOException{
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1500,780);
//        frame.add(new FirstPlayer(new ColumnsClient()));
//        frame.setVisible(true);
//    }

     private class PaintThread implements Runnable {
        public void run() {
            while (true) {
                repaint();
                //强制判断服务器是否返回给客户端对手的新动作
                if(!(columnsClient.movementNum == 0)){
                    controllerNet.setPeerPlayerMovement(columnsClient.getControlMsg());
                }
                //强制判断服务器是否返回给客户端对手的新方块
                if(!(columnsClient.shape.equals(""))){
                    if(times == 1){
                        try {
                        controllerNet.newGame();
                        times ++;
                    } catch (IOException ex) {
                        Logger.getLogger(FirstPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
