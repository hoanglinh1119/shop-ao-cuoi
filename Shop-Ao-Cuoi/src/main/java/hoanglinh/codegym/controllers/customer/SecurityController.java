package hoanglinh.codegym.controllers.customer;

import hoanglinh.codegym.model.product.Product;
import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.model.user.Role;
import hoanglinh.codegym.service.User.AccountService;
import hoanglinh.codegym.service.product.IProductService;
import hoanglinh.codegym.model.user.AccountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("role-user")
public class SecurityController {
    @Autowired
    private Role role;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private AccountService accountService;

    private String getPrincipal() {
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

    @GetMapping("/login")
    public String showFormLogin() {
            return "login";
    }
    @GetMapping("/admin/home")
    public ModelAndView homeAdmin(@PageableDefault(size = 3) Pageable pageable){
        Iterable<Product> products=iProductService.findAll(pageable);
        ModelAndView modelAndView=new ModelAndView("home-admin");
        modelAndView.addObject("products",products);
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
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
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView showFormRegister(){
        ModelAndView mav=new ModelAndView("register");
          mav.addObject("account",new Account());
        return mav;
    }
    @PostMapping("/register")
    public String Register(@Validated @ModelAttribute Account account, BindingResult bindingResult,Model model){
        System.out.println(account.getName());
        new AccountValidation().validate(account,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "register";
        }
        if (accountService.checkUserName(account)){
            model.addAttribute("message","tai khoan da ton tai");
        }else {
            account.setRole(role);
            accountService.save(account);
            model.addAttribute("message","dang ki thanh cong");
        }
        return "register";
    }
}


