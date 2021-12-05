package tcp.grizzly.client;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author wzr
 * @version TODO
 * @Description TODO
 * @ClassName tcp.grizzly.client.ClientFilter.java
 * @createTime 2021年12月05日 20:33:00
 */
public class ClientFilter extends BaseFilter {
    private static final Logger logger = Grizzly.logger(ClientFilter.class);
    @Override
    public NextAction handleRead(FilterChainContext ctx) throws IOException {
        String message = ctx.getMessage();
        logger.info("server :"+message);
        return ctx.getStopAction();
    }
}
