package hoanglinh.codegym.model.user;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Account {
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

    public Account(Long id, @NotEmpty(message = "khong duoc de trong")
    @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu") String name,
                   @NotEmpty(message = "khong duoc de trong") @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu")
                   @Pattern(regexp = "([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})", message = "vui long co chu hoa, chu thuong va so")
                           String username, @NotEmpty(message = "khong duoc de trong")
                   @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu")
                   @Pattern(regexp = "([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})", message = "vui long co chu hoa, chu thuong va so")
                           String password, @NotEmpty(message = "khong duoc de trong")
                   @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu")
                   @Pattern(regexp = "(^[A-Za-z0-9+_.-]+@(.+)$)", message = "nhap theo dinh dang abc@gmail...") String email,
                   @NotEmpty(message = "khong duoc de trong") @Size(min = 20, max = 100, message = "nhap day du chi tiet")
                           String address, @NotEmpty(message = "khong duoc de trong")
    @Pattern(regexp = "(09|03|07|08|05)+([0-9]{8})", message = "nhap chinh xac so cua ban") String numberPhone, Role role) {
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

    public Account(Long id, @NotEmpty(message = "khong duoc de trong") @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu") String name,
                   @NotEmpty(message = "khong duoc de trong")
                   @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu")
                   @Pattern(regexp = "([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})", message = "vui long co chu hoa, chu thuong va so")
                           String username, @NotEmpty(message = "khong duoc de trong")
                   @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu")
                   @Pattern(regexp = "([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})", message = "vui long co chu hoa, chu thuong va so")
                           String password, @NotEmpty(message = "khong duoc de trong")
                   @Size(min = 8, max = 30, message = "chuoi phai tu 8-30 ki tu")
                   @Pattern(regexp = "(^[A-Za-z0-9+_.-]+@(.+)$)", message = "nhap theo dinh dang abc@gmail...") String email,
                   @NotEmpty(message = "khong duoc de trong") @Size(min = 20, max = 100, message = "nhap day du chi tiet") String address,
                   @NotEmpty(message = "khong duoc de trong") @Pattern(regexp = "(09|03|07|08|05)+([0-9]{8})",
                           message = "nhap chinh xac so cua ban") String numberPhone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.numberPhone = numberPhone;
    }

    public Account() {
    }
}
