package hoanglinh.codegym.controllers.admin;

import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.model.user.Role;
import hoanglinh.codegym.service.User.AccountService;
import hoanglinh.codegym.service.product.IStoreLocationService;
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
public class StoreLocationController {
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

    @GetMapping("/admin/storeLocations")
    public ModelAndView listStoreLocations(){
        Iterable<StoreLocation> storeLocations = iStoreLocationService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-store-list");
        modelAndView.addObject("storeLocations", storeLocations);
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/create-storeLocation")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admin-store-create");
        modelAndView.addObject("storeLocation", new StoreLocation());
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @PostMapping("/admin/create-storeLocation")
    public ModelAndView saveStoreLocation(@ModelAttribute("storeLocation") StoreLocation storeLocation){
        iStoreLocationService.save(storeLocation);
        ModelAndView modelAndView = new ModelAndView("admin-store-create");
        modelAndView.addObject("storeLocation", new StoreLocation());
        modelAndView.addObject("user", getAccount_role());
        modelAndView.addObject("message", "New store Location created successfully");
        return modelAndView;
    }

    @GetMapping("/admin/edit-storeLocation/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        StoreLocation storeLocation = iStoreLocationService.findById(id);
        if(storeLocation != null) {
            ModelAndView modelAndView = new ModelAndView("admin-store-edit");
            modelAndView.addObject("storeLocation", storeLocation);
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;
        }
    }

    @PostMapping("/admin/edit-storeLocation")
    public ModelAndView updateStoreLocation(@ModelAttribute("storeLocation") StoreLocation storeLocation){
        iStoreLocationService.save(storeLocation);
        ModelAndView modelAndView = new ModelAndView("admin-store-edit");
        modelAndView.addObject("storeLocation",storeLocation);
        modelAndView.addObject("message", "Store Location updated successfully");
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/delete-storeLocation/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        StoreLocation storeLocation = iStoreLocationService.findById(id);
        if(storeLocation != null) {
            ModelAndView modelAndView = new ModelAndView("admin-store-delete");
            modelAndView.addObject("storeLocation", storeLocation);
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            modelAndView.addObject("user", getAccount_role());
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-storeLocation")
    public ModelAndView deleteMaterial(@ModelAttribute("storeLocation") StoreLocation storeLocation){
        iStoreLocationService.remove(storeLocation.getId());
        Iterable<StoreLocation> storeLocations = iStoreLocationService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-store-list");
        modelAndView.addObject("storeLocations", storeLocations);
        modelAndView.addObject("user", getAccount_role());
        return modelAndView;
    }
}
