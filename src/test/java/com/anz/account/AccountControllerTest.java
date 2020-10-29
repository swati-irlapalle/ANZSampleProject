package com.anz.account;

import com.anz.account.controller.AccountController;
import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;
import com.anz.account.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
public class AccountControllerTest {

    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountServiceImpl service;


    @Test
    public void getAccountDetails() {
        AccountResponse expected = Mockito.mock(AccountResponse.class);
        Mockito.when(service.getAccountDetails(585309209)).thenReturn(expected);
        ResponseEntity<AccountResponse> actual = controller.getAccountDetails(585309209);
        assertThat(actual.getBody()).isEqualTo(expected);
    }

    @Test
    public void getAccountTransaction() {
        Set<TransactionResponse> expected = new HashSet<TransactionResponse>();
        Mockito.when(service.getAccountTransaction(585309209)).thenReturn(expected);
        ResponseEntity<Set<TransactionResponse>> actual = controller.getAccountTransaction(585309209);
        assertThat(actual.getBody()).isEqualTo(expected);
    }
}

