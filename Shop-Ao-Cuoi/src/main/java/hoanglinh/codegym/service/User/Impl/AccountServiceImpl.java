package hoanglinh.codegym.service.User.Impl;

import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.repositories.user.AccountRepository;
import hoanglinh.codegym.service.User.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService, UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account getAccountByUserName(String username) {
        return accountRepository.findByUsername(username) ;
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public void save(Account account) {
     accountRepository.save(account);
    }

    @Override
    public void remove(Long id) {
     accountRepository.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account=this.getAccountByUserName(username);
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(account.getRoles());

        UserDetails userDetails=new User(
                account.getUsername(),
                account.getPassword(),
                authorities
        );
        return userDetails;
    }
}
