package dev.bug.bankapp.rest;

import dev.bug.bankapp.repositories.AccountRepository;
import dev.bug.bankapp.repositories.BankRepository;
import dev.bug.bankapp.repositories.ClientRepository;
import dev.bug.bankapp.utils.ErrorMessageProvider;
import dev.bug.bankapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public abstract class ApiController {

    @Autowired
    protected BankRepository bankRepository;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected ErrorMessageProvider messageProvider;

    @Autowired
    protected Mapper mapper;
}
