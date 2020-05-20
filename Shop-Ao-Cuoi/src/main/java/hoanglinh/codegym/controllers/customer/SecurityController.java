package hoanglinh.codegym.controllers.customer;

import hoanglinh.codegym.model.product.Product;
import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.service.User.AccountService;
import hoanglinh.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class SecurityController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private AccountService accountService;

    private Collection<? extends GrantedAuthority> getPrincipal() {
        Collection<? extends GrantedAuthority> role=null ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            role = ((UserDetails) principal).getAuthorities();
        }
        return role;
    }

    @GetMapping("/login")
    public String showFormLogin(String username, String password, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("user",getPrincipal());
        if (getPrincipal() == null) {
            return "login";
        } else
        return "redirect:user/home";
    }

    @GetMapping(value = {"/home","/Access_Denied"})
    public String Homepage(Model model,@PageableDefault(size = 3) Pageable pageable) {
        Iterable<Product> products=iProductService.findAll(pageable);
        model.addAttribute("products", products);
        return "home-user";
    }

    @GetMapping("/user/home")
    public ModelAndView showProduct(@PageableDefault(size = 3) Pageable pageable){
        Iterable<Product> products=iProductService.findAll(pageable);
        ModelAndView modelAndView=new ModelAndView("home-user");
        modelAndView.addObject("products",products);
        modelAndView.addObject("user",getPrincipal());
        Collection<? extends GrantedAuthority> role=getPrincipal();
        System.out.println(role);
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView showFormRegister(@ModelAttribute("user")Account account){
        ModelAndView mav=new ModelAndView("register");
          mav.addObject("user",new Account());
        return mav;
    }
}


