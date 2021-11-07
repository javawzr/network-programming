package tcp.dataStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author wzr
 * @version TODO
 * @Description TODO
 * @ClassName tcp.servce.ServerDemo.java
 * @createTime 2021年10月31日 18:37:00
 */
public class Server {
    private static final  int PORT = 8088;
    /**
     * @方法描述：一个简单的tcp 服务端
     * @作者： wzr
     * @创建时间： 18:40
     */
    public static void service() {//创建service方法
        try {// 建立服务器连接
            ServerSocket server = new ServerSocket(PORT);//创建  ServerSocket类
            Socket socket = server.accept();// 等待客户连接
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());// 读取客户端传过来信息的DataInputStream
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());// 向客户端发送信息的DataOutputStream
                Scanner scanner = new Scanner(System.in);//从键盘接受数据
                while (true) {
                    String accpet = in.readUTF();// 读取来自客户端的信息
                    System.out.println(accpet);//输出来自客户端的信息
                    System.out.println("请回消息：");
                    String send = scanner.nextLine();//nextLine方式接受字符串
                    System.out.println("服务器：" + send);//输出提示信息
                    out.writeUTF("服务器：" + send);//把服务器端的输入发给客户端
                }
            } finally {// 建立连接失败的话不会执行socket.close();
                socket.close();//关闭连接
                server.close();//关闭
            }
        } catch (IOException e) {//捕获异常
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        service();
    }

}
