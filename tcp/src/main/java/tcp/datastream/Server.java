package tcp.datastream;

import constants.SignalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * @方法描述：一个简单的tcp 服务端
     * @作者： wzr
     * @创建时间： 18:40
     */
    public static void service() {//创建service方法
        try (//创建  ServerSocket类
             ServerSocket server = new ServerSocket(PORT);
             // 等待客户连接
             Socket socket = server.accept();
             // 读取客户端传过来信息的DataInputStream
             DataInputStream in = new DataInputStream(socket.getInputStream());
             // 向客户端发送信息的DataOutputStream
             DataOutputStream out = new DataOutputStream(socket.getOutputStream()); ){

            Scanner scanner = new Scanner(System.in);//从键盘接受数据
            while (true) {
                String accpet = in.readUTF();// 读取来自客户端的信息
                System.out.println(accpet);//输出来自客户端的信息
                System.out.println("请回消息：");
                String send = scanner.nextLine();//nextLine方式接受字符串
                System.out.println("服务器：" + send);//输出提示信息
                if (SignalConstant.BYE.equals(send)) {
                    break;
                }
                out.writeUTF("服务器：" + send);//把服务器端的输入发给客户端
            }
        }catch (IOException e){
            logger.error("服务端发生异常：{}",e);
        }
    }
    public static void main(String[] args) throws IOException {
        service();
    }

}
