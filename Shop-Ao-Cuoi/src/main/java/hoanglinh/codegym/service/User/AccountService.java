package hoanglinh.codegym.service.User;

import hoanglinh.codegym.model.user.Account;

public interface AccountService {
    Account getAccountByUserName(String username);

    Iterable<Account> findAll();

    Account findById(Long id);

    void save(Account account);

    void remove(Long id);
}
