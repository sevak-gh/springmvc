package com.infotech.ivr.reporting.util;

import com.infotech.ivr.reporting.domain.User;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

/**
 * audit logger.
 *
 * @author Sevak Gharibian
 */
@Aspect
@Component
public class AuditLogger {

    private static final Logger LOG = LoggerFactory.getLogger("audit");

    // after execution 
    @After("execution(public * com.infotech.ivr.reporting.service..*.*(..))")
    public void log(JoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "null";
        if (authentication != null) {
            username = authentication.getName();
        }
        LOG.info("[user:{}]{}.{}({})",
                  username,  
                  joinPoint.getSignature().getDeclaringType().getSimpleName(),
                  joinPoint.getSignature().getName(),
                  joinPoint.getArgs());
    }
}
