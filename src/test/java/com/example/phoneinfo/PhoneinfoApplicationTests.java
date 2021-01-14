package com.example.phoneinfo;

import com.example.emp.service.SaleService;
import com.example.zookeeper.util.PropertiesUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneinfoApplicationTests {
    @Autowired
    SaleService saleService;
    @Autowired
    PropertiesUtil propertiesUtil;
    /**
     * 测试设计模式
     */
    @Test
    public void testSale() {
        double d=saleService.sale("vip",100);
        System.out.println("价格是："+d);
    }

    @Test
    public void testNewSale() {
        double d=saleService.newSale("普通",100);
        System.out.println("价格是："+d);
    }

    @Test
    public void testZK(){
        try {
            CuratorFramework zkClient= CuratorFrameworkFactory.newClient("172.16.10.113:2181",new RetryOneTime(1000));
            zkClient.start();//启动与zk的连接
            byte[] b=zkClient.getData().forPath("/"+"zhoupeng-testbioandnio-config"+"/url");
            System.out.println("/"+"zhoupeng-testbioandnio-config"+"/url节点下对应的值："+new String(b));

            //获取节点下的子节点
            List<String> list=zkClient.getChildren().forPath("/"+"zhoupeng-testbioandnio-config");
            System.out.println("/"+"zhoupeng-testbioandnio-config下的子节点是：");
            list.forEach(s -> {
                System.out.println(s);
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    @Test
    public void testPropertiesByZK(){
        System.out.println("url值："+propertiesUtil.getProperties("url"));
    }
}
