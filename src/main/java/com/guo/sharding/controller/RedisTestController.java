package com.guo.sharding.controller;

import com.alibaba.fastjson.JSONObject;
import com.guo.sharding.config.redis.JedisClientCluster;
import com.guo.sharding.config.redis.RedisConfig;
import com.guo.sharding.config.redis.RedisProperties;
import com.guo.sharding.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Resource
    private RedisProperties redisProperties;

    @Resource
    private RedisConfig redisConfig;

    @Resource
    private JedisClientCluster jedisClientCluster;

    @GetMapping("/list")
    public String testRedis() {
        List<Person> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setAge(30+i);
            person.setName("张三");
            person.setPassword("123456789");

            System.out.println("哈哈哈：" + redisProperties.toString());
            System.out.println("哈哈哈：" + redisConfig.getJedisCluster().getClusterNodes());
            System.out.println(jedisClientCluster.set("yp", "123456"));
            System.out.println(jedisClientCluster.get("yp"));
            System.out.println(jedisClientCluster.get("12"));
            list.add(person);
        }

        String string = JSONObject.toJSONString(list);
        jedisClientCluster.set("person集合",string);
        jedisClientCluster.set("嘿嘿嘿", "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body style=\"color: #666; font-size: 14px; font-family: 'Open Sans',Helvetica,Arial,sans-serif;\">\n" +
                "<div class=\"box-content\" style=\"width: 80%; margin: 20px auto; max-width: 1500px; min-width: 600px;\">\n" +
                "    <div class=\"header-tip\" style=\"font-size: 12px;\n" +
                "                                   color: #aaa;\n" +
                "                                   text-align: right;\n" +
                "                                   padding-right: 25px;\n" +
                "                                   padding-bottom: 10px;\">\n" +
                "    </div>\n" +
                "    <div class=\"info-top\" style=\"padding: 15px 25px;\n" +
                "                                 border-top-left-radius: 10px;\n" +
                "                                 border-top-right-radius: 10px;\n" +
                "                                 background: {0};\n" +
                "                                 color: #fff;\n" +
                "                                 overflow: hidden;\n" +
                "                                 line-height: 32px;\">\n" +
                "        <div style=\"color:#010e07\"><strong>分销平台产品列表通知</strong></div>\n" +
                "    </div>\n" +
                "    <div class=\"info-wrap\" style=\"border-bottom-left-radius: 10px;\n" +
                "                                  border-bottom-right-radius: 10px;\n" +
                "                                  border:1px solid #ddd;\n" +
                "                                  overflow: hidden;\n" +
                "                                  padding: 15px 15px 20px;\">\n" +
                "        <div class=\"tips\" style=\"padding:15px;\">\n" +
                "            <p style=\" list-style: 160%; margin: 10px 0;\">Hi,</p>\n" +
                "            <p style=\" list-style: 160%; margin: 10px 0;\">{1}</p>\n" +
                "        </div>\n" +
                "        <div class=\"time\" style=\"text-align: right; color: #999; padding: 0 15px 15px;\">{2}</div>\n" +
                "        <br>\n" +
                "        <table class=\"list\" style=\"width: 100%; border-collapse: collapse; border-top:1px solid #eee; font-size:12px; table-layout: fixed;\">\n" +
                "            <thead>\n" +
                "            <tr style=\" background: #fafafa; color: #333; border-bottom: 1px solid #eee;\">\n" +
                "                {3}\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "            {4}\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        return jedisClientCluster.get("嘿嘿嘿");
    }

}
