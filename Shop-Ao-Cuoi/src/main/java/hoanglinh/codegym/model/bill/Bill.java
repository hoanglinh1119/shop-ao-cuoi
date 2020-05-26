package hoanglinh.codegym.model.bill;

import hoanglinh.codegym.model.user.Account;

import javax.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @OneToMany
//    private Account account;



}
