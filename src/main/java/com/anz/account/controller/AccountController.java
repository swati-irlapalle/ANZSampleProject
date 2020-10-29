package com.anz.account.controller;


import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;
import com.anz.account.service.impl.AccountServiceImpl;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl service;

    @GetMapping(value = "/view/account/{id}")
    public ResponseEntity<AccountResponse> getAccountDetails(@NotNull @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(service.getAccountDetails(id));
    }

    @GetMapping(value = "/view/transaction/{id}")
    public ResponseEntity<Set<TransactionResponse>> getAccountTransaction(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(service.getAccountTransaction(id));

    }
}
