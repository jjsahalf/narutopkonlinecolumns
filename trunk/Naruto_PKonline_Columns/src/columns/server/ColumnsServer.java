//服务器界面类
package columns.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author SHY
 */
public class ColumnsServer extends JFrame implements ActionListener{

    JButton clearMsgButton = new JButton("清空列表");
    JButton serverStatusButton = new JButton("服务器状态");
    JButton closeServerButton = new JButton("关闭服务器");
    JPanel buttonPanel = new JPanel();
    ServerMsgPanel serverMsgPanel = new ServerMsgPanel();
    ServerSocket serverSocket;
    Hashtable clientDataHash = new Hashtable(50);
    Hashtable clientNameHash = new Hashtable(50);
    Hashtable columnsPeerHash = new Hashtable(50);

    public ColumnsServer(){
        super("Java Columns服务器");
        setBackground(Color.black);
        buttonPanel.setLayout(new FlowLayout());
        clearMsgButton.setSize(60,25);
        buttonPanel.add(clearMsgButton);
        clearMsgButton.addActionListener(this);
        serverStatusButton.setSize(75,25);
        buttonPanel.add(serverStatusButton);
        serverStatusButton.addActionListener(this);
        closeServerButton.setSize(75,25);
        buttonPanel.add(closeServerButton);
        closeServerButton.addActionListener(this);
        add(serverMsgPanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        pack();
        setVisible(true);
        setSize(600,450);
        setResizable(false);
        validate();
        try {
            createServer(8888, serverMsgPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用指定端口和面板创建服务器
    public void createServer(int port, ServerMsgPanel serverMsgPanel) throws IOException{
        Socket clientSocket;
        long clientAccessNumber = 1;  //连接到主机的客户数量
        this.serverMsgPanel = serverMsgPanel;
        try {
            serverSocket = new ServerSocket(port);
            serverMsgPanel.msgTextArea.setText("服务器启动于：" + InetAddress.getLocalHost()
                    + ":" + serverSocket.getLocalPort() + "\n");
            while(true){  //监听客户端接口的信息
                clientSocket = serverSocket.accept();
                serverMsgPanel.msgTextArea.append("已连接用户：" + clientSocket + "\n");
                DataOutputStream outputData = new DataOutputStream(clientSocket.getOutputStream());
                clientDataHash.put(clientSocket, outputData);
                clientNameHash.put(clientSocket, ("新玩家" + clientAccessNumber ++));
                ColumnsServerThread thread = new ColumnsServerThread(clientSocket,
                        clientDataHash, clientNameHash, columnsPeerHash, serverMsgPanel);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clearMsgButton){  //清空服务器信息
            serverMsgPanel.msgTextArea.setText("");
        }
        if(e.getSource() == serverStatusButton){  //显示服务器信息
            try {
                serverMsgPanel.msgTextArea.append("服务器信息：" + InetAddress.getLocalHost() + ":"
                       + serverSocket.getLocalPort() + "\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == closeServerButton){
            System.exit(0);
        }
    }

    public static void main(String[] args){
        ColumnsServer columnsServer = new ColumnsServer();
    }
}
