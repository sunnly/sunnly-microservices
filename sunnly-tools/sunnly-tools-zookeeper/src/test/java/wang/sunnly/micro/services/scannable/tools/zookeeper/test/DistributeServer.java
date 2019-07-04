package wang.sunnly.micro.services.scannable.tools.zookeeper.test;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * DistributeServer
 * * @author Sunnly
 * @create 2019/7/2 0002 18:45
 */
public class DistributeServer {

    public static void main(String[] args) throws Exception {
        DistributeServer server = new DistributeServer();
        //1.获取集群连接
        server.getConnect();
        //2.注册
        server.regist(args[0]);
        //3.业务处理
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String hostname) throws KeeperException, InterruptedException {
        String path = zkClient.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

    }

    private String connectString = "192.168.101.13:2181,192.168.101.13:2182,192.168.101.13:2183";
    private int sessionTimeout = 2000;
    ZooKeeper zkClient;
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }

}
