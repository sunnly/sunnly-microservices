package wang.sunnly.micro.services.scannable.tools.zookeeper.test;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DistributeClient
 *
 * @author Sunnly
 * @since 2019/7/2 0002 18:59
 */
public class DistributeClient {

    public static void main(String[] args) throws Exception {
        DistributeClient client = new DistributeClient();
        //1.获取zookeeper
        client.getConnect();
        //2.注册监听
        client.getChildren();
        //3.业务处理
        client.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getChildren() throws KeeperException, InterruptedException {

        List<String> children = zkClient.getChildren("/servers", true);

        ArrayList<String> hosts = new ArrayList<>();
        for (String child : children){
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            hosts.add(new String(data));
        }

        System.out.println(hosts);
    }

    private String connectString = "192.168.101.13:2181,192.168.101.13:2182,192.168.101.13:2183";
//    private String connectString = "192.168.101.69:2181,192.168.101.70:2181,192.168.101.71:2181";
    private int sessionTimeout = 2000;
    ZooKeeper zkClient;
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    getChildren();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
