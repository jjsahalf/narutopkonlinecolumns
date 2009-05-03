//Columns客户端
package columns.client;

import columns.Gui.UserChatPad;
import columns.Gui.UserInputPad;
import columns.Gui.UserListPad;
import columns.pad.ColumnsPad;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author SHY
 */
public class ColumnsClient extends JPanel implements MouseListener,ActionListener, KeyListener{

    public Socket clientSocket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    String columnsClientName = null; //用户名
    String host = null;
    int port = 8888;
    boolean isOnChat = false;
    boolean isGameConnected = false;  //游戏是否进行中,是否跟主机相连，相连才可以在主机上创建游戏
    boolean isOnColumns = false;  //是否在游戏
    boolean isCreator = false;
    boolean isPaticipant = false;
    public ColumnsClientThread columnsClientThread;

    UserListPad userListPad = new UserListPad();  //用户列表区
    UserChatPad userChatPad = new UserChatPad();  //用户聊天去
    public UserInputPad userInputPad = new UserInputPad();   //用户输入区
    ColumnsPad columnsPad = new ColumnsPad(); //对战区域
    //面板区
    public JPanel southPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel westPanel = new JPanel();

    public JLabel IPlabel=new JLabel("IP",JLabel.LEFT);
    public JTextField inputIP=new JTextField("localhost",10);
    public JButton connectButton=new JButton("连接主机");
    public JButton createGameButton=new JButton("建立游戏");
    public JButton joinGameButton=new JButton("加入游戏");
    public JButton cancelGameButton=new JButton("放弃游戏");
    public JButton exitGameButton=new JButton("退出游戏大厅");

    public int movementNum = 0;
    public String shape = "";
    public boolean canJoinGame = false;
    public boolean isGameOver = false;

    public ColumnsClient() throws IOException{
//        this.setFocusable(true);
        setLayout(new BorderLayout());
        host=this.inputIP.getText();

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(columnsPad,BorderLayout.CENTER);
        centerPanel.add(userInputPad,BorderLayout.SOUTH);
        centerPanel.setBackground(Color.gray);

        westPanel.setLayout(new BorderLayout());
        westPanel.add(userListPad,BorderLayout.NORTH);
        westPanel.add(userChatPad,BorderLayout.CENTER);

        westPanel.setBackground(Color.gray);

        userInputPad.contentInputted.addKeyListener(this);
        columnsPad.host=this.inputIP.getText();    //设定该玩家要连接的玩家的IP

        this.connectButton.addActionListener(this);
        this.createGameButton.addActionListener(this);
        this.joinGameButton.addActionListener(this);
        this.cancelGameButton.addActionListener(this);
        this.exitGameButton.addActionListener(this);
//        this.connectButton.addMouseListener((MouseListener) this);
//        this.createGameButton.addMouseListener((MouseListener) this);
//        this.joinGameButton.addMouseListener((MouseListener) this);
//        this.cancelGameButton.addMouseListener((MouseListener) this);
//        this.exitGameButton.addMouseListener((MouseListener) this);

//        this.connectButton.setBorderPainted(false);
//        this.createGameButton.setBorderPainted(false);
//        this.joinGameButton.setBorderPainted(false);
//        this.cancelGameButton.setBorderPainted(false);
//        this.exitGameButton.setBorderPainted(false);
        this.connectButton.setUI(new BasicButtonUI());

        this.createGameButton.setEnabled(false);
        this.joinGameButton.setEnabled(false);
        this.cancelGameButton.setEnabled(false);

         southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
         southPanel.add(IPlabel);
         southPanel.add(inputIP);
         southPanel.add(connectButton);
         southPanel.add(createGameButton);
         southPanel.add(joinGameButton);
         southPanel.add(cancelGameButton);
         southPanel.add(exitGameButton);
         southPanel.setBackground(Color.gray);

       add(westPanel,BorderLayout.WEST);
       add(centerPanel,BorderLayout.CENTER);
       add(southPanel,BorderLayout.SOUTH);

//       pack();
       Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d.width, d.height);
       setVisible(true);
//       setResizable(false);
//       validate();
    }

    //按指定的IP地址和端口连接到服务器
    public boolean connectToServer(String serverIP, int serverPort) throws Exception{
        try {
//            columnsPad.columnsSocket = clientSocket;
            clientSocket = new Socket(serverIP, serverPort);
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
            columnsClientThread = new ColumnsClientThread(this); //创建客户端线程
            columnsClientThread.start();
            isOnChat = true;
            return true;
        } catch (Exception e) {
            userChatPad.chatTextArea.setText("连接失败！\n");
        }
        return false;
    }

    public void sendMessage(String sndMessage){
        try {
            outputStream.writeUTF(sndMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.connectButton){ //连接到服务器按钮单击事件
            host = columnsPad.host = this.inputIP.getText(); //获得服务器地址
            canJoinGame = false;
            try {
                if(connectToServer(host, port)){  //成功连接服务器时，设置客户端相应的界面状态
                    userChatPad.chatTextArea.setText("");
                    this.connectButton.setEnabled(false);
                    this.createGameButton.setEnabled(true);
                    this.joinGameButton.setEnabled(true);
                    columnsPad.statusText.setText("连接成功，请等待");
                }
            } catch (Exception ex) {
                userChatPad.chatTextArea.setText("不能连接！\n");
            }
        }
        if(e.getSource() == this.exitGameButton){  //离开游戏按钮单击事件
            if(isOnChat){
                try{  //关闭客户端套接口
                    clientSocket.close();
                }catch(Exception ed){
                    ed.printStackTrace();
                }
            }
//            System.exit(0);
        }
        if(e.getSource() == this.joinGameButton){
            String selectedUser = (String)userListPad.userList.getSelectedValue(); //取得要加入的游戏
            //判断选中的用户是否已经在游戏或该用户为自己本身或者为空
            if(selectedUser == null || selectedUser.startsWith("[incolumns]") || selectedUser.equals(columnsClientName)){
                columnsPad.statusText.setText("必须选择一个用户！");
                canJoinGame = false;
            }else{   //执行加入游戏的操作
                try {
                    if(!isGameConnected){  //若游戏套接口未连接
//                        if(columnsPad.connectServer(columnsPad.host, columnsPad.port)){  //若连接到主机成功
                            isGameConnected = true;
                            isOnColumns = true;
                            isPaticipant = true;
                            this.createGameButton.setEnabled(false);
                            this.joinGameButton.setEnabled(false);
                            this.cancelGameButton.setEnabled(true);
                            sendMessage("/joingame "
                                    + (String)userListPad.userList.getSelectedValue() + " " + columnsClientName);
//                        }
                    }else{  //若游戏端口连接中
                        isOnColumns = true;
                        isPaticipant = true;
                        this.createGameButton.setEnabled(false);
                        this.joinGameButton.setEnabled(false);
                        this.cancelGameButton.setEnabled(true);
                        sendMessage("/joingame "
                                  + (String)userListPad.userList.getSelectedValue() + " " + columnsClientName);
                    }
                } catch (Exception ee) {
                    isGameConnected = false;
                    isOnColumns = false;
                    isPaticipant = false;
                    canJoinGame = false;
                    this.createGameButton.setEnabled(false);
                    this.joinGameButton.setEnabled(true);
                    this.cancelGameButton.setEnabled(false);
                    userChatPad.chatTextArea.setText("不能连接：\n" + ee);
                }
            }
        }
        if(e.getSource() == this.createGameButton){
            try {
                if(!isGameConnected){
//                    if(columnsPad.connectServer(columnsPad.host, columnsPad.port)){
                        isGameConnected = true;
                        isOnColumns = true;
                        isCreator = true;
                        this.createGameButton.setEnabled(false);
                        this.joinGameButton.setEnabled(false);
                        this.cancelGameButton.setEnabled(true);
                        sendMessage("/creategame " + "[incolumns]" + columnsClientName);
                        columnsPad.statusText.setText("创建成功，等待玩家进入...");
//                    }
                }else{
                    isOnColumns = true;
                    isCreator = true;
                    this.createGameButton.setEnabled(false);
                    this.joinGameButton.setEnabled(false);
                    this.cancelGameButton.setEnabled(true);
                    sendMessage("/creategame " +"[incolumns]" + columnsClientName);
                    columnsPad.statusText.setText("创建成功，等待玩家进入...");
                }
            } catch (Exception ec) {
                isGameConnected = false;
                isOnColumns = false;
                isCreator = false;
                this.createGameButton.setEnabled(true);
                this.joinGameButton.setEnabled(true);
                this.cancelGameButton.setEnabled(false);
                ec.printStackTrace();
                userChatPad.chatTextArea.setText("不能连接：\n" + ec);
            }
        }
        if(e.getSource() == this.cancelGameButton){
            if(isOnColumns){
                canJoinGame = false;
                sendMessage("/giveup " + columnsClientName);
//                columnsPad.setVicStatus(-1 * columnsPad.playerNum);
                this.createGameButton.setEnabled(true);
                this.joinGameButton.setEnabled(true);
                this.cancelGameButton.setEnabled(false);
                columnsPad.statusText.setText("请创建或加入游戏！");
            }
            if(!isOnColumns){
                canJoinGame = false;
                this.createGameButton.setEnabled(true);
                this.joinGameButton.setEnabled(true);
                this.cancelGameButton.setEnabled(false);
                columnsPad.statusText.setText("请创建或加入游戏！");
            }
            isPaticipant = isCreator = isOnColumns = false;
        }
    }

    public void keyPressed(KeyEvent e){
        JTextField inputwords = (JTextField)e.getSource();
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(userInputPad.userChoice.getSelectedItem().equals("所有用户")){  //发消息给所有人
                try {
                    outputStream.writeUTF(inputwords.getText());
                    inputwords.setText("");
                } catch (Exception ea) {
                    userChatPad.chatTextArea.setText("不能连接到服务器！\n");
                    userListPad.userList.removeAll();
                    userInputPad.userChoice.removeAll();
                    inputwords.setText("");
                    this.connectButton.setEnabled(true);
                }
            }else{  //发送信息给指定人
                try {
                    outputStream.writeUTF("/" + userInputPad.userChoice.getSelectedItem().toString().trim()
                            + " " + inputwords.getText());
                    if(!userInputPad.userChoice.getSelectedItem().equals(columnsClientName)){
                        userChatPad.chatTextArea.append("\n" + columnsClientName + ":\n" + inputwords.getText() + "\n");
                    }
                    inputwords.setText("");
                } catch (Exception ea) {
                    userChatPad.chatTextArea.setText("不能连接到服务器！\n");
                    userListPad.userList.removeAll();
                    userInputPad.userChoice.removeAll();
                    inputwords.setText("");
                    this.connectButton.setEnabled(true);
                }
            }
        }
    }

    public void refresh(){
        columnsPad.refresh();
    }

    //响应游戏中的按键,将其发送到服务器;1代表转动，2代表左移，3代表右移，4代表下移,5代表暂停
    public void sendControlMsg(int keyNum){
        sendMessage("/movement" + columnsClientName + " " + keyNum);
    }

    //从服务器获得另一个玩家的操作
    public int getControlMsg(){
        int temp = movementNum;
        movementNum = 0;
        return temp;
    }

    //获得本机游戏中的shape
    public void sendShape(String seriableShape){
        sendMessage("/shape" + columnsClientName + " " + seriableShape);
    }

    //从服务器获得另一个玩家的shape
    public String getShape(){
        String temp = shape;
        shape = "";
        return temp;
    }

    //发送游戏结束信息
    public void sendGameOver(){
        sendMessage("/gameOver" + columnsClientName);
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) { }

    public void mousePressed(MouseEvent e) {}
}
