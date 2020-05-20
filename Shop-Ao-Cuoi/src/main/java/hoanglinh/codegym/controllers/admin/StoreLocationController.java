package hoanglinh.codegym.controllers.admin;

import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import hoanglinh.codegym.service.product.IStoreLocationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/admin/storeLocations")
    public ModelAndView listStoreLocations(){
        Iterable<StoreLocation> storeLocations = iStoreLocationService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-store-list");
        modelAndView.addObject("storeLocations", storeLocations);
        return modelAndView;
    }

    @GetMapping("/admin/create-storeLocation")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admin-store-create");
        modelAndView.addObject("storeLocation", new StoreLocation());
        return modelAndView;
    }

    @PostMapping("/admin/create-storeLocation")
    public ModelAndView saveStoreLocation(@ModelAttribute("storeLocation") StoreLocation storeLocation){
        iStoreLocationService.save(storeLocation);
        ModelAndView modelAndView = new ModelAndView("admin-store-create");
        modelAndView.addObject("storeLocation", new StoreLocation());
        modelAndView.addObject("message", "New store Location created successfully");
        return modelAndView;
    }

    @GetMapping("/admin/edit-storeLocation/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        StoreLocation storeLocation = iStoreLocationService.findById(id);
        if(storeLocation != null) {
            ModelAndView modelAndView = new ModelAndView("admin-store-edit");
            modelAndView.addObject("storeLocation", storeLocation);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/edit-storeLocation")
    public ModelAndView updateStoreLocation(@ModelAttribute("storeLocation") StoreLocation storeLocation){
        iStoreLocationService.save(storeLocation);
        ModelAndView modelAndView = new ModelAndView("admin-store-edit");
        modelAndView.addObject("storeLocation",storeLocation);
        modelAndView.addObject("message", "Store Location updated successfully");
        return modelAndView;
    }

    @GetMapping("/admin/delete-storeLocation/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        StoreLocation storeLocation = iStoreLocationService.findById(id);
        if(storeLocation != null) {
            ModelAndView modelAndView = new ModelAndView("admin-store-delete");
            modelAndView.addObject("storeLocation", storeLocation);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-storeLocation")
    public String deleteMaterial(@ModelAttribute("storeLocation") StoreLocation storeLocation){
        iStoreLocationService.remove(storeLocation.getId());
        return "redirect:admin/storeLocations";
    }
}
