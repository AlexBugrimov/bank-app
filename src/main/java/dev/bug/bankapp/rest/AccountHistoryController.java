package dev.bug.bankapp.rest;

import dev.bug.bankapp.dto.AccountHistoryDto;
import dev.bug.bankapp.model.AccountHistory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("account-histories")
public class AccountHistoryController extends ApiController {

    @GetMapping
    @Transactional
    public ResponseEntity<List<AccountHistoryDto>> getAccountHistoryReport(
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate to) {
        List<AccountHistory> accountHistories = accountHistoryRepository
                .findAllByTimeBetween(from.atTime(LocalTime.MIN), to.atTime(LocalTime.MAX));
        if (!accountHistories.isEmpty()) {
            List<AccountHistoryDto> accountHistoryDtoList = mapper.accountHistoriesTo(accountHistories);
            return ResponseEntity.ok(accountHistoryDtoList);
        }
        return ResponseEntity.notFound().build();
    }
}
