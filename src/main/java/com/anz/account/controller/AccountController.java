package com.anz.account.controller;


import com.anz.account.exception.InvalidAccountException;
import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;
import com.anz.account.service.impl.AccountServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl service;


    @GetMapping(value = "/view/account/{id}")
    public ResponseEntity<AccountResponse> getAccountDetails(@NotNull @PathVariable("id") Long id) {

        return ResponseEntity.ok().body(service.getAccountDetails(id));
    }


    @GetMapping(value = "/view/transaction/{id}")
    public ResponseEntity<Set<TransactionResponse>> getAccountTransaction(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.getAccountTransaction(id));

    }
}
