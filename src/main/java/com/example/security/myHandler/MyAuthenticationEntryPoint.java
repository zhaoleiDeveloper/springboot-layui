package com.example.security.myHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2019/11/20 17:12
 * Modified By:
 * Description: 未登陆处理
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        try {
            LOGGER.warn("***not login,url:{}",httpServletRequest.getRequestURI());
            //ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.LOGIN_WITHOUT);
        } catch (Exception ee) {
            LOGGER.error("***commence throw e:{}", e.getMessage());
        }
    }
}