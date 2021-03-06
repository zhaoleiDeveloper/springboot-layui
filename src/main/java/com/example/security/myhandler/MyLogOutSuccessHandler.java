package com.example.security.myhandler;

import com.example.security.userdetails.MyUserDetailService;
import com.example.security.userdetails.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2020/3/10 21:44
 * Modified By:
 * Description:
 */
@Component
public class MyLogOutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyLogOutSuccessHandler.class);

    private SessionRegistry sessionRegistry;
    private MyUserDetailService myUserDetailService;

    @Autowired
    public MyLogOutSuccessHandler(SessionRegistry sessionRegistry, MyUserDetailService myUserDetailService) {
        this.sessionRegistry = sessionRegistry;
        this.myUserDetailService = myUserDetailService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        //其实在退出时，servlet中session会被注销掉，注销时会触发自定义的session注销监听实现sessionRegistry中的session失效，这里也可以实现，不过这里再去实现一遍意义不大
        //myUserDetailService.expireSession(null,userDetails,sessionRegistry);
        httpServletResponse.sendRedirect("/login");
        LOGGER.error(userDetails.getUsername() + "：退出登陆");
    }
}
