package com.rfiot.ams.common.logging;//package com.rfiot.ams.rfiot.ams.common.logging;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Slf4j
//@Aspect
//@Component
//public class LoggingAspect {
//
//    @Before("execution(* com.rfiot.ams..*(..))")
//    public void logBeforeMethodExecution(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().toShortString();
//        String args = Arrays.toString(joinPoint.getArgs());
//        log.debug("Executing method: {} with arguments: {}", methodName, args);
//    }
//
//    @AfterReturning(
//            pointcut = "execution(* com.rfiot.ams..*(..))",
//            returning = "result")
//    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
//        String methodName = joinPoint.getSignature().toShortString();
//        log.debug("Method: {} returned: {}", methodName, result);
//    }
//
//    @AfterThrowing(
//            pointcut = "execution(* com.rfiot.ams..*(..))",
//            throwing = "exception")
//    public void logOnException(JoinPoint joinPoint, Exception exception) {
//        String methodName = joinPoint.getSignature().toShortString();
//        log.debug("Method: {} threw exception: {}", methodName, exception.getMessage());
//    }
//
//    @After("execution(* com.rfiot.ams..*(..))")
//    public void logAfterMethodExecution(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().toShortString();
//        log.debug("Completed execution of method: {}", methodName);
//    }
//
//    @Around("execution(* com.rfiot.ams..*(..))")
//    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object result = joinPoint.proceed();
//        long endTime = System.currentTimeMillis();
//        long executionTime = endTime - startTime;
//        String methodName = joinPoint.getSignature().toShortString();
//        log.debug("Method: {} executed in {} ms", methodName, executionTime);
//        return result;
//    }
//}
//
//
//
