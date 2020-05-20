package hoanglinh.codegym.model.product.ProductProperties;


import hoanglinh.codegym.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "materials")
public class ProductMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String material;
     @OneToMany
    private List<Product> materials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<Product> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Product> materials) {
        this.materials = materials;
    }
}
