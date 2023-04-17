package com.beditsch.ing.transactions.model;

public class Transaction {
    private final String debitAccount;
    private final String creditAccount;
    private final Double amount;

    public Transaction(String debitAccount, String creditAccount, Double amount) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
    }
    public String getDebitAccount() {
        return debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public Double getAmount() {
        return amount;
    }
}
