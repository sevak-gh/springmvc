package com.infotech.ivr.reporting.util;

import com.infotech.ivr.reporting.domain.Audit;
import com.infotech.ivr.reporting.service.AuditService;

import java.util.Arrays;
import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AuditService auditService;

    @Autowired
    public AuditLogger(AuditService auditService) {
        this.auditService = auditService;
    }

    // after execution 
    @After("execution(public * com.infotech.ivr.reporting.service..*.*(..))"
            + " && !execution(public * com.infotech.ivr.reporting.service.AuditService.*(..))")
    public void log(JoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "null";
        if (authentication != null) {
            username = authentication.getName();
        }
        Audit audit = new Audit();
        audit.setUsername(username);
        audit.setAction(joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName());
        audit.setInfo(Arrays.toString(joinPoint.getArgs()));
        audit.setDateTime(LocalDateTime.now()); 
        auditService.save(audit);
        LOG.info("[user:{}]{}({})",
                  username,  
                  audit.getAction(),
                  audit.getInfo());
    }
}
