package tcp.grizzly.server;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.glassfish.grizzly.utils.StringFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * @author wzr
 * @version TODO
 * @Description TODO
 * @ClassName tcp.grizzly.server.EchoServer.java
 * @createTime 2021年12月05日 18:56:00
 */
public class EchoServer {
    private static final Logger logger = Grizzly.logger(EchoServer.class);
     public static final String HOST="localhost";
     public static final int PORT=1234;

    public static void main(String[] args) {
        // 设置过滤器链
        FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
        filterChainBuilder.add(new TransportFilter());
        filterChainBuilder.add(new StringFilter(Charset.forName("UTF-8")));
        filterChainBuilder.add(new ServerFilter());
        
        //创建tcp服务端实例
        TCPNIOTransport transPor = TCPNIOTransportBuilder.newInstance().build();
        transPor.setProcessor(filterChainBuilder.build());
        try {
            transPor.bind(HOST,PORT);
            transPor.start();
            logger.info("press any key to stop ");
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String s = bf.readLine();
            while (true){
                if ("exit".equals(s)){
                    logger.info("echo server exit ......");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            transPor.shutdown();
        }

    }
}
