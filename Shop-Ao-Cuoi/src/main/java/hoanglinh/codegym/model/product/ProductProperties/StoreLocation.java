package hoanglinh.codegym.model.product.ProductProperties;

import hoanglinh.codegym.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "stores")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String apartmentNumber;//so nha

    private String ward; // phuong

    private String district; //quan

    private String province;//tinh

    @OneToMany(targetEntity = Product.class)
    private List<Product> products;

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
