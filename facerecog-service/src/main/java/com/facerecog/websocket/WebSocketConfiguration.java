package com.facerecog.websocket;


import com.facerecog.interceptor.WebSocketInterceptor;
import com.facerecog.utils.SystemConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Classname WebSocketConfig
 * @Description websocket服务端实现类
 * @Date 2018/10/23 9:54
 * @Created by cjw
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration extends WebMvcConfigurationSupport implements WebSocketConfigurer {

    private static final Logger LOGGER= LoggerFactory.getLogger(WebSocketConfiguration.class);

    //需要用注入的方式，SocketMessageHandle才能交给spring管理
    @Autowired
    SocketMessageHandle handle;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        LOGGER.info("websocke配置");
        registry.addHandler(handle, SystemConfig.WEB_SOCKET_URL).addInterceptors(new WebSocketInterceptor());
    }
}
