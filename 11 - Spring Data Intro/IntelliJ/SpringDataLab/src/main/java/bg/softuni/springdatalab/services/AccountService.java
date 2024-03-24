package bg.softuni.springdatalab.services;

import bg.softuni.springdatalab.models.entities.Account;

import java.math.BigDecimal;

public interface AccountService {
    void createAccount(Account account);
    void withdrawMoney(BigDecimal amount,long id);
    void transferMoney(BigDecimal amount,long id);
}
