package hoanglinh.codegym.model.user;

import hoanglinh.codegym.model.bill.BillDetail;

import javax.persistence.*;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String email;
    private String numberPhone;
//    @OneToOne
//    private BillDetail billDetail;
    @OneToOne
    private Account account;

//    public BillDetail getBillDetail() {
//        return billDetail;
//    }
//
//    public void setBillDetail(BillDetail billDetail) {
//        this.billDetail = billDetail;
//    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Account getUser() {
        return account;
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
