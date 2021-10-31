package tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author wzr
 * @version TODO
 * @Description TODO
 * @ClassName tcp.client.ClientDemo.java
 * @createTime 2021年10月31日 18:37:00
 */
public class ClientDemo {
    private static String host = "localhost";// 默认连接到本机
    private static int port = 8088;// 默认连接到端口8088

    public static void chat() {//chat方法
        try {
            // 连接到服务器
            Socket socket = new Socket(host, port);//创建Socket类对象
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());// 读取服务器端传过来信息的DataInputStream
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());// 向服务器端发送信息的DataOutputStream
                Scanner scanner = new Scanner(System.in);// 装饰标准输入流，用于从控制台输入
                while (true) {
                    System.out.println("请输入信息：");
                    String send = scanner.nextLine();//读取控制台输入的内容
                    System.out.println("客户端：" + send);//输出键盘输出内容提示 ，也就是客户端向服务器端发送的消息
                    // 把从控制台得到的信息传送给服务器
                    out.writeUTF("客户端：" + send);//将客户端的信息传递给服务器
                    String accpet = in.readUTF();// 读取来自服务器的信息
                    System.out.println(accpet);//输出来自服务器的信息
                }

            } finally {
                socket.close();//关闭Socket监听
            }
        } catch (IOException e) {//捕获异常
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        chat();
    }
}
