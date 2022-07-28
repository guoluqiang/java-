package com.guo.sharding.config.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Component
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public JedisCluster getJedisCluster(){
        //获取redis集群的ip及端口号等相关信息；
        String[] serverArray = redisProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        //遍历add到HostAndPort中；
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            //System.out.println("ipPort = " + ipPortPair[0].trim()+Integer.valueOf(ipPortPair[1].trim()));
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        //构建对象并返回；
        //System.out.println("nodes.toString() = " + nodes.toString());
        return new JedisCluster(nodes, redisProperties.getCommandTimeout());
    }
}
