package com.facerecog.ehcache;

import com.facerecog.utils.CommUtil;
import com.facerecog.utils.MD5Util;

/**
 * Token 处理器
 * Created by Administrator on 2018/1/4.
 */
public class TokenProcessor {
    private static TokenProcessor tokenProcessor;

    private TokenProcessor() {
    }

    public static TokenProcessor getInstance() {
        if (tokenProcessor == null) {
            synchronized (TokenProcessor.class) {
                if (tokenProcessor == null) {
                    tokenProcessor = new TokenProcessor();
                }
            }
        }
        return tokenProcessor;
    }

    public String generateToken(String token) {
        return MD5Util.md5(token + CommUtil.createStamp());
    }

    /**
     * 为了保证同一个用户在缓存中只有一条登录信息，服务器端在生成Token后，可以再单独对用户名进行MD5作为Seed，即MD5(username)。
     * 再将Seed作为key，Token作为value保存到缓存中，这样即便Token是变化的，
     * 但每个用户的Seed是固定的，就可以通过Seed索引到Token，再通过Token清除上一次的登录信息，避免重复登录时缓存中保存过多无效的登录信息。
     */
    public String generateSeed(String seed) {
        return MD5Util.md5(seed);
    }
}
