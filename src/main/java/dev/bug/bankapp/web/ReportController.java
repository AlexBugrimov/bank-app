package dev.bug.bankapp.web;

import dev.bug.bankapp.dto.AccountHistoryDto;
import dev.bug.bankapp.services.ReportService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<AccountHistoryDto>> getAccountHistory(
            @RequestParam @ApiParam(value = "Дата от", required = true, example = "01.01.2020") LocalDateTime from,
            @RequestParam @ApiParam(value = "Дата до", required = true, example = "01.07.2020") LocalDateTime to) {
        List<AccountHistoryDto> report = reportService.getAccountHistory(from, to);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report);
    }
}
