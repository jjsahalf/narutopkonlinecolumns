
//提供显示所有用户的列表框
package columns.Gui;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author SHY
 */
public class UserListPad  extends JPanel{
    public JList userList = new JList();
    public DefaultListModel model = new DefaultListModel();
    public UserListPad(){
        this.setFocusable(false);
        this.setLayout(new BorderLayout());
        userList.setModel(model);
//        for(int i = 1; i <= 10; i ++){
//            model.addElement(i + "." + "无用户");
//        }
        model.addElement("暂无用户加入游戏");
        this.add(userList,BorderLayout.CENTER);
    }
}
