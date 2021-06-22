package sample.chats;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public server(int port){
        try {

            // 监听8888端口
            ServerSocket ss = new ServerSocket(port);

            System.out.println("监听在端口号:8888");
            Socket s = ss.accept();

            //启动发送消息线程
            new SendThread(s).start();
            //启动接受消息线程
            new RecieveThread(s).start();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
