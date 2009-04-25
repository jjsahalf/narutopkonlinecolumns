//主机面板类
package columns.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author SHY
 */
public class ServerMsgPanel extends JPanel{
    public JTextArea msgTextArea = new JTextArea("",10,50);
    //服务器当前状态标签
    public JLabel statusLabel = new JLabel("当前连接数：", JLabel.LEFT);
    public JPanel msgPanel = new JPanel();  //信息面板
    public JPanel statusPanel = new JPanel();   //状态面板
    public ServerMsgPanel(){
        this.setBackground(Color.black);
        setSize(350,300);
        setLayout(new BorderLayout());
        msgPanel.setLayout(new FlowLayout());
        msgPanel.setSize(210,210);
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setSize(210,50);
        msgPanel.add(msgTextArea);
        statusPanel.add(statusLabel,BorderLayout.WEST);
        add(msgPanel,BorderLayout.CENTER);
        add(statusPanel,BorderLayout.NORTH);
    }
}
