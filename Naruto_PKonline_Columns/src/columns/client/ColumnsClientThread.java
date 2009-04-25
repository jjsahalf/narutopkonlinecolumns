//客户端线程类
package columns.client;

import columns.pad.FirstPlayer;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author SHY
 */
public class ColumnsClientThread extends Thread{
    public ColumnsClient columnsClient;
    public FirstPlayer firstPlayer;
    public ColumnsClientThread(ColumnsClient columnsClient){
        this.columnsClient = columnsClient;
    }

    public void dealWithMsg(String msgReceived) throws IOException{
        if(msgReceived.startsWith("/userlist")){  //获得的信息为用户列表
            StringTokenizer userToken = new StringTokenizer(msgReceived, " ");
            int userNumber = 0;
            columnsClient.userListPad.model.removeAllElements();   //清空客户端用户列表
//            columnsClient.userInputPad.userChoice.removeAll(); //清空客户端用户下拉表
            columnsClient.userInputPad.userChoice.removeAllItems();
            columnsClient.userInputPad.userChoice.addItem("所有用户");
            while(userToken.hasMoreTokens()){ //收到的用户信息列表中存在数据时
                String user = (String)userToken.nextToken(" ");
                if(userNumber>0 && !user.startsWith("[incolumns]")){  //用户信息有效时
                    columnsClient.userListPad.model.addElement(user);
                    columnsClient.userListPad.userList.setModel(columnsClient.userListPad.model); //*******************************
                    columnsClient.userInputPad.userChoice.addItem(user);
                }
                userNumber ++;
            }
            columnsClient.userInputPad.userChoice.setSelectedIndex(0);//默认选择所有人
        }else if(msgReceived.startsWith("/waitList")){
            StringTokenizer userToken = new StringTokenizer(msgReceived, " ");
            int waitNumber = 0;
            columnsClient.userListPad.model.removeAllElements();   //清空客户端用户列表
//            columnsClient.userInputPad.userChoice.removeAll(); //清空客户端用户下拉表
            while(userToken.hasMoreTokens()){ //收到的用户信息列表中存在数据时
                String user = (String)userToken.nextToken(" ");
                if(waitNumber>0){  //用户信息有效时
                    columnsClient.userListPad.model.addElement(user);
                    columnsClient.userListPad.userList.setModel(columnsClient.userListPad.model); //*******************************
                }
                waitNumber ++;
            }
        }
        else if(msgReceived.startsWith("/yourname")){  //收到的信息为用户本名时
            columnsClient.columnsClientName = msgReceived.substring(10); //取得用户本名
            columnsClient.setTitle("Columns 客户端 用户名：" + columnsClient.columnsClientName);  //设置程序Frame标题
        }else if(msgReceived.equals("/reject")){
            try {
                columnsClient.columnsPad.statusText.setText("不能加入游戏！");
                columnsClient.cancelGameButton.setEnabled(false);
                columnsClient.joinGameButton.setEnabled(true);
                columnsClient.createGameButton.setEnabled(true);
            } catch (Exception ef) {
                columnsClient.userChatPad.chatTextArea.setText("Cannot close!");
            }
            columnsClient.joinGameButton.setEnabled(true);
        }else if(msgReceived.startsWith("/peer")){  //收到的信息为游戏中的等待
            columnsClient.columnsPad.columnsPeerName = msgReceived.substring(6);
            if(columnsClient.isCreator){
                columnsClient.columnsPad.playerNum = 1;//设定玩家1先行      **************************************
                columnsClient.columnsPad.isKeyEnabled = true;
                columnsClient.columnsPad.statusText.setText("玩家1开始..."); // ***************************
            }else if(columnsClient.isPaticipant){  //用户为游戏加入者
                columnsClient.columnsPad.playerNum = -1;  //设定玩家2开始
                columnsClient.columnsPad.statusText.setText("游戏加入，等待对手...");
            }
            firstPlayer = new FirstPlayer(columnsClient);
            firstPlayer.setVisible(true);
        }else if(msgReceived.startsWith("/youwin")){   //收到的为胜利信息时
            columnsClient.isOnColumns = false;
            columnsClient.columnsPad.setVicStatus(columnsClient.columnsPad.playerNum);
            columnsClient.columnsPad.statusText.setText("对手退出");
            columnsClient.cancelGameButton.setEnabled(false);
            columnsClient.createGameButton.setEnabled(true);
            columnsClient.joinGameButton.setEnabled(true);
        }else if(msgReceived.startsWith("/OK")){   //收到的为成功创建游戏的信息时
            int firstLocation = 0;
            firstLocation = msgReceived.indexOf(" ",0);
            String number = msgReceived.substring(firstLocation + 1);
            Global.CLIENTROOMNUMBER = Integer.parseInt(number);
            columnsClient.refresh();
            columnsClient.columnsPad.statusText.setText("游戏创建等待对手");
        }else if(msgReceived.startsWith("/error")){
            columnsClient.userChatPad.chatTextArea.append("不能和自己聊天!\n");
        }else if(msgReceived.startsWith("/chat")){
            String name = "";
            int firstLocation = 0,lastLocation = 0;
            firstLocation = msgReceived.indexOf(" ",0);
            msgReceived = msgReceived.substring(firstLocation + 1);
            lastLocation = msgReceived.indexOf(" ",0);
            name = msgReceived.substring(0,lastLocation);
            msgReceived = msgReceived.substring(lastLocation+1);
            columnsClient.userChatPad.chatTextArea.append(name + ":\n" + msgReceived);
            columnsClient.userChatPad.chatTextArea.setCaretPosition(    //设定在聊天面板中光标所在的位置
            columnsClient.userChatPad.chatTextArea.getText().length());
        }else if(msgReceived.startsWith("/roomNum")){   //获得游戏房间总数
            int firstLocation = 0;
            firstLocation = msgReceived.indexOf(" ",0);
            String number = msgReceived.substring(firstLocation + 1);
            Global.CLIENTROOMNUMBER = Integer.parseInt(number);
            columnsClient.refresh();
        }else if(msgReceived.startsWith("/movement")){   //从服务器获得对方玩家的动作
            int tempMovement = Integer.parseInt(msgReceived.substring(9));
            columnsClient.movementNum = tempMovement;
        }else if(msgReceived.startsWith("/shape")){   //从服务器获得对方玩家的方块
            columnsClient.shape = msgReceived.substring(6);
        }
        else{
            columnsClient.userChatPad.chatTextArea.append(msgReceived + "\n");
            columnsClient.userChatPad.chatTextArea.setCaretPosition(    //设定在聊天面板中光标所在的位置
            columnsClient.userChatPad.chatTextArea.getText().length());
        }
    }

    @Override
    public void run(){
        String message = "";
        try {
            while(true){  //等待聊天信息，进入等待状态
                message = columnsClient.inputStream.readUTF();
                System.out.println("client  "  + message);
                dealWithMsg(message);
            }
        } catch (Exception es) {
            System.out.println("closed");
        }
    }

}
