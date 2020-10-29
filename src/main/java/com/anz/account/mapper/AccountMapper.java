package com.anz.account.mapper;

import com.anz.account.entity.Account;
import com.anz.account.entity.Transaction;
import com.anz.account.response.AccountResponse;
import com.anz.account.response.TransactionResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper(componentModel = "springs")
public interface AccountMapper {

    AccountMapper INSTANCE=getMapper(AccountMapper.class);
    AccountResponse mapAccountToAccountResponse(Account account);
    TransactionResponse mapAccountToTransactionResponse(Transaction transaction);


}
