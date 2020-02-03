package dev.bug.bankapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("account-histories")
public class AccountHistoryController extends ApiController {

    @GetMapping
    public void getAccountHistoryReport(@RequestParam LocalDate from, @RequestParam LocalDate to) {

    }
}
