//package hoanglinh.codegym.controllers.admin;
//
//import hoanglinh.codegym.model.product.Product;
//import hoanglinh.codegym.service.product.IProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class ProductControllerAjax {
//    @Autowired
//    IProductService iProductService;
//    @RequestMapping("/search/{key}")
//    @ResponseBody()
//    public String[] doSearch(@PathVariable("key") String key) {
//        List<Product> products =  this.iProductService.allProduct(key,100,0).getContent();
//        System.out.println();
//        List<String> nameProduct=new ArrayList<>();
//        for (Product p:products) {
//            nameProduct.add(p.getName());
//        }
//        String[] name= new String[nameProduct.size()];
//        name=  nameProduct.toArray(name);
//        return name;
//    }
//}
