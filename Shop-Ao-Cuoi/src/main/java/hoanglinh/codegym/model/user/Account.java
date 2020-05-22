package hoanglinh.codegym.model.user;



import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.regex.Pattern;

@Entity
public class Account implements Validator, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "khong duoc de trong")
    @Size(min = 8,max = 30,message = "chuoi phai tu 8-30 ki tu")
    private String name;
    @NotEmpty(message = "khong duoc de trong")
    @Size(min = 8,max = 30,message = "chuoi phai tu 8-30 ki tu")
    @javax.validation.constraints.Pattern(regexp = "([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})",message ="vui long co chu hoa, chu thuong va so" )
    private String username;
    @NotEmpty(message = "khong duoc de trong")
    @Size(min = 8,max = 30,message = "chuoi phai tu 8-30 ki tu")
    @javax.validation.constraints.Pattern(regexp = "([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})",message ="vui long co chu hoa, chu thuong va so" )
    private String password;
    @NotEmpty(message = "khong duoc de trong")
    @Size(min = 8,max = 30,message = "chuoi phai tu 8-30 ki tu")
    @javax.validation.constraints.Pattern(regexp = "(^[A-Za-z0-9+_.-]+@(.+)$)",message ="nhap theo dinh dang abc@gmail..." )
    private String email;
    @NotEmpty(message = "khong duoc de trong")
    @Size(min = 20,max = 100,message = "nhap day du chi tiet")
    private String address;

    @NotEmpty(message = "khong duoc de trong")
    @javax.validation.constraints.Pattern(regexp = "(09|03|07|08|05)+([0-9]{8})",message ="nhap chinh xac so cua ban" )
    private  String numberPhone;
    @ManyToOne
    private Role role;

    public Account(Long id, String name, String username, String password, String email, String address, String numberPhone, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.numberPhone = numberPhone;
        this.role = role;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account(Long id, String name, String username, String password, String email, String address, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public Account() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account= (Account) target;
        String name= account.getName();
        String username=account.getUsername();
        String pass=account.getPassword();
        String number=account.getNumberPhone();
        String email=account.getEmail();
        String address=account.getAddress();

        ValidationUtils.rejectIfEmpty(errors,name,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,username,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,email,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,pass,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,number,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,address,"value.empty");

        Pattern patternNumber=Pattern.compile("(09|03|07|08|05)+([0-9]{8})");
        Pattern patternCharacter=Pattern.compile("([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})");
        Pattern patternEmail=Pattern.compile("(^[A-Za-z0-9+_.-]+@(.+)$)");

        if (name.length()<5||name.length()>30){
            errors.rejectValue("name","name.length");
        }
        if (username.length()<6||username.length()>15){
            errors.rejectValue("username","username.length");
        }
//        if (!patternCharacter.matcher(username).find()){
//            errors.rejectValue("username","username.regex");
//        }
        if (!patternEmail.matcher(email).find()){
            errors.rejectValue("email","email.regex");
        }
        if (!patternNumber.matcher(pass).find()){
            errors.rejectValue("numberPhone","numberPhone.regex");
        }
        if (!patternCharacter.matcher(pass).find()){
            errors.rejectValue("password","password.regex");
        }
        if (address.length()<6||address.length()>100){
            errors.rejectValue("address","address.length");
        }

    }
}
