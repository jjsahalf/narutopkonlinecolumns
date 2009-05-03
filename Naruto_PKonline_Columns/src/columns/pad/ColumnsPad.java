
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
public class ColumnsPad extends JPanel{

//    public int playerNum = 1;    //玩家编号
    public int firstWinNumber = 0;     //第一个玩家获胜的次数
    public int secondWinNumber = 0;    //第二个玩家获胜的次数
//    public Socket columnsSocket;
    public String columnsSelfName = null;  //自己的名字
    public String columnsPeerName = null;  //对手的名字
    public String host = null;
    public int port = 8888;
    public JTextField statusText = new JTextField("Please connect to the server first!");
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

    //发送胜利消息
    public void setVicStatus(int vicColumnsPlayer){   //********************************************
        this.removeAll();        
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
    public void paint(Graphics g){     
        super.paint(g);
        for(int i = 1; i <= roomNum; i ++){
            g.fill3DRect(40*i, 40, 30, 30, true);
        }
    }

    
}
