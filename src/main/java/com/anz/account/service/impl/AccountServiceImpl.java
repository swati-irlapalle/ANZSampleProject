package com.anz.account.service.impl;

import com.anz.account.WebClient;
import com.anz.account.entity.Account;
import com.anz.account.entity.Transaction;
import com.anz.account.exception.InvalidAccountException;
import com.anz.account.exception.NoDataFoundException;
import com.anz.account.mapper.AccountMapper;
import com.anz.account.repository.AccountDetailsRepository;
import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;
import com.anz.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDetailsRepository repository;

    @Autowired
    private WebClient client;

    public AccountResponse getAccountDetails(Long id) throws NoDataFoundException {
        log.trace("AccountServiceImpl.getAccountDetails-entry");
        client.getCall();
        validateAccountId(id);
        Optional<Account> isData = repository.findById(id);
        if (isData.isPresent()) {
            return AccountMapper.INSTANCE.mapAccountToAccountResponse(isData.get());
        } else {
            log.error("Account Details Not Available");
            throw new NoDataFoundException("Account Details Not Available");
        }
    }


    public Set<TransactionResponse> getAccountTransaction(Long id) {
        log.trace("AccountServiceImpl.getAccountTransaction-entry");
        validateAccountId(id);
        Optional<Account> isData = repository.findById(id);
        if (isData.isPresent()) {
            Set<TransactionResponse> response = new HashSet<>();

            if (isData.get() != null && isData.get().getTransaction() == null) {
                log.error("Transaction Details Not Available");
                throw new NoDataFoundException("Transaction Details Not Available");
            } else {
                Set<Transaction> transactions = isData.get().getTransaction();
                for (Transaction transaction : transactions) {
                    response.add(AccountMapper.INSTANCE.mapAccountToTransactionResponse(transaction));
                }
                return response;
            }
        } else {
            log.error("Account Details Not Available");
            throw new NoDataFoundException("Account Details Not Available");
        }
    }

    private void validateAccountId(Long id) {
        if (id == null) {
            log.error("Invalid Account Number");
            throw new InvalidAccountException("Invalid Account Number");
        }
    }
}
