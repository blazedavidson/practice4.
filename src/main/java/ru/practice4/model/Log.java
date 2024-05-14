package ru.practice4.model;

import ru.practice4.beans.FilesReadData;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.time.LocalDateTime;

@Aspect
@Component
public class Log {
    @Autowired
    FilesReadData readData;
    @Pointcut("@annotation(LogTransformation)")
    public void loggableMethod() {}
    @Around("loggableMethod()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        String classTitle   = method.getDeclaringType().getSimpleName();
        LocalDateTime currentTime = LocalDateTime.now();
        StopWatch watcher = new StopWatch();
        watcher.start();
        try {return joinPoint.proceed();}
        finally {
            watcher.stop();
            LogTransformation logInfo = method.getMethod().getAnnotation( LogTransformation.class);
            if (logInfo != null) {readData.insertLog( classTitle, logInfo.value(), currentTime, watcher.getTotalTimeMillis());}
        }
    }
}