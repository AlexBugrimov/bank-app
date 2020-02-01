package dev.bug.bankapp.rest;

import dev.bug.bankapp.exceptions.ErrorMessageProvider;
import dev.bug.bankapp.repositories.BankRepository;
import dev.bug.bankapp.repositories.ClientRepository;
import dev.bug.bankapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ApiController {

    @Autowired
    protected BankRepository bankRepository;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected ErrorMessageProvider messageProvider;

    @Autowired
    protected Mapper mapper;
}
