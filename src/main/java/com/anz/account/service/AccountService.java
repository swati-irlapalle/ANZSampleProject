package com.anz.account.service;

import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;

import java.util.Set;

public interface AccountService {

     AccountResponse getAccountDetails(Integer id);
     Set<TransactionResponse> getAccountTransaction(Integer id);
}
