import com.sun.tools.javac.util.Assert;
import org.junit.Test;
import tcp.client.ClientDemo;
import tcp.servce.ServerDemo;

/**
 * @author wzr
 * @version v1.0
 * @Description tcp 服务端测试用例
 * @ClassName PACKAGE_NAME.TestTcpCase.java
 * @createTime 2021年10月31日 19:51:00
 */
public class TestTcpCase {
    @Test
    public void simpleTcpClinetTest(){
        ClientDemo cd = new ClientDemo();
        cd.chat();
    }
}
