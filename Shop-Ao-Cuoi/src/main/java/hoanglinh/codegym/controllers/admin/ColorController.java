package hoanglinh.codegym.controllers.admin;


import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import hoanglinh.codegym.service.product.IProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IProductColorService productColorService;
    @GetMapping("/admin/colors")
    public ModelAndView listColors(){
        Iterable<ProductColor> colors = productColorService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin-color-list");
        modelAndView.addObject("colors", colors);
        return modelAndView;
    }

    @GetMapping("/admin/create-product-color")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admin-color-create");
        modelAndView.addObject("colors", new ProductColor());
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
        modelAndView.addObject("message", "New color created successfully");

     return modelAndView;
    }

    @GetMapping("/admin/edit-color/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ProductColor color = productColorService.findById(id);
        if(color != null) {
            ModelAndView modelAndView = new ModelAndView("admin-color-edit");
            modelAndView.addObject("colors", color);
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
        return modelAndView;
    }

    @GetMapping("/admin/delete-color/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        ProductColor color = productColorService.findById(id);
        if(color != null) {
            ModelAndView modelAndView = new ModelAndView("admin-color-delete");
            modelAndView.addObject("color", color);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-color")
    public String deleteColor(@ModelAttribute("color") ProductColor productColor){
        productColorService.remove(productColor.getId());
        return "redirect:admin/colors";
    }
}
