//用户输入区
package columns.Gui;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author SHY
 */
public class UserInputPad extends JPanel{
    //用户在该控件中输入聊天信息
    public JTextField contentInputted = new JTextField("",26);
    //在该下拉框中用户可以选择聊天对象
    public JComboBox userChoice = new JComboBox();
    public UserInputPad(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
//        for(int i = 1; i <= 10; i++){
//            userChoice.addItem(i + "." + "无用户");
//        }
        userChoice.addItem("暂无用户");
        userChoice.setSize(60,24);
        this.add(userChoice);
        this.add(contentInputted);
    }
}
