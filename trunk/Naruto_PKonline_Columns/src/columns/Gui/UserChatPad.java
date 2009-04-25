//用户聊天面板
package columns.Gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author SHY
 */
public class UserChatPad extends JPanel{
    public JTextArea chatTextArea = new JTextArea("命令区域",18,20);
    public JScrollPane scrollPanel = new JScrollPane(chatTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    public UserChatPad(){
        this.setLayout(new BorderLayout());
//        chatTextArea.setAutoscrolls(true);  //可以支持滚动条
//        chatTextArea.setLineWrap(true);    //可以支持多行
//        scrollPanel.add(chatTextArea);
        this.add(scrollPanel, BorderLayout.CENTER);
    }
}
