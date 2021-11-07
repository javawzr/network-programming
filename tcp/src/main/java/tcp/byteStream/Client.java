package tcp.byteStream;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
    public static void main(String[] args) throws IOException {
        inputStreamClient();
    }
    /**
     * @方法描述：字节流客户端
     * @作者： wzr
     * @创建时间： 22:59
     */
    public static void inputStreamClient() throws IOException {
        try(  Socket socket = new Socket(host, port);//创建Socket类对象
              // 输入流
              InputStream inputStream = socket.getInputStream();
              // 输出流
              OutputStream outputStream = socket.getOutputStream();){
            // 字节流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 键盘输入
            Scanner sc = new Scanner(System.in);

            while (true){
                System.out.println("客户端数据：");
                // 读取键盘输入
                String str = sc.nextLine();
                // 发送数据
                outputStream.write(str.getBytes(StandardCharsets.UTF_8));
                if (str.equals("bye")) {
                    socket.shutdownOutput();
                    System.out.println("输出流关闭了，我不会再发数据了");
                }
                byte[] bte= new byte[8];
                int len=0;
                do{
                    len =inputStream.read(bte);
                    if (len != -1) {
                        bos.write(bte,0,len);
                    }
                }while (len == bte.length);
                String recStr = new String(bos.toByteArray());
                bos.reset();
                System.out.println(recStr);
                // 断开连接信号
                if (recStr.equals("bye")) {
                    socket.shutdownInput();
                    System.out.println("数据流关闭了，我不再接收数据了");
                }
                if (socket.isInputShutdown() && socket.isOutputShutdown()){
                    socket.close();
                    return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
