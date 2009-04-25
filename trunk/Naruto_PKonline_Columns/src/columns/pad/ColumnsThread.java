
package columns.pad;

/**
 *
 * @author SHY
 */
public class ColumnsThread extends Thread{
    ColumnsPad currentPad;
    public ColumnsThread(ColumnsPad currentPad){
        this.currentPad = currentPad;
    }

    //处理获得的信息
//    public void dealWithMsg(String msgReceived){
//        if(msgReceived.startsWith("/columns")){
//
//        }else if(msgReceived.startsWith("/yourname")){
//            currentPad.columnsSelfName = msgReceived.substring(10);
//        }else if(msgReceived.equals("/error")){
//            currentPad.statusText.setText("用户不存在，请重新加入！");
//        }else{
//
//        }
//    }

    //发送信息
//    public void sendMessage(String sndMessage){
//        try {
//            currentPad.outputData.writeUTF(sndMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void run(){
        String msgReceived = "";
//        try {
//            while(true){      //等待信息输入
//                msgReceived = currentPad.inputData.readUTF();
//                System.out.println("columnsThread "  + msgReceived);
//                dealWithMsg(msgReceived);
//            }
//        } catch (Exception e) {
//        }
    }
}
