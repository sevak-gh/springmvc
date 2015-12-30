package com.infotech.ivr.reporting.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * request matcher for ajax.
 *
 * @author Sevak Gharibian
 *
 */
public class AjaxRequestMatcher implements RequestMatcher {

    private static final Logger LOG = LoggerFactory.getLogger(AjaxRequestMatcher.class);

    @Override
    public boolean matches(HttpServletRequest request) {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return true;
        }
        return false;
    }
}
