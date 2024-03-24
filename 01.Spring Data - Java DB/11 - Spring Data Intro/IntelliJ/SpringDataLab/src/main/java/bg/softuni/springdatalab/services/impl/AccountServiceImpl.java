package bg.softuni.springdatalab.services.impl;

import bg.softuni.springdatalab.models.entities.Account;
import bg.softuni.springdatalab.repositories.AccountRepository;
import bg.softuni.springdatalab.services.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(Account account) {
        this.accountRepository.saveAndFlush(account);
    }

    @Override
    public void withdrawMoney(BigDecimal amount, long id) {
        Optional<Account> optional = this.accountRepository.findById((int) id);
        if (optional.isPresent()){
            Account account = optional.get();

            if (account.getBalance().compareTo(amount)>=0) {
                account.setBalance(account.getBalance().subtract(amount));
                this.accountRepository.saveAndFlush(account);
            }
        }

    }

    @Override
    public void transferMoney(BigDecimal amount, long id) {
        Optional<Account> optional = this.accountRepository.findById((int) id);
        if (optional.isPresent()) {
            Account account = optional.get();

            if (amount.doubleValue() > 0) {
                account.setBalance(account.getBalance().add(amount));
                this.accountRepository.saveAndFlush(account);
            }
        }
    }
}
