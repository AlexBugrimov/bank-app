package dev.bug.bankapp.services;

import dev.bug.bankapp.utils.ErrorMessageProvider;
import dev.bug.bankapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ApiService {

    @Autowired
    protected ErrorMessageProvider messageProvider;

    @Autowired
    protected Mapper mapper;
}
