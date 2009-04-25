
package columns.pad;

import columns.client.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author SHY
 */
public class ColumnsPad extends JPanel implements ActionListener{

    public boolean isKeyEnabled = false;  //键盘是否能用
    public boolean isWinned = false;    //是否胜利
    public boolean isGaming = false;    //是否正在游戏状态
    public int playerNum = 1;    //玩家编号
    public int firstWinNumber = 0;     //第一个玩家获胜的次数
    public int secondWinNumber = 0;    //第二个玩家获胜的次数
    public Socket columnsSocket;
//    public DataInputStream inputData;
//    public DataOutputStream outputData;
    public String columnsSelfName = null;  //自己的名字
    public String columnsPeerName = null;  //对手的名字
    public String host = null;
    public int port = 8888;
    public JTextField statusText = new JTextField("Please connect to the server first!");
//    public ColumnsThread columnsThread = new ColumnsThread(this);
    public FirstPlayer firstPlayer;
    public int roomNum = 0;


    public ColumnsPad() throws IOException{
        setSize(440,440);
        setLayout(null);
        setBackground(Color.gray);
//        this.addKeyListener(this);
        add(statusText);
        statusText.setBounds(40,5,360,24);
        statusText.setEditable(false);
//        firstPlayer = new FirstPlayer();
//        secondPlayer = new SecondPlayer(this);
    }

    //根据玩家键盘输入设定方块动作
    public void setMovement(int playerNum){     //*************************************************
        if(playerNum == 1){  //第一个玩家

        }else if(playerNum == -1){  //第二个玩家
        }
    }

    //判断当前状态是否为胜利状态
    public boolean checkVicStatus(int playerNum){   //*************************************************
        if(playerNum == 1){   //对第一个玩家进行判断

        }else if(playerNum == -1){   //对第二个玩家进行判读

        }
        return false;
    }

    //发送胜利消息
    public void setVicStatus(int vicColumnsPlayer){   //********************************************
        //清空两个对战面板，使它们返回到初始状态
        this.removeAll();        
        //清空后必须加上statusText
        add(statusText);
        statusText.setBounds(40,5,360,24);
        if(vicColumnsPlayer == 1){  //第一个玩家胜利
            firstWinNumber ++;
            statusText.setText("玩家1胜利，玩家1：玩家2 " + firstWinNumber + " : "
                    + secondWinNumber + "，游戏重启，等待玩家...");
        }else if(vicColumnsPlayer == -1){
            secondWinNumber ++;
            statusText.setText("玩家2胜利，玩家1：玩家2 " + firstWinNumber + " : "
                    + secondWinNumber + "，游戏重启，等待玩家...");
        }
    }

    public void refresh(){
        this.roomNum = Global.CLIENTROOMNUMBER;
        repaint();
    }

    //画面板
    public void paint(Graphics g){    //*****************************************************     
        super.paint(g);
        for(int i = 1; i <= roomNum; i ++){
            g.fill3DRect(40*i, 40, 30, 30, true);
        }
    }

    //将当前所移动的方块画到游戏面板上；
    public void paintColumnsDiamond(int playerNum) throws IOException{
        if(playerNum == 1 && isKeyEnabled){  //第一个玩家设置方块位置
            //设置方块动作
            setMovement(playerNum);// *******************************************************
            isWinned = checkVicStatus(playerNum);
            if(isWinned==false){

            }else {   //胜利状态
//                columnsThread.sendMessage("/" + columnsPeerName + " /columns"
//                        + "...." + playerNum);
            }
        }else if(playerNum == -1 && isKeyEnabled){
            setMovement(playerNum);
            isWinned = checkVicStatus(playerNum);
            if(isWinned==false){

            }else {   //胜利状态
//                columnsThread.sendMessage("/" + columnsPeerName + " /columns"
//                        + "...." + playerNum);
            }
        }
    }

    //将己方的方块动作画到对方的面板上
    public void paintNetColumns(int playerNum) throws IOException{      //*************************************
        if(playerNum == 1 && isKeyEnabled){  //第一个玩家设置方块位置
            //设置方块动作
            setMovement(playerNum);// *******************************************************
            isWinned = checkVicStatus(playerNum);
            if(isWinned==false){

            }else {   //胜利状态
//                columnsThread.sendMessage("/" + columnsPeerName + " /victory"
//                        + "...." + playerNum);
                setVicStatus(1);
            }
        }else if(playerNum == -1 && isKeyEnabled){
            setMovement(playerNum);
            isWinned = checkVicStatus(playerNum);
            if(isWinned==false){

            }else {   //胜利状态
//                columnsThread.sendMessage("/" + columnsPeerName + " /columns"
//                        + "...." + playerNum);
                setVicStatus(-1);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {}
    
}
