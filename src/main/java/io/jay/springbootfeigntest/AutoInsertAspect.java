package io.jay.springbootfeigntest;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AutoInsertAspect {

    private final ProductRepository repository;

    @Around("@annotation(AutoInsert)")
    public Object insert(ProceedingJoinPoint joinPoint) {

        try {
            Object proceed = joinPoint.proceed();
            Product result = (Product) proceed;

            repository.save(result);

            return proceed;

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
