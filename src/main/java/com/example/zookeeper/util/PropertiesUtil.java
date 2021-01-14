package com.example.zookeeper.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Component
public class PropertiesUtil {
    Properties properties=new Properties();
    public String getProperties(String key){
        return (String)properties.get(key);
    }
    //@PostConstruct//被@PostConstruct注解的方法在服务启动加载servlet时候就执行
    public void init(){
        System.out.println("被@PostConstruct注解的方法在服务启动加载servlet时候就执行...");
        CuratorFramework zkClient= CuratorFrameworkFactory.newClient("172.16.10.113:2181",new RetryOneTime(1000));
        zkClient.start();//启动与zk的连接
        try {
            List<String> configNames=zkClient.getChildren().forPath("/zhoupeng-testbioandnio-config");
            for (String configName : configNames) {
                byte[] value=zkClient.getData().forPath("/zhoupeng-testbioandnio-config/"+configName);
                //将属性保存带properties
                properties.put(configName,new String(value));
            }
            //获取zookeeper的数据变化，动态获取最新的数据
            TreeCache treeCache=new TreeCache(zkClient,"/zhoupeng-testbioandnio-config");
            treeCache.start();
            treeCache.getListenable().addListener(new TreeCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                    switch (treeCacheEvent.getType()){
                        case NODE_UPDATED:
                            //收到数据变化
                            System.out.println("发生了数据变化"+treeCacheEvent.getData());
                            //具体变化的节点
                            String configName=treeCacheEvent.getData().getPath().replace("/zhoupeng-testbioandnio-config/","");
                            String configValue=new String(treeCacheEvent.getData().getData());
                            properties.setProperty(configName,configValue);
                            break;
                            default:
                                break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
