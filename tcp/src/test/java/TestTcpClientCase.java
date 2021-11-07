import org.junit.Test;
import tcp.dataStream.Client;

/**
 * @author wzr
 * @version v1.0
 * @Description tcp 服务端测试用例
 * @ClassName PACKAGE_NAME.TestTcpCase.java
 * @createTime 2021年10月31日 19:51:00
 */
public class TestTcpClientCase {
    /**
     * @方法描述：通过DataOutputStream ,DataInputStream  发送和读取流
     * @作者： wzr
     * @创建时间： 22:42
     */
    @Test
    public void simpleTcpClinetTest(){
        Client cd = new Client();
        cd.chat();
    }
}
