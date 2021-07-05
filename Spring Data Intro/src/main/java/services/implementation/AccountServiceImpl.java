package services.implementation;

import com.example.springdataintro.exceptions.EntityDoesNotExistException;
import com.example.springdataintro.exceptions.InvalidAccountOperationException;
import entities.Account;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import repositories.AccountRepository;
import services.AccountService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createUserAccount(User user, Account account) {
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepository.save(account);
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new EntityDoesNotExistException(String.format("Enter with ID:%s does not exist.",
                        accountId)));
        if(account.getBanalce().compareTo(amount) < 0) {
            throw new InvalidAccountOperationException(
                    String.format("Account ID:%s does not have enough money.", accountId));
        }
        account.setBanalce(account.getBanalce().subtract(amount));
    }

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new EntityDoesNotExistException(String.format("Enter with ID:%s does not exist.",
                        accountId)));
        account.setBanalce(account.getBanalce().add(amount));
    }

    @Override
    public void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        depositMoney(amount, toAccountId);
        withdrawMoney(amount, fromAccountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
