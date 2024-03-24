package bg.softuni.springdatalab.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private BigDecimal balance;
    @ManyToOne
    private User user;

    public Account() {
    }

    public Account(BigDecimal balance, User account) {
        this.balance = balance;
        this.user = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getAccount() {
        return user;
    }

    public void setAccount(User account) {
        this.user = account;
    }
}
