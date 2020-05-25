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
import java.util.HashMap;
import java.util.List;

public class AccountServiceImpl implements AccountService, UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account getAccountByUserName(String username) {
        return accountRepository.findByUsername(username) ;
    }

    @Override
    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Account getAccountByMail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account getAccountByNumber(String number) {
        return accountRepository.findByNumberPhone(number);
    }

    @Override
    public boolean checkMail(Account account) {
        boolean check=false;
       List<Account> list=findAll();
       for (Account account1: list){
           if (account1.getEmail().equals(account.getEmail())) {
               check = true;
               break;
           }
       }
       return check;
    }

    @Override
    public boolean checkNumber(Account account) {
        boolean check=false;
        List<Account> list=findAll();
        for (Account account1: list){
            if (account1.getNumberPhone().equals(account.getNumberPhone())) {
                check = true;
                break;
            }
        }
        return check;

    }

//    @Override
//    public boolean checkPass(Account account) {
//        HashMap<String,String> userMap=getUserMap();
//          if (userMap!=null){
//              if (userMap.containsKey(account.getUsername())
//                      &&userMap.get(account.getUsername()).equals(account.getPassword())){
//                  return true;
//              }else {
//                  return false;
//              }
//          }else return false;
//    }

    @Override
    public boolean checkUserName(Account account) {
        HashMap<String,String> userMap=getUserMap();
        if (userMap!=null){
            if (userMap.containsKey(account.getUsername())){
                return true;
            }else {
                return false;}
        }else {
            return false;
        }
    }

//    @Override
//    public List<String> getMail() {
//        List<String> listMail=new ArrayList<>();
//        List<Account> accountList=findAll();
//        if (accountList!=null){
//            for (Account account:accountList){
//                listMail.add(account.getEmail());
//            }
//        }
//        return listMail;
//    }
//
//    @Override
//    public List<String> getNumber() {
//        List<String> listNumber=new ArrayList<>();
//        List<Account> accountList=findAll();
//        if (accountList!=null){
//            for (Account account:accountList){
//                listNumber.add(account.getNumberPhone());
//            }
//        }
//        return listNumber;
//    }

    @Override
    public HashMap<String, String> getUserMap() {
        HashMap<String,String> userMap=new HashMap<>();
        List<Account> accountList=findAll();
        if (accountList!=null){
           for (Account account:accountList){
               userMap.put(account.getUsername(),account.getPassword());
           }
        }
        return userMap;
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
        authorities.add(account.getRole());

        UserDetails userDetails=new User(
                account.getUsername(),
                account.getPassword(),
                authorities
        );
        return userDetails;
    }
}
