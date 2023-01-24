package e101.hishop.global.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RequestLogAspect {
    @Around("execution(* e101.hishop.controller..*Controller.*(..))")
    public Object requestControllerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object target = joinPoint.getTarget();
        log.info("============================={}:   {}:  {}==============", request.getMethod(), request.getRequestURI(), target.getClass().getSimpleName());
        return joinPoint.proceed();
    }
}
