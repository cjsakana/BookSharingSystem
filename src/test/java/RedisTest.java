import kg.us.sakanatang.config.RedisConfig;
import kg.us.sakanatang.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedisConnection() {
        assertNotNull(redisTemplate);

        String key = "test:key";
        String value = "test value";

        // 测试写入
        redisTemplate.opsForValue().set(key, value);

        // 测试读取
        String retrieved = (String) redisTemplate.opsForValue().get(key);
        assertEquals(value, retrieved);

        // 测试删除
        redisTemplate.delete(key);
        assertNull(redisTemplate.opsForValue().get(key));
    }
    @Test
    public void a(){
        assertNotNull(jedisConnectionFactory);

    }
    @Test
    public void b(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.ping();

    }
    @Test
    public void c(){
//        redisUtil.set("test","1");
//        String code = (String) redisUtil.get("user:verified:3164882851@qq.com");
//        System.out.println(code);
        System.out.println(redisUtil.get("user:verified:3164882851@qq.com"));
    }
}