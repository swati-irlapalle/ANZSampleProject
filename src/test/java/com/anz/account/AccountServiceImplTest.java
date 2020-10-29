package com.anz.account;


import com.anz.account.entity.Account;
import com.anz.account.entity.Transaction;
import com.anz.account.exception.InvalidAccountException;
import com.anz.account.exception.NoDataFoundException;
import com.anz.account.repository.AccountDetailsRepository;
import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;
import com.anz.account.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;

@SpringBootTest
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountDetailsRepository repository;

    @Test
    public void getAccountDetails_InvalidAccountNumber() {
        Mockito.when(repository.findById(null)).thenThrow(new InvalidAccountException("Invalid Account Number"));
        Throwable throwable = catchThrowable(
                () -> accountService.getAccountDetails(null));
        assertThat(throwable).isInstanceOf(InvalidAccountException.class);
    }

    @Test
    public void getAccountDetails_NoAccountDetailsFound() {
        Mockito.when(repository.findById(1234)).thenThrow(new NoDataFoundException("Account Details Not Available"));
        Throwable throwable = catchThrowable(
                () -> accountService.getAccountDetails(1234));
        assertThat(throwable).isInstanceOf(NoDataFoundException.class);
    }

    @Test
    public void getAccountTransaction_NoAccountDetailsFound() {

        Mockito.when(repository.findById(98767878)).thenReturn(Optional.of(new Account()));
        Throwable throwable = catchThrowable(
                () -> accountService.getAccountTransaction(98767878));
        assertThat(throwable).isInstanceOf(NoDataFoundException.class);
    }

    @Test
    public void getAccountTransactionWithInvalidAccount_Number() {
        Mockito.when(repository.findById(null)).thenThrow(new NoDataFoundException("Invalid Account Number"));
        Throwable throwable = catchThrowable(
                () -> accountService.getAccountTransaction(null));
        assertThat(throwable).isInstanceOf(InvalidAccountException.class);
    }

    @Test
    public void getAccountDetails_withAllFields() {
        Account account = new Account();
        account.setAccountName("James");
        account.setAccountNumber(585309209);
        account.setAccountType("Saving");
        account.setBalanceDate(new Date());
        account.setCurrency("INR");
        Mockito.when(repository.findById(585309209)).thenReturn(Optional.of(account));

        AccountResponse response = accountService.getAccountDetails(585309209);
        Assertions.assertEquals(585309209, response.getAccountNumber());
        Assertions.assertEquals("James", response.getAccountName());
        Assertions.assertEquals("Saving", response.getAccountType());
        Assertions.assertEquals("INR", response.getCurrency());
        Assertions.assertEquals(null, response.getTransaction());
    }

    @Test
    public void getTransactionDetails_withAllFields() {
        Account account = new Account();
        account.setAccountName("James");
        account.setAccountNumber(585309209);
        account.setAccountType("Saving");
        account.setBalanceDate(new Date());
        Transaction transaction = new Transaction();
        transaction.setTransactionId(12345678);
        transaction.setDebitOrCredit("credit");
        transaction.setAccount(account);
        transaction.setCurrency("INR");
        Set set = new HashSet<Transaction>();
        set.add(transaction);
        account.setTransaction(set);
        Mockito.when(repository.findById(585309209)).thenReturn(Optional.of(account));
        Set<TransactionResponse> response = accountService.getAccountTransaction(585309209);
        for (TransactionResponse rs : response) {
            Assertions.assertEquals("INR", rs.getCurrency());
            Assertions.assertEquals(12345678, rs.getTransactionId());
            Assertions.assertEquals("credit", rs.getDebitOrCredit());

        }
    }


}