package com.bit.jsptest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.bit.jsptest.model..*Service.*(..))")
    public void loggin(JoinPoint joinPoint) {
        logger.debug("메서드 선언부 BEFORE : {} 전달 파라미터 : {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    //
//	@Around(value = "execution(* com.bit.guestbook.model..GuestBook*.*(..))")
//	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
//
//		Object proceed = joinPoint.proceed();
//
//		stopWatch.stop();
//
//		logger.debug("summary : {}", stopWatch.shortSummary());
//		logger.debug("totalTime : {}", stopWatch.getTotalTimeMillis());
//		logger.debug("pretty : {}", stopWatch.prettyPrint());
//
//		return proceed;
//	}
//
    @AfterReturning(value = "execution(* com.bit.jsptest.model..GuestBook*.list*(..))", returning = "obj")
    public void afterReturningMethod(JoinPoint joinPoint, Object obj) {
        logger.debug("afterReturning call method : {} ", joinPoint.getSignature());
        logger.debug("return value : {}", obj);
    }

    @AfterThrowing(value = "execution(* com.bit.jsptest.model..GuestBook*.list*(..))", throwing = "exception")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) {
        logger.debug("afterThrowing call method : {}", joinPoint.getSignature());
        logger.debug("exception : {}", exception);
    }

    @After(value = "execution(* com.bit.jsptest.model..GuestBook*.list*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        logger.debug("after call method : {}", joinPoint.getSignature());
    }
}