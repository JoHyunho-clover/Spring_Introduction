package introduction.springintroduction.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP는 이것을 적어주어야 AOP로 적용할 수 있다.

@Component//AOP도 스프링 빈으로 등록해야한다. ->SpringConfig  또는 @ComponentScan나 @Component 써도 된다.
public class TimeTraceAop {//공통 관심 사항 - 시간 측정

    @Around("execution(* introduction.springintroduction..*(..))")//이 공통 관심사항을 어디에 적용할 것인가.. execution사용 introduction.springintroduction하위의 모든 패키지들에게 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs +
                    "ms");
        }
    }
}
