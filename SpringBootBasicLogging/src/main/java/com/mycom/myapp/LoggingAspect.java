package com.mycom.myapp;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// com.mycom.myapp.repository 의 모든 메소드가 호출될 때 기본 로그를 남기는 Aspect
// repository 패키지의 메소드는 모두 login 한 상태에서만 호출 가능
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    // Session
    private final HttpSession session;

    @Pointcut(value="execution( * com.mycom.myapp.repository.*.*(..) )") // excution()괄호 안쪽은 대상.
    private void logPointcut() {

    }

    // 로그인한 사용자가 호출할 경우, 호출한 사용자의 이름과 시각을 출력
    @Before("logPointcut()")
    public void logRepositoryMethodCall(JoinPoint joinPoint) { // joinpoint는 위 pointcut의 대상 중 실제 호출된 메소드의 진입점.
        String userName = (String) session.getAttribute("username");
        if(userName == null) return;

        String methodName = joinPoint.getSignature().getName(); // 해당 메소드명 가져옴.
        log.info("User [" + userName + "] 가 method : " + methodName + " 을 " + LocalDateTime.now() + " 에 호출했습니다.");
    }
}
