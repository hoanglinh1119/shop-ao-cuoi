package hoanglinh.codegym.model.product;


import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;
import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import hoanglinh.codegym.model.product.ProductProperties.TypeProduct;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String name;
    private double price;
    private String forGender;
    private String description;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private ProductMaterial productMaterial;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ProductColor productColor;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private StoreLocation storeLocation;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeProduct types;
    public Product(){

    }

    public Product(Long id, String image, String name, double price, String forGender,
                   String description, int amount, ProductMaterial productMaterial,
                   ProductColor productColor, StoreLocation storeLocation, TypeProduct types) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.forGender = forGender;
        this.description = description;
        this.amount = amount;
        this.productMaterial = productMaterial;
        this.productColor = productColor;
        this.storeLocation = storeLocation;
        this.types = types;
    }

    public Product(String image, String name, double price, String forGender, String description,
                   int amount, ProductMaterial productMaterial, ProductColor productColor,
                   StoreLocation storeLocation, TypeProduct types) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.forGender = forGender;
        this.description = description;
        this.amount = amount;
        this.productMaterial = productMaterial;
        this.productColor = productColor;
        this.storeLocation = storeLocation;
        this.types = types;
    }



    public Product(String name, double price, String forGender, String description, int amount, ProductMaterial productMaterial, ProductColor productColor, StoreLocation storeLocation, TypeProduct types) {
        this.name = name;
        this.price = price;
        this.forGender = forGender;
        this.description = description;
        this.amount = amount;
        this.productMaterial = productMaterial;
        this.productColor = productColor;
        this.storeLocation = storeLocation;
        this.types = types;
    }

    public Product(Long id, String name, double price, String forGender, String description, int amount, ProductMaterial productMaterial, ProductColor productColor, StoreLocation storeLocation, TypeProduct types) {
    this.id=id;
    this.name = name;
    this.price = price;
    this.forGender = forGender;
    this.description = description;
    this.amount = amount;
    this.productMaterial = productMaterial;
    this.productColor = productColor;
    this.storeLocation = storeLocation;
    this.types = types;
    }
    public String getForGender() {
        return forGender;
    }

    public void setForGender(String forGender) {
        this.forGender = forGender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductMaterial getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(ProductMaterial productMaterial) {
        this.productMaterial = productMaterial;
    }

    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public TypeProduct getTypes() {
        return types;
    }

    public void setTypes(TypeProduct types) {
        this.types = types;
    }
}
