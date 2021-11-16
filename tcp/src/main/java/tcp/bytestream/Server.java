package tcp.bytestream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author wzr
 * @version v1.0
 * @Description TODO
 * @ClassName tcp.servce.ServerDemo.java
 * @createTime 2021年10月31日 18:37:00
 */
public class Server {
    // 端口
    private static final  int PORT = 8088;
    public static void main(String[] args) throws IOException {
        inputStreamServer();
    }
    /**
     * @方法描述：使用字节流发送和接受数据
     * @作者： wzr
     * @创建时间： 22:44
     */
    public static void inputStreamServer() throws IOException {
        ServerSocket server = new ServerSocket(PORT);//创建  ServerSocket类
        Socket socket = server.accept();// 等待客户连接
        // 输入流
        InputStream inputStream = socket.getInputStream();
        //输出流
        OutputStream outputStream = socket.getOutputStream();
        //字节输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //键盘上输入
        Scanner sc = new Scanner(System.in);
        byte[] bytes= new byte[32];
        int read =0;
        //读取和发送数据
        while (true){
            do{
                read = inputStream.read(bytes);
                if (read != -1) {
                    bos.write(bytes,0,read);
                }
            }while (read == bytes.length);
            String recStr = bos.toString("UTF-8");
            bos.reset();
            System.out.println(recStr);
            // 断开连接信号
            if ("bye".equals(recStr)) {
                socket.shutdownInput();
                System.out.println("服务端输入流关闭了，我不能在接收数据了");
            }
            System.out.println("服务端输入数据：");
            String str = sc.nextLine();
            outputStream.write(str.getBytes(StandardCharsets.UTF_8));
            if ("bye".equals(str) ) {
                socket.shutdownOutput();
                System.out.println("服务端输出流关闭了，我能再发数据了");
            }
            if (socket.isInputShutdown() && socket.isOutputShutdown()){
                socket.close();
                return;
            }
        }
    }
}
