package com.infotech.springmvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * request time logger.
 *
 * @author Sevak Gharibian
 *
 */
@Component
public class RequestTimeLogger extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger("profiler");
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                             HttpServletResponse response, 
                             Object handler) throws Exception {
        long time = System.currentTimeMillis();
        request.setAttribute("startTime", time);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler, 
                           ModelAndView modelAndView) throws Exception {
        long time = System.currentTimeMillis();
        request.setAttribute("startViewTime", time);
   }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception exceptionIfAny) throws Exception {
        long time = System.currentTimeMillis();
        long startTime = (long)request.getAttribute("startTime");
        long startViewTime = (long)request.getAttribute("startViewTime");
        LOG.info("{} --> total:{}, start-view:{}, view-end:{}", 
                    request.getRequestURI(), 
                    time-startTime, 
                    startViewTime - startTime,
                    time - startViewTime);
    }
}
