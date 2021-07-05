package services;

import entities.Account;
import entities.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface AccountService {
    Account createUserAccount(User user, Account account);
    void withdrawMoney(BigDecimal amount, Long accountId);
    void depositMoney(BigDecimal amount, Long accountId);
    void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId);

    List<Account> getAllAccounts();
}
