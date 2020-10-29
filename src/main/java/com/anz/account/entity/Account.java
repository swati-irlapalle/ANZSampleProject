package com.anz.account.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "accountDetails")
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer accountNumber;

    @Column
    private String accountName;

    @Column
    private String accountType;

    @Column
    private Date balanceDate;

    @Column
    private String currency;

    @Column
    private Double openingAvailableBalance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Set<Transaction> transaction;
}
