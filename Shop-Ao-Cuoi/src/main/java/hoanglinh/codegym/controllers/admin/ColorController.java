package hoanglinh.codegym.controllers.admin;



import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.model.user.Role;
import hoanglinh.codegym.service.User.AccountService;
import hoanglinh.codegym.service.product.IProductColorService;
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
public class ColorController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private IProductColorService productColorService;

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
    @GetMapping("/admin/colors")
    public ModelAndView listColors(){
        Iterable<ProductColor> colors = productColorService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-color-list");
        modelAndView.addObject("colors", colors);
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/create-product-color")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admin-color-create");
        modelAndView.addObject("colors", new ProductColor());
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }

    @PostMapping("/admin/create-product-color")
    public ModelAndView saveColor(@Validated @ModelAttribute("colors") ProductColor productColor, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){

        ModelAndView modelAndView = new ModelAndView("admin-color-create");

            return modelAndView;
        }
        productColorService.save(productColor);
        ModelAndView modelAndView = new ModelAndView("admin-color-create");
        modelAndView.addObject("colors", new ProductColor());
        modelAndView.addObject("user", getAccount_role());
        modelAndView.addObject("message", "New color created successfully");

     return modelAndView;
    }

    @GetMapping("/admin/edit-color/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ProductColor color = productColorService.findById(id);
        if(color != null) {
            ModelAndView modelAndView = new ModelAndView("admin-color-edit");
            modelAndView.addObject("colors", color);
            modelAndView.addObject("user",getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/edit-color")
    public ModelAndView updateColor(@Validated @ModelAttribute("colors") ProductColor productColor){
        productColorService.save(productColor);
        ModelAndView modelAndView = new ModelAndView("admin-color-edit");
        modelAndView.addObject("colors", productColor);
        modelAndView.addObject("message", "Color updated successfully");
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/delete-color/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        ProductColor color = productColorService.findById(id);
        if(color != null) {
            ModelAndView modelAndView = new ModelAndView("admin-color-delete");
            modelAndView.addObject("color", color);
            modelAndView.addObject("user",getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-color")
    public ModelAndView deleteColor(@ModelAttribute("color") ProductColor productColor){
        ModelAndView modelAndView=new ModelAndView("admin-color-list");
        productColorService.remove(productColor.getId());
        Iterable<ProductColor> colors = productColorService.findAll();
        modelAndView.addObject("colors", colors);
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }
}
