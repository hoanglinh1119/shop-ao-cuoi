package hoanglinh.codegym.controllers.admin;


import hoanglinh.codegym.model.product.Product;
import hoanglinh.codegym.model.product.ProductForm;
import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;
import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import hoanglinh.codegym.model.product.ProductProperties.TypeProduct;
import hoanglinh.codegym.service.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@PropertySource("classpath:global-config-app.properties")
public class ProductController {
      @Autowired
   private Environment env;
      @Autowired
   private IProductService iProductService;
      @Autowired
   private IProductColorService iProductColorService;
      @Autowired
    private IProductMaterialService iProductMaterialService;
    @Autowired
    private IStoreLocationService iStoreLocationService;
    @Autowired
    private ITypeProductService iTypeProductService;

    @ModelAttribute("types")
    public Iterable<TypeProduct> typeProducts(){return iTypeProductService.findAll();}
    @ModelAttribute("stores")
    public Iterable<StoreLocation> storeLocations(){return iStoreLocationService.findAll();}
   @ModelAttribute("materials")
      public Iterable<ProductMaterial> materials(){return iProductMaterialService.findAll();}
    @ModelAttribute("colors")
    public Iterable<ProductColor> colors(){return iProductColorService.findAll();}




 @GetMapping("/admin/create-product")
 public ModelAndView createProduct() {
     ModelAndView modelAndView = new ModelAndView("admin-product-create");
     modelAndView.addObject("productForm", new ProductForm());
     return modelAndView;
 }
 @PostMapping("/admin/create-product")
 public ModelAndView saveProduct(@ModelAttribute("productForm") ProductForm productForm, BindingResult result) {
     // thong bao neu xay ra loi
     if (result.hasErrors()) {
         System.out.println("Result Error Occured" + result.getAllErrors());
     }
     // lay ten file
     MultipartFile multipartFile = productForm.getImage();
     String fileName = multipartFile.getOriginalFilename();
     String fileUpload = env.getProperty("file_upload").toString();

     // luu file len server
     try {
         FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
     } catch (IOException ex) {
         ex.printStackTrace();
     }
     // tao doi tuong de luu vao db
    Product product=new Product(fileName,productForm.getName(),productForm.getPrice(),
            productForm.getShortDescription(), productForm.getLongDescription(),productForm.getAmount(),
            productForm.getProductMaterial(),productForm.getProductColor(),productForm.getStoreLocation(),
            productForm.getTypes());
     // luu vao db
     //productService.save(productObject);
     iProductService.save(product);
     ModelAndView modelAndView = new ModelAndView("admin-product-create");
     modelAndView.addObject("product", new Product());
     modelAndView.addObject("message", "New customer created successfully");
     return modelAndView;
 }
    @GetMapping("/admin/edit-product/{id}")
    public String showFormEditProduct(@PathVariable("id") Long id, Model model) {
        Product product = iProductService.findOne(id);
        ProductForm productForm=new ProductForm();

        productForm.setId(product.getId());
        productForm.setAmount(product.getAmount());
        productForm.setName(product.getName());
        productForm.setPrice(product.getPrice());
        productForm.setProductColor(product.getProductColor());
        productForm.setStoreLocation(product.getStoreLocation());
        productForm.setProductMaterial(product.getProductMaterial());
        productForm.setTypes(product.getTypes());
        productForm.setShortDescription(product.getShortDescription());
        productForm.setLongDescription(product.getLongDescription());

        model.addAttribute("productForm",productForm);
        return "admin-product-edit";
    }

    @PostMapping("/admin/edit-product")
    public ModelAndView editProduct(@ModelAttribute("productForm") ProductForm productForm, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("admin-product-edit");
        // thong bao neu xay ra loi
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        System.out.println(productForm.getAmount()+productForm.getName());

        // lay ten file
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        // luu file len server
        if (fileName.equals("")){
            Product product=new Product(productForm.getId(),productForm.getName(),productForm.getPrice(),
                    productForm.getShortDescription(), productForm.getLongDescription(),productForm.getAmount(),
                    productForm.getProductMaterial(),productForm.getProductColor(),productForm.getStoreLocation(),
                    productForm.getTypes());
            iProductService.save(product);
            modelAndView.addObject("message", "edit success");
            modelAndView.addObject("product",product);
        }else {
            try {

                FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));

                Product product=new Product(productForm.getId(),fileName,productForm.getName(),productForm.getPrice(),
                        productForm.getShortDescription(), productForm.getLongDescription(),productForm.getAmount(),
                        productForm.getProductMaterial(),productForm.getProductColor(),productForm.getStoreLocation(),
                        productForm.getTypes());
                System.out.println(product);
                iProductService.save(product);
                modelAndView.addObject("message", "edit success");
                modelAndView.addObject("product",product);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // tao doi tuong de luu vao db
        // luu vao db
        return modelAndView;
    }

}
