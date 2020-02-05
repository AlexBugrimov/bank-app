package dev.bug.bankapp.model;

import dev.bug.bankapp.dto.TransferReqDto;
import dev.bug.bankapp.services.ReportService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public enum AccountOperation {

    WITHDRAW {
        @Override
        public void proceed(ProceedingJoinPoint joinPoint, TransferReqDto transfer, ReportService reportService) {
            reportService.record(joinPoint, transfer, this);
        }
    }, DEPOSIT {
        @Override
        public void proceed(ProceedingJoinPoint joinPoint, TransferReqDto transfer, ReportService reportService) {
        }
    }, TRANSFER {
        @Override
        public void proceed(ProceedingJoinPoint joinPoint, TransferReqDto transfer, ReportService reportService) {
        }
    };

    public abstract void proceed(ProceedingJoinPoint joinPoint, TransferReqDto transfer, ReportService reportService);
}
