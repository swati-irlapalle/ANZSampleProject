package com.anz.account.response;


import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class AccountResponse {
    private Integer accountNumber;
    private String accountName;
    private String accountType;
    private Date balanceDate;
    private String currency;
    private Double openingAvailableBalance;
    private Set<TransactionResponse> transaction;
}
