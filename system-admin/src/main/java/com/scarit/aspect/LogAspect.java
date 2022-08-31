package com.scarit.aspect;

import com.scarit.annotation.Log;
import com.scarit.core.RedisTemplate;
import com.scarit.entity.ExerciseOperLog;
import com.scarit.service.ExerciseOperLogService;
import com.scarit.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Resource
    private ExerciseOperLogService exerciseOperLogService;

    @Resource
    private RedisTemplate redisTemplate;

//    @Resource
//    private ExecutorService executorService;

    private BeanFactory beanFactory;

//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory = beanFactory;
//    }


        @AfterReturning("@annotation(operLog)")
    public void afterReturning(JoinPoint joinPoint, Log operLog) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            ExerciseOperLog exerciseOperLog= createOperLog(request, operLog, joinPoint, null);
            exerciseOperLogService.insert(exerciseOperLog);
//            LogAspect logAspect = beanFactory.getBean(this.getClass());
//            logAspect.logHandler(operLog);

    }

    @AfterThrowing(value = "@annotation(operLog)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Log operLog, Exception exception) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        ExerciseOperLog exerciseOperLog = createOperLog(request, operLog, joinPoint, exception);
        //这个方法是异步的
        exerciseOperLogService.insert(exerciseOperLog);
//        LogAspect logAspect = beanFactory.getBean(this.getClass());
//        logAspect.logHandler(operLog);
    }


//    @Async("scarit-logger")
//    public void logHandler(ExerciseOperLog operLog) {
//        //保存日志对象
//        operLogService.insert(operLog);
//    }

    private ExerciseOperLog createOperLog(HttpServletRequest request, Log log, JoinPoint joinPoint, Exception exception) {
        //根据现场信息封装日志实例
        ExerciseOperLog operLog = new ExerciseOperLog();
        operLog.setTitle(log.tittle());
        operLog.setBusinessType(log.businessType());
        if (exception != null) {
    operLog.setErrormsg(exception.getMessage().length() > 1000 ?
            exception.getMessage().substring(0, 1000) : exception.getMessage());
    operLog.setStatus(500);
} else {
    operLog.setStatus(200);
}

        //从request中获取一些信息
        operLog.setOperIp(request.getRemoteAddr());
        operLog.setRequestMethod(request.getMethod());
        //注意空指针的问题
        if (AuthUtil.getLoginUser(redisTemplate) != null && AuthUtil.getLoginUser(redisTemplate).getExerciseUser() != null) {
    operLog.setOperName(AuthUtil.getLoginUser(redisTemplate).getExerciseUser().getUserName());
}
        operLog.setOperUrl(request.getRequestURI());

        //获取执行的方法
        String methodName = joinPoint.getSignature().getName();
        operLog.setMethod(methodName);
        operLog.setOpertime(new Date());
        return operLog;
    }
}

//    /**
//     * 真正执行添加日志的方法
//     * @param request
//     * @param log
//     * @param joinPoint
//     * @param exception
//     */
//    private void logHandler(HttpServletRequest request, Log log, JoinPoint joinPoint ,Exception exception) {
//

//    //提交任务
//    //根据现场信息封装日志实例
//    ExerciseOperLog operLog = new ExerciseOperLog();
//            operLog.setTitle(log.tittle());
//            operLog.setBusinessType(log.businessType());
//            if (exception != null) {
//        operLog.setErrormsg(exception.getMessage().length() > 1000 ?
//                exception.getMessage().substring(0, 1000) : exception.getMessage());
//        operLog.setStatus(500);
//    } else {
//        operLog.setStatus(200);
//    }
//
//    //从request中获取一些信息
//            operLog.setOperIp(request.getRemoteAddr());
//            operLog.setRequestMethod(request.getMethod());
//    //注意空指针的问题
//            if (AuthUtil.getLoginUser(redisTemplate) != null && AuthUtil.getLoginUser(redisTemplate).getExerciseUser() != null) {
//        operLog.setOperName(AuthUtil.getLoginUser(redisTemplate).getExerciseUser().getUserName());
//    }
//            operLog.setOperUrl(request.getRequestURI());
//
//    //获取执行的方法
//    String methodName = joinPoint.getSignature().getName();
//            operLog.setMethod(methodName);
//            operLog.setOpertime(new Date());
//
//        executorService.execute(() -> {
//        //保存日志对象
//        operLogService.insert(operLog);
//    });

