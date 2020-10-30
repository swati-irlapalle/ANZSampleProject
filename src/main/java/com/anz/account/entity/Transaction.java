package com.anz.account.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "TBL_TRANSACTION")
@Setter
@Getter
public class Transaction implements Serializable {
    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "debit_or_credit")
    private String debitOrCredit;

    @Column(name = "value_date")
    private Date valueDate;

    @Column(name = "transaction_narrative")
    private String transactionNarrative;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "account_number")
    private Account account;
}
