package chen.sport.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * @Author: Yiheng Chen
 * @Description: Jedis测试类
 * @Date: Created in 19:45 2017/8/19
 * @Modified by:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JedisTest{
    @Autowired
    private Jedis jedis;
    @Test
    public void testRedis() {
        // 创建redis客户端对象并指定服务器地址 端口默认为6379
        Jedis jedis = new Jedis("192.168.56.201", 6379);
        // 使redis中的pno key值加1
        Long incr = jedis.incr("mykey");
        System.out.println(incr);
    }

    @Test
    public void testRedis2() {
        Long value = jedis.incr("mykey");
        System.out.println(value);
    }

}
