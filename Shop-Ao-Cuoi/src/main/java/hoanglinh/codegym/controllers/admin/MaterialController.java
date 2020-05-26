package hoanglinh.codegym.controllers.admin;

import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;
import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.model.user.Role;
import hoanglinh.codegym.service.User.AccountService;
import hoanglinh.codegym.service.product.IProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MaterialController {
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

    @Autowired
    private IProductMaterialService iProductMaterialService;

    @GetMapping("/admin/materials")
    public ModelAndView listMaterial(){
        Iterable<ProductMaterial> materials = iProductMaterialService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-material-list");
        modelAndView.addObject("materials", materials);
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/create-material")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admin-material-create");
        modelAndView.addObject("materials", new ProductMaterial());
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @PostMapping("/admin/create-material")
    public ModelAndView saveMaterial(@ModelAttribute("materials") ProductMaterial productMaterial){
        iProductMaterialService.save(productMaterial);

        ModelAndView modelAndView = new ModelAndView("admin-material-create");
        modelAndView.addObject("materials", new ProductMaterial());
        modelAndView.addObject("message", "New material created successfully");
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/edit-material/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ProductMaterial material = iProductMaterialService.findById(id);
        if(material != null) {
            ModelAndView modelAndView = new ModelAndView("admin-material-edit");
            modelAndView.addObject("material", material);
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;
        }
    }

    @PostMapping("/admin/edit-material")
    public ModelAndView updateMaterial(@ModelAttribute("materials") ProductMaterial productMaterial){
        iProductMaterialService.save(productMaterial);
        ModelAndView modelAndView = new ModelAndView("admin-material-edit");
        modelAndView.addObject("material", productMaterial);
        modelAndView.addObject("message", "Material updated successfully");
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/delete-material/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        ProductMaterial productMaterial = iProductMaterialService.findById(id);
        if(productMaterial != null) {
            ModelAndView modelAndView = new ModelAndView("admin-material-delete");
            modelAndView.addObject("materials", productMaterial);
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-material")
    public ModelAndView deleteMaterial(@ModelAttribute("materials") ProductMaterial productMaterial){
        iProductMaterialService.remove(productMaterial.getId());
        Iterable<ProductMaterial> materials = iProductMaterialService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-material-list");
        modelAndView.addObject("materials", materials);
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }
}
