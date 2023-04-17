package com.beditsch.ing.transactions;

import com.beditsch.ing.transactions.model.AccountSummary;
import com.beditsch.ing.transactions.model.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("transactions")
public class TransactionsController {
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "report"
    )
    public Collection<AccountSummary> getTransactionsReport(@NotNull @RequestBody List<Transaction> request) {
        Map<String, AccountSummary> accountSummaryMap = new TreeMap<>();
        for (Transaction t: request) {
            String creditAccount = t.getCreditAccount();
            String debitAccount = t.getDebitAccount();

            if (accountSummaryMap.containsKey(creditAccount)) {
                accountSummaryMap.get(creditAccount).creditAccount(t.getAmount());
            } else {
                accountSummaryMap.put(
                        creditAccount,
                        new AccountSummary(creditAccount, 0, 1, t.getAmount())
                );
            }

            if (accountSummaryMap.containsKey(debitAccount)) {
                accountSummaryMap.get(debitAccount).debitAccount(t.getAmount());
            } else {
                accountSummaryMap.put(
                        debitAccount,
                        new AccountSummary(debitAccount, 1, 0, t.getAmount()*(-1))
                );
            }
        }
        return accountSummaryMap.values();
    }
}
