//支持多人同时在线玩游戏
package columns.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 *
 * @author SHY
 */
public class ColumnsServerThread extends Thread{
    Socket clientSocket; //保存客户端套接口信息
    Hashtable clientDataHash;  //保存客户端套接口与输出流对应的hash
    Hashtable clientNameHash;  //保存客户端套接口和客户名对应的hash
    Hashtable columnsPeerHash;  //保存游戏创建者和游戏加入者对应的hash
    ServerMsgPanel serverMsgPanel;
    boolean isClientClosed = false;

    public ColumnsServerThread(Socket clientSocket, Hashtable clientDataHash,
            Hashtable clientNameHash, Hashtable columnsPeerHash, ServerMsgPanel server){
        this.clientSocket = clientSocket;
        this.clientDataHash = clientDataHash;
        this.clientNameHash = clientNameHash;
        this.columnsPeerHash = columnsPeerHash;
        this.serverMsgPanel = server;
    }
    //发送反馈信息给当前线程连接到主机的用户
    public void feedBack(String feedBackMsg){
        synchronized(clientDataHash){
            DataOutputStream outputData = (DataOutputStream)clientDataHash.get(clientSocket);
            try {
                outputData.writeUTF(feedBackMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //获得某个未参加游戏的用户的套接字
    public Socket getSocket(String name){
        for(Enumeration enu = clientNameHash.keys();enu.hasMoreElements();){   //遍历以取得游戏中的用户的套接字
            Socket userClient = (Socket)enu.nextElement();
            if(name.equals((String)clientNameHash.get(userClient))){ //除去和本身比较的例外情况
                return userClient;
            }
        }
        return null;
    }

    //获得某个正在参加游戏的用户的套接字
    public Socket getOnGameSocket(String name){
        for(Enumeration enu = clientNameHash.keys();enu.hasMoreElements();){   //遍历以取得游戏中的用户的套接字
            Socket userClient = (Socket)enu.nextElement();
            if(("[incolumns]" + name).equals((String)clientNameHash.get(userClient))){ //除去和本身比较的例外情况
                return userClient;
            }
        }
        return null;
    }

    //发送信息给指定的游戏中的用户
    public boolean sendGamePeerMsg(String gamePeerTarget,String gamePeerMsg){
        String temp = "";
        for(Enumeration enu = clientDataHash.keys();enu.hasMoreElements();){   //遍历以取得游戏中的用户的套接字
            Socket userClient = (Socket)enu.nextElement();
            if(((String)clientNameHash.get(userClient)).startsWith("[incolumns]")){
                temp = ((String)clientNameHash.get(userClient)).substring(11);
            }else
                temp = ((String)clientNameHash.get(userClient));
            if(gamePeerTarget.equals(temp)
                    &&!gamePeerTarget.equals((String)clientNameHash.get(clientSocket))){ //除去和本身比较的例外情况
                synchronized(clientDataHash){
                    DataOutputStream peerOutData = (DataOutputStream)clientDataHash.get(userClient);
                    try {
                        peerOutData.writeUTF(gamePeerMsg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        }
        return true;
    }

    //发送公开信息
    public void sendPublicMsg(String publicMsg){
        synchronized(clientDataHash){
            for(Enumeration enu = clientDataHash.elements();enu.hasMoreElements();){
                DataOutputStream outputData = (DataOutputStream)enu.nextElement();
                try {
                    outputData.writeUTF(publicMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获得所有在线用户列表
    public String getUserList(){
        String userList = "/userlist";
        for(Enumeration enu = clientNameHash.elements();enu.hasMoreElements();){
            userList = userList + " " + (String)enu.nextElement();
        }
        return userList;
    }

    //获得所有创建了游戏但是处于等待参加状态的用户列表
    public String getWaitList(){
        String waitList = "/waitList";
        for(Enumeration enu = columnsPeerHash.keys();enu.hasMoreElements();){
            Object temp = enu.nextElement();
            if(columnsPeerHash.get(temp).equals("wait")){
                waitList = waitList + " " + temp;
            }
        }
        return waitList;
    }

    //根据value值从Hashtable中取得相应的key
    public Object getHashKey(Hashtable targetHash, Object hashValue){
        Object hashKey;
        for(Enumeration enu = targetHash.keys();enu.hasMoreElements();){
            hashKey = (Object)enu.nextElement();
            if(hashValue.equals((Object)targetHash.get(hashKey)))
                return hashKey;
        }
        return null;
    }

    //刚连接到主机时执行的方法
    public void sendInitMsg(){
        sendPublicMsg(getUserList());
        sendPublicMsg(getWaitList());
        feedBack("/yourname " + (String)clientNameHash.get(clientSocket));
        sendPublicMsg("/roomNum " + Global.SERVERROOMNUMBER);
//        feedBack("Columns客户端");
//        feedBack("/list--更新用户列表");
//        feedBack("/<username><talk>--私聊");
//        feedBack("注意：命令必须对所有用户发送");
    }

    //该方法处理服务器从客户端监听得到的消息
    public void dealWithMsg(String msgReceived){
        String clientName;
        String peerName;
        if(msgReceived.startsWith("/")){      //首先判断受到的消息是否为要处理的消息
            if(msgReceived.equals("/list")){  //收到的信息为更新用户列表
                feedBack(getUserList());
                feedBack(getWaitList());
            }else if(msgReceived.startsWith("/creategame [incolumns]")){     //收到的消息为创建游戏
                String gameCreaterName = msgReceived.substring(23); //获取创建者姓名
//                synchronized(clientNameHash){    //将用户端口放到用户列表中
//                    clientNameHash.put(clientSocket, msgReceived.substring(12));
//                    clientNameHash.put(clientSocket, gameCreaterName);
//                }
                synchronized(columnsPeerHash){   //将主机设置为等待状态
                    columnsPeerHash.put(gameCreaterName, "wait");
                }
                Global.SERVERROOMNUMBER ++ ;
                feedBack("/yourname " + clientNameHash.get(clientSocket));
//                sendGamePeerMsg(gameCreaterName, "/OK " + Global.SERVERROOMNUMBER);
//                feedBack("/OK " + Global.SERVERROOMNUMBER);
                sendPublicMsg("/OK " + Global.SERVERROOMNUMBER);
                sendPublicMsg(getUserList());
                sendPublicMsg(getWaitList());
            }else if(msgReceived.startsWith("/joingame")){  //收到的信息为加入游戏
                StringTokenizer userTokens = new StringTokenizer(msgReceived," ");
                String userToken;
                String gameCreaterName;
                String gamePaticipantName;
                String[] playerNames = {"0", "0"};
                int nameIndex = 0;
                while(userTokens.hasMoreTokens()){
                    userToken = (String)userTokens.nextToken(" ");
                    if(nameIndex>=1 && nameIndex<=2){
                        playerNames[nameIndex - 1] = userToken;  //获得游戏者姓名
                    }
                    nameIndex ++;
                }
                gameCreaterName = playerNames[0];
                gamePaticipantName = playerNames[1];
                if(columnsPeerHash.containsKey(gameCreaterName)
                        && columnsPeerHash.get(gameCreaterName).equals("wait")){
                    synchronized(clientNameHash){  //增加游戏加入者的名称修饰
                        clientNameHash.put(clientSocket, ("[incolumns]" + gamePaticipantName));
                    }
                    synchronized(clientNameHash){  //增加游戏创建者名称修饰
                        clientNameHash.put(getSocket(gameCreaterName), ("[incolumns]" + gameCreaterName));
                    }
                    synchronized(columnsPeerHash){   //增加或修改游戏创建者与游戏加入者的名称的对应
                        columnsPeerHash.put(gameCreaterName, gamePaticipantName);
                    }
                    sendPublicMsg(getUserList());
                    sendPublicMsg(getWaitList());
                    //发送消息给游戏加入者
                    sendGamePeerMsg(gamePaticipantName, ("/peer " + "[incolumns]" + gameCreaterName));
                    //发送消息给游戏创建者
                    sendGamePeerMsg(gameCreaterName, ("/peer " + "[incolumns]" + gamePaticipantName));
                }else{  //游戏未创建，拒绝加入游戏
                    sendGamePeerMsg(gamePaticipantName, "/reject");
                    try {
                        closeClient();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else if(msgReceived.startsWith("/giveup")){   //收到的信息为放弃游戏
                String columnsClientName = msgReceived.substring(8);
                if(columnsPeerHash.containsKey(columnsClientName)
                        && !((String)columnsPeerHash.get(columnsClientName)).equals("wait")){
                    //游戏胜利方为加入者，发送胜利消息
                    sendGamePeerMsg((String)columnsPeerHash.get(columnsClientName), "/youwin");
                    synchronized(clientNameHash){   //解除正在玩游戏的用户的名称修饰
                        clientNameHash.put(getOnGameSocket((String)columnsPeerHash.get(columnsClientName)),
                                (String)columnsPeerHash.get(columnsClientName));
                    }
                    synchronized(clientNameHash){   //解除正在玩游戏的用户的名称修饰
                        clientNameHash.put(getOnGameSocket(columnsClientName),
                                columnsClientName);
                    }
//                    //游戏创建者退出，则
//                    sendGamePeerMsg((String)columnsPeerHash.get(columnsClientName),"/winOver");
                    synchronized(columnsPeerHash){  //删除退出游戏的用户
                        columnsPeerHash.remove(columnsClientName);
                    }
                }else if(columnsPeerHash.containsKey(columnsClientName)      //还没有玩家加入游戏时创建者就取消游戏
                        && ((String)columnsPeerHash.get(columnsClientName)).equals("wait")){
                    synchronized(clientNameHash){   //解除正在玩游戏的用户的名称修饰
                        clientNameHash.put(getSocket(columnsClientName),
                                columnsClientName);
                    }
//                    //游戏创建者退出，则
//                    sendGamePeerMsg((String)columnsPeerHash.get(columnsClientName),"/winOver");
                    synchronized(columnsPeerHash){  //删除退出游戏的用户
                        columnsPeerHash.remove(columnsClientName);
                    }
                }else if(columnsPeerHash.containsValue(columnsClientName)){  //胜利方为游戏创建者，发送胜利消息
                    sendGamePeerMsg((String)getHashKey(columnsPeerHash, columnsClientName), "/youwin");
                    synchronized(clientNameHash){   //解除正在玩游戏的用户的名称修饰,创建者
                        clientNameHash.put(getOnGameSocket((String)getHashKey(columnsPeerHash, columnsClientName)),
                                (String)getHashKey(columnsPeerHash, columnsClientName));
                    }
                    synchronized(clientNameHash){   //解除正在玩游戏的用户的名称修饰,参加者
                        clientNameHash.put(getOnGameSocket(columnsClientName),
                                columnsClientName);
                    }
                    synchronized(columnsPeerHash){  //删除退出游戏的用户
                        columnsPeerHash.remove((String)getHashKey(columnsPeerHash, columnsClientName));
                    }
                }
                sendPublicMsg(getUserList());
                sendPublicMsg(getWaitList());
                //将游戏房间减一
                Global.SERVERROOMNUMBER -- ;
                sendPublicMsg("/roomNum " + Global.SERVERROOMNUMBER);
            }else if(msgReceived.startsWith("/movement")){
                int firstLocation = msgReceived.indexOf(" ",0);
                String movement = msgReceived.substring(firstLocation + 1);
                String tempString = msgReceived.substring(0, firstLocation );
                String columnsClientName = tempString.substring(9);
                if(columnsPeerHash.containsKey(columnsClientName)){   //发送给参加游戏的玩家
                    sendGamePeerMsg((String)columnsPeerHash.get(columnsClientName),"/movement" + movement);
                }else if(columnsPeerHash.containsValue(columnsClientName)){   //发送给创建游戏的玩家
                    String gamePeerName = (String)(getHashKey(columnsPeerHash, columnsClientName));
                    sendGamePeerMsg(gamePeerName, "/movement" + movement);
                }
            }else if(msgReceived.startsWith("/shape")){
                int firstLocation = msgReceived.indexOf(" ",0);
                String shape = msgReceived.substring(firstLocation + 1);
                String tempString = msgReceived.substring(0, firstLocation);
                String columnsClientName = tempString.substring(6);
                if(columnsPeerHash.containsKey(columnsClientName)){   //发送给参加游戏的玩家
                    sendGamePeerMsg((String)columnsPeerHash.get(columnsClientName),"/shape" + shape);
                }else if(columnsPeerHash.containsValue(columnsClientName)){   //发送给创建游戏的玩家
                    String gamePeerName = (String)(getHashKey(columnsPeerHash, columnsClientName));
                    sendGamePeerMsg(gamePeerName, "/shape" + shape);
                }
            }else if(msgReceived.startsWith("/")){  //收到的信息为游戏中的聊天信息时
                int firstLocation = 0, lastLocation;
                lastLocation = msgReceived.indexOf(" ", 0);
                peerName = msgReceived.substring((firstLocation + 1), lastLocation);
                msgReceived = msgReceived.substring((lastLocation + 1));
                msgReceived = "/chat " + clientNameHash.get(clientSocket).toString() + " " + msgReceived;
                if(sendGamePeerMsg(peerName, msgReceived)){
                    feedBack("/error");
                }
            }else{  //收到其他信息时
                int lastLocation = msgReceived.indexOf(" ", 0);
                if(lastLocation == -1){
                    feedBack("无效命令");
                    return;
                }
            }
        }else{
            msgReceived = "\n" + clientNameHash.get(clientSocket) + " 给所有人:\n" + msgReceived;
            serverMsgPanel.msgTextArea.append(msgReceived + "\n");
            sendPublicMsg(msgReceived);
            serverMsgPanel.msgTextArea.setCaretPosition(serverMsgPanel.msgTextArea.getText().length());
        }
    }

    //该方法用于关闭客户端的套接口clientSocket
    public void closeClient(){
        serverMsgPanel.msgTextArea.append("用户断开连接：" + clientSocket + "\n");
        synchronized(columnsPeerHash){
            if(columnsPeerHash.containsKey(clientNameHash.get(clientSocket))){  //clientSocket对应游戏创建者时直接删掉
                columnsPeerHash.remove((String)clientNameHash.get(clientSocket));
            }
            if(columnsPeerHash.containsValue(clientNameHash.get(clientSocket))){  //clientSocket对应游戏加入者时改变其key对应的值
                columnsPeerHash.put((String)getHashKey(columnsPeerHash,(String)clientNameHash.get(clientSocket)), "tobeclosed");
            }
        }
        synchronized(clientDataHash){
            clientDataHash.remove(clientSocket);
        }
        synchronized(clientNameHash){
            clientNameHash.remove(clientSocket);
        }
        sendPublicMsg(getUserList());
        sendPublicMsg(getWaitList());
        serverMsgPanel.statusLabel.setText("当前连接数：" + clientDataHash.size());
        try {
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isClientClosed = true;
    }

    public void run(){
        DataInputStream inputData;
        synchronized(clientDataHash){
            serverMsgPanel.statusLabel.setText("当前连接数：" + clientDataHash.size());
        }
        try {
            //等待连接到主机的信息
            inputData = new DataInputStream(clientSocket.getInputStream());
            sendInitMsg();
            while(true){
                String message = inputData.readUTF();
                dealWithMsg(message);
            }
        } catch (Exception e) {
        }finally{
            if(!isClientClosed){
                closeClient();
            }
        }
    }

}
