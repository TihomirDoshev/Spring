package bg.softuni.springdatalab;

import bg.softuni.springdatalab.services.AccountService;
import bg.softuni.springdatalab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final AccountService accountService;

    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... args) throws Exception {
       this.accountService.transferMoney(BigDecimal.valueOf(500),1);

    }
}
