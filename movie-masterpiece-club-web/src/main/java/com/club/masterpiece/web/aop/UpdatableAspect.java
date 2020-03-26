package com.club.masterpiece.web.aop;

import com.club.masterpiece.web.updatestate.service.UpdateStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @AfterReturning("@annotation(com.club.masterpiece.web.annotation.UpdatableState)")
    public void afterUpdateOnMethod(JoinPoint joinPoint) {

//        Signature signature = joinPoint.getSignature();
//        Class clazz = signature.getDeclaringType();
//
//        /**
//         * (1) AOP 따로, Transaction 따로여서 DB 호출이 동기적이지 않다.
//         * (2) OAuth2 유저는 해당 어노테이션을 붙여서 호출되지 않는다.
//         */
//        String packageClassName = clazz.getName();
//        String className = clazz.getSimpleName();
//        String methodName = signature.getName();
//
//        log.debug("=======================================================>");
//        log.debug("\n\npackageFullName : {}\nclassName : {}\nmethodName : {}\n", packageClassName, className, methodName);
//        log.debug("=======================================================>");
//
//        if(className.toLowerCase().contains(userTable)) {
//            updateStateService.doUpdate(userTable);
//            return;
//        }
//
//        if(className.toLowerCase().contains(articleTable)) {
//            updateStateService.doUpdate(articleTable);
//            return;
//        }
//
//        if(className.toLowerCase().contains(commentTable)) {
//            updateStateService.doUpdate(commentTable);
//        }
    }
}
