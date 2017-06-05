package cn.edu.nju.p.cache;

import cn.edu.nju.p.utils.EncryptHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by xihao on 17-6-5.
 */
@Service
public class TokenManagerImpl implements TokenManager {

    @Autowired
    RedisCache redisCache;

    @Override
    public String createToken(String phoneNumber) {

        String token = EncryptHelper.getShaEncryption(phoneNumber);
        redisCache.putCacheWithExpireTime(token, phoneNumber, 60 * 20); //20 minutes
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        return !StringUtils.isEmpty(token) && redisCache.getCache(token,String.class)!=null;
    }
}
