package sample.chats;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
    public client(int port){
        try {
            // 创建8888端口
            Socket s = new Socket("127.0.0.1", port);

            // 启动发送消息线程
            new SendThread(s).start();
            // 启动接受消息线程
            new RecieveThread(s).start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
