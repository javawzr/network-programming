package tcp.grizzly.server;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import tcp.grizzly.client.EchoClient;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author wzr
 * @version TODO
 * @Description TODO
 * @ClassName tcp.grizzly.server.EchoFilter.java
 * @createTime 2021年12月05日 13:51:00
 */
public class ServerFilter extends BaseFilter {
    private static final Logger logger = Grizzly.logger(EchoClient.class);
    @Override
    public NextAction handleRead(FilterChainContext ctx) throws IOException {
        Object address = ctx.getAddress();
        String message = ctx.getMessage();
        logger.info("client: "+message);

        message="sever receive message is "+message;
        ctx.write(address,message,null);
        return ctx.getStopAction();
    }

}
