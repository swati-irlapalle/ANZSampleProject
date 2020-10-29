package com.anz.account.service;

import com.anz.account.entity.Account;
import com.anz.account.entity.Transaction;
import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;

import java.util.List;
import java.util.Set;

public interface AccountService {

     AccountResponse getAccountDetails(Integer id);
     Set<TransactionResponse> getAccountTransaction(Integer id);
}
