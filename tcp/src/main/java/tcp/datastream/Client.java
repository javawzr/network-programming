package tcp.datastream;

import constants.SignalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author wzr
 * @version TODO
 * @Description TODO
 * @ClassName tcp.client.ClientDemo.java
 * @createTime 2021年10月31日 18:37:00
 */
public class Client {
    private static String host = "localhost";// 默认连接到本机
    private static int port = 8088;// 默认连接到端口8088
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
/**
 * @方法描述：DataInputStream ,DataOutputStream 实现的客户端
 * @作者： wzr
 * @创建时间： 23:00
 */
    public static void chat() {//chat方法

        // 连接到服务器
        try {
            Socket socket = new Socket(host, port);//创建Socket类对象
            DataInputStream in = new DataInputStream(socket.getInputStream());// 读取服务器端传过来信息的DataInputStream
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());// 向服务器端发送信息的DataOutputStream
            Scanner scanner = new Scanner(System.in);// 装饰标准输入流，用于从控制台输入
            while (true) {
                System.out.println("请输入信息：");
                String send = scanner.nextLine();//读取控制台输入的内容
                System.out.println("客户端：" + send);//输出键盘输出内容提示 ，也就是客户端向服务器端发送的消息
                if (SignalConstant.BYE.equals(send)) {
                    break;
                }
                // 把从控制台得到的信息传送给服务器
                out.writeUTF("客户端：" + send);//将客户端的信息传递给服务器
                String accpet = in.readUTF();// 读取来自服务器的信息
                System.out.println(accpet);//输出来自服务器的信息
            }
        } catch (IOException e) {//捕获异常
            e.printStackTrace();
            logger.error("客户端异常：{}",e);
        }
    }

    public static void main(String[] args) throws IOException {
        chat();

    }
}
