package com.anz.account.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "TBL_ACCOUNT")
@Setter
@Getter
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance_date")
    private Date balanceDate;

    @Column(name = "currency")
    private String currency;

    @Column(name = "opening_available_balance")
    private Double openingAvailableBalance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Transaction> transaction;
}
