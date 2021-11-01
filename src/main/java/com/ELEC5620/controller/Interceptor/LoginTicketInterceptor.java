package com.ELEC5620.controller.Interceptor;


import com.ELEC5620.dao.LoginTicketMapper;
import com.ELEC5620.dao.UserMapper;
import com.ELEC5620.entity.LoginTicket;
import com.ELEC5620.entity.User;
import com.ELEC5620.util.CookieUtil;
import com.ELEC5620.util.HostHolder;
import com.ELEC5620.util.NineyardConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ticket = CookieUtil.getValue(request, "ticket");
        if (ticket != null) {
            LoginTicket loginTicket = loginTicketMapper.selectByTicket(ticket);
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
                User user = userMapper.selectById(loginTicket.getUserId());
                hostHolder.setUser(user);
                String url = request.getContextPath() + "/user/create-company/" + user.getId();
                if (user.getCompanyId() < 0 && !request.getRequestURI().equals(url) && user.getType() == NineyardConstants.TYPE_PM) {
                    response.sendRedirect(request.getContextPath() + "/user/create-company/" + user.getId());
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
