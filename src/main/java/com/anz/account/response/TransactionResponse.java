package com.anz.account.response;

import lombok.Data;

import java.util.Date;


@Data
public class TransactionResponse {

    private Integer transactionId;
    private Integer accountNumber;
    private String accountName;
    private Date valueDate;
    private String currency;
    private Double amount;
    private String debitOrCredit;
    private String transactionNarrative;
}
