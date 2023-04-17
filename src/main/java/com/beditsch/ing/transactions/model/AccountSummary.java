package com.beditsch.ing.transactions.model;

import java.math.BigDecimal;

public class AccountSummary {
    private final String account;
    private int debitCount;
    private int creditCount;
    private final BigDecimal balance;

    public AccountSummary(String account, int debitCount, int creditCount, Double balance) {
        this.account = account;
        this.debitCount = debitCount;
        this.creditCount = creditCount;
        this.balance = new BigDecimal(balance);
    }

    public String getAccount() {
        return account;
    }

    public int getDebitCount() {
        return debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public Double getBalance() {
        return balance.doubleValue();
    }

    public void debitAccount(double amount) {
        this.debitCount += 1;
        this.balance.subtract(new BigDecimal(amount));
    }

    public void creditAccount(double amount) {
        this.creditCount += 1;
        this.balance.add(new BigDecimal(amount));
    }
}
