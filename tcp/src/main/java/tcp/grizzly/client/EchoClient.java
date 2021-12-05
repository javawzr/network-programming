package tcp.grizzly.client;

import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.GrizzlyFuture;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.glassfish.grizzly.utils.StringFilter;
import tcp.grizzly.server.EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * @author wzr
 * @version TODO
 * @Description TODO
 * @ClassName tcp.grizzly.client.EchoClient.java
 * @createTime 2021年12月05日 20:46:00
 */
public class EchoClient {
    private static final Logger logger = Grizzly.logger(EchoClient.class);
    public static void main(String[] args) {
        FilterChainBuilder stateless = FilterChainBuilder.stateless();
        stateless.add(new TransportFilter());
        stateless.add(new StringFilter(Charset.forName("UTF-8")));
        stateless.add(new ClientFilter());
        TCPNIOTransport build = TCPNIOTransportBuilder.newInstance().build();
        build.setProcessor(stateless.build());
        GrizzlyFuture<Connection> connect = build.connect(EchoServer.HOST, EchoServer.PORT);
        Connection connection= null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            build.start();
            connection= (Connection) connect.get(10, TimeUnit.SECONDS);
            logger.info("begin .... if you want to exit please read in q");
            do {
                String line = br.readLine();
                if ("q".equals(line)){
                    break;
                }
                connection.write(line);
            }while(true);
        } catch (IOException | InterruptedException|ExecutionException|TimeoutException e) {
            e.printStackTrace();
            if (e instanceof  InterruptedException){
                Thread.currentThread().interrupt();
            }
        }finally{
            if(connection!= null)connection.close();
        }
    }
}
