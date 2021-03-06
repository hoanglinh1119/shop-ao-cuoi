package hoanglinh.codegym.model.product;

import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;
import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import hoanglinh.codegym.model.product.ProductProperties.TypeProduct;
import org.springframework.web.multipart.MultipartFile;


public class ProductForm {
    private Long id;
    private MultipartFile image;
    private String name;
    private double price;
    private String forGender;
    private String description;
    private int amount;

//    @ManyToOne
//    @JoinColumn(name = "material_id")
    private ProductMaterial productMaterial;
//
//    @ManyToOne
//    @JoinColumn(name = "color_id")
    private ProductColor productColor;
//    @ManyToOne
//    @JoinColumn(name = "location_id")
    private StoreLocation storeLocation;
//
//    @ManyToOne
//    @JoinColumn(name = "type_id")
    private TypeProduct types;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ProductForm(){

    }

    public ProductForm(Long id, MultipartFile image, String name, double price, String forGender,
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

    public ProductForm(MultipartFile image, String name, double price, String forGender,
                       String description, int amount, ProductMaterial productMaterial,
                       ProductColor productColor, StoreLocation storeLocation, TypeProduct types) {
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
    public String getForGender() {
        return forGender;
    }

    public void setForGender(String forGender) {
        this.forGender = forGender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
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
