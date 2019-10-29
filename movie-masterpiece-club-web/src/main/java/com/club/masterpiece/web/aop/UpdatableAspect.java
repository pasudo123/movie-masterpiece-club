package com.club.masterpiece.web.aop;

import com.club.masterpiece.web.updatestate.service.UpdateStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by pasudo123 on 2019-10-30
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class UpdatableAspect {

    @Value("${table.user}")
    private String userTable;

    @Value("${table.article}")
    private String articleTable;

    @Value("${table.comment}")
    private String commentTable;

    private final UpdateStateService updateStateService;

    @AfterReturning("@annotation(com.club.masterpiece.web.annotation.UpdatableState)")
    public void afterUpdateOnMethod(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        Class clazz = signature.getDeclaringType();


        String packageClassName = clazz.getName();
        String className = clazz.getSimpleName();
        String methodName = signature.getName();

        log.debug("=======================================================>");
        log.debug("\n\npackageFullName : {}\nclassName : {}\nmethodName : {}\n", packageClassName, className, methodName);
        log.debug("=======================================================>");

        if(className.toLowerCase().contains(userTable)) {
            updateStateService.doUpdate(userTable);
            return;
        }

        if(className.toLowerCase().contains(articleTable)) {
            updateStateService.doUpdate(articleTable);
            return;
        }

        if(className.toLowerCase().contains(commentTable)) {
            updateStateService.doUpdate(commentTable);
        }
    }
}
