package com.anz.account.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "transactionDetails")
@Entity
@Data
public class Transaction {
    @Id
    @Column
    private Integer transactionId;

    @Column
    private String currency;

    @Column
    private Double amount;

    @Column
    private String debitOrCredit;

    @Column
    private Date valueDate;

    @Column
    private String transactionNarrative;

    @ManyToOne
    private Account account;
}
