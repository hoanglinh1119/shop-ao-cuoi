package hoanglinh.codegym.service.User;

import hoanglinh.codegym.model.user.Account;


import java.util.HashMap;
import java.util.List;

public interface AccountService {
    Account getAccountByUserName(String username);

    List<Account> findAll();
    Account getAccountByMail(String email);
    Account getAccountByNumber(String number);

    boolean checkMail(Account account);
    boolean checkNumber(Account account);
    boolean checkUserName(Account account);

    HashMap<String,String> getUserMap();

    Account findById(Long id);

    void save(Account account);

    void remove(Long id);
}
