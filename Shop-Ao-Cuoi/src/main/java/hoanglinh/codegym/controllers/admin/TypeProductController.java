package hoanglinh.codegym.controllers.admin;

import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import hoanglinh.codegym.model.product.ProductProperties.TypeProduct;
import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.model.user.Role;
import hoanglinh.codegym.service.User.AccountService;
import hoanglinh.codegym.service.product.IStoreLocationService;
import hoanglinh.codegym.service.product.ITypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class TypeProductController {
    @Autowired
    private ITypeProductService typeProductService;
    @Autowired
    private IStoreLocationService iStoreLocationService;
    @Autowired
    private AccountService accountService;

    public String getPrincipal() {
        String role=null ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            role = ((UserDetails) principal).getUsername();
        }else {
            role=principal.toString();
        }
        return role;
    }
    public String getAccount_role(){
        Account account=accountService.getAccountByUserName(getPrincipal());
        Role role=account.getRole();
        return role.getName();
    }
    @GetMapping("/admin/types")
    public ModelAndView listTypeProduct(){
        Iterable<TypeProduct> types = typeProductService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-type-list");
        modelAndView.addObject("types", types);
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/create-type")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admin-type-create");
        modelAndView.addObject("types", new TypeProduct());
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @PostMapping("/admin/create-type")
    public ModelAndView saveType(@Validated @ModelAttribute("types") TypeProduct typeProduct, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){

            ModelAndView modelAndView = new ModelAndView("admin-type-create");

            return modelAndView;
        }
        typeProductService.save(typeProduct);
        ModelAndView modelAndView = new ModelAndView("admin-type-create");
        modelAndView.addObject("types", new TypeProduct());
        modelAndView.addObject("message", "New type product created successfully");

        return modelAndView;
    }

    @GetMapping("/admin/edit-type/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        TypeProduct type = typeProductService.findById(id);
        if(type != null) {
            ModelAndView modelAndView = new ModelAndView("admin-type-edit");
            modelAndView.addObject("types", type);
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;
        }
    }

    @PostMapping("/admin/edit-type")
    public ModelAndView updateType(@Validated @ModelAttribute("types") TypeProduct typeProduct,BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin-type-edit");

            return modelAndView;
        }
        typeProductService.save(typeProduct);
        ModelAndView modelAndView = new ModelAndView("admin-type-edit");
        modelAndView.addObject("types", typeProduct);
        modelAndView.addObject("message", "type product updated successfully");
        return modelAndView;
    }

    @GetMapping("/admin/delete-type/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        TypeProduct typeProduct = typeProductService.findById(id);
        if(typeProduct != null) {
            ModelAndView modelAndView = new ModelAndView("admin-type-delete");
            modelAndView.addObject("type", typeProduct);
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-type")
    public ModelAndView deleteType(@ModelAttribute("type") TypeProduct typeProduct){
        typeProductService.remove(typeProduct.getId());
        Iterable<TypeProduct> types = typeProductService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-type-list");
        modelAndView.addObject("types", types);
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }
}
