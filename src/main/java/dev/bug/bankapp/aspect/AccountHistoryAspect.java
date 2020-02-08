package dev.bug.bankapp.aspect;

import dev.bug.bankapp.dto.TransferRequest;
import dev.bug.bankapp.model.AccountHistory;
import dev.bug.bankapp.model.AccountOperation;
import dev.bug.bankapp.services.ReportService;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Transactional
public class AccountHistoryAspect {

    @Autowired
    private ReportService reportService;


    @SneakyThrows
    @Around("execution(* dev.bug.bankapp.web.AccountController.*(dev.bug.bankapp.dto.TransferRequest)) && args(data)")
    public Object logHistory(ProceedingJoinPoint joinPoint, Object data) {
        AccountOperation operation = AccountOperation.valueOf(joinPoint.getSignature().getName().toUpperCase());
        TransferRequest transfer = (TransferRequest) data;
        AccountHistory history = new AccountHistory(operation);

        reportService.executeBefore(transfer, history);

        Object proceed = joinPoint.proceed();

        reportService.executeAfter(transfer, history);

        return proceed;
    }
}

