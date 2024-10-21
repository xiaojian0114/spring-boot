package org.example.cache;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.example.config.RedisCache;
import org.example.config.RedisKeys;
import org.example.vo.UserLoginVO;
import java.util.ArrayList;
import java.util.List;
import static org.example.config.RedisCache.HOUR_SIX_EXPIRE;

@Component
@AllArgsConstructor

public class TokenStoreCache {



    private final RedisCache redisCache;

    public void saveUser( String accessToken,UserLoginVO user) {
        String accessTokenKey = RedisKeys.getAccessTokenKey(accessToken);
        String userIdKey = RedisKeys.getUserIdKey(user.getPkId());
        if (redisCache.get(userIdKey) != null ) {
            redisCache.delete(String.valueOf(redisCache.get(userIdKey)));
        }

        System.out.println("[TokenStoreCache] accessToken = "+accessToken);
        redisCache.set(userIdKey,accessToken,HOUR_SIX_EXPIRE);
        redisCache.set(accessTokenKey,user,HOUR_SIX_EXPIRE);
    }
    public UserLoginVO getUser(String accessToken ) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return JSON.to(UserLoginVO.class,redisCache.get(key));
    }

    public void deleteUser(String accessToken) {
        String key=RedisKeys.getAccessTokenKey(accessToken);
        redisCache.delete(key);
    }
    public void deleteUserById(Long id ) {
        String userId = RedisKeys.getUserIdKey(id);
        String key=String.valueOf(redisCache.get(userId));
        redisCache.delete(key);
    }

    public void deleteUserByIds(List<Long> ids) {
       List<String> keys = new ArrayList<>();
       for (Long id : ids) {
           String userId = RedisKeys.getUserIdKey(id);
           String key = String.valueOf(redisCache.get(userId));
           keys.add(key);
       }
       redisCache.delete(keys);
    }
}
