package cn.edu.nju.p.utils;

import cn.edu.nju.p.cache.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;

/**
 * Created by Xihao on 2017/6/14.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class DeleteCacheTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisCache redisCache;

    @Test
    public void deleteStockVolumeCache(){

        Set<String> keys = redisTemplate.keys("getStockVolumeFromRedis*");
        for (String key : keys) {
            redisTemplate.delete(key);
        }
    }

    @Test
    public void deleteStockVolumeByRedisCache(){

        redisCache.deleteCacheWithPattern("getStockVolumeFromRedis*");
    }
}
