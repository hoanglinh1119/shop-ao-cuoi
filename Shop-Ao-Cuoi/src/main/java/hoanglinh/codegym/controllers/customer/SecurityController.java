package hoanglinh.codegym.controllers.customer;

import hoanglinh.codegym.model.product.Product;
import hoanglinh.codegym.model.user.Account;
import hoanglinh.codegym.model.user.Role;
import hoanglinh.codegym.service.User.AccountService;
import hoanglinh.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@SessionAttributes("role-user")
public class SecurityController {
    @Autowired
    private Role role;
    @Autowired
    private IProductService iProductService;
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

    @GetMapping("/login")
    public String showFormLogin() {
            return "login";
    }
    @GetMapping("/admin/home")
    public ModelAndView homeAdmin(@PageableDefault(size = 3) Pageable pageable){
        Page<Product> products=iProductService.findAll(pageable);

        ModelAndView modelAndView=new ModelAndView("home-admin");
        modelAndView.addObject("products",products);
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }

    @GetMapping(value = {"/home","/Access_Denied"})
    public String Homepage(Model model,@PageableDefault(size = 3) Pageable pageable) {
        Iterable<Product> products=iProductService.findAll(pageable);
        model.addAttribute("products", products);
        return "home";
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
        public ModelAndView Register(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult){
        ModelAndView mav=new ModelAndView("register");
        if (bindingResult.hasFieldErrors()){
            return mav;
        }
        if (accountService.checkUserName(account)){mav.addObject("message","tai khoan da ton tai");
            return mav;}

        else
            if (accountService.checkUserName(account)){
                mav.addObject("message","tai khoan da ton tai");
                return mav;
            } else if (accountService.checkNumber(account)&&accountService.checkMail(account)){
                mav.addObject("message1","email da duoc su dung");
                mav.addObject("message2","so dien thoai da su dung");
                return mav;
            }else if (accountService.checkMail(account))
            {mav.addObject("message1","email da duoc su dung");
                      return mav;
            } else
                if (accountService.checkNumber(account))
                {mav.addObject("message2","so dien thoai da su dung");
                       return mav;
                } else {account.setRole(role);
                accountService.save(account);
                mav.addObject("message","dang ki thanh cong");}


        return mav;
    }

    @GetMapping("/user/change-info")
    public ModelAndView formChangeInfo(){
        ModelAndView mav=new ModelAndView("change-info");
        Account account=accountService.getAccountByUserName(getPrincipal());
        mav.addObject("account",account);
        mav.addObject("user",getAccount_role());
        return mav;
    }
    @PostMapping("/user/change-info")
    public ModelAndView changeInfo(@Valid @ModelAttribute ("account")Account account,BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("change-info");
        if (!bindingResult.hasFieldErrors()) {
            Account account2 = accountService.getAccountByMail(account.getEmail());
            Account account3 = accountService.getAccountByNumber(account.getNumberPhone());

            if (accountService.checkNumber(account) && accountService.checkMail(account)) {
                if (account2.getUsername().equals(account.getUsername()) && account3.getUsername().equals(account.getUsername())) {
                    account.setRole(role);
                    accountService.save(account);
                    mav.addObject("message", "sua thanh cong");
                    return mav;
                } else if (!account2.getUsername().equals(account.getUsername())) {
                    mav.addObject("message", "email da duoc su dung");
                    return mav;
                } else if (!account3.getUsername().equals(account.getUsername())) {
                    mav.addObject("message", "so dien thoai da su dung");
                    return mav;
                } else {
                    mav.addObject("message", "email va so dien thoai da duoc su dung");
                    return mav;
                }

            } else {
                account.setRole(role);
                accountService.save(account);
                mav.addObject("message", "sua thanh cong");
                return mav;
            }
        } return mav;
    }
    @GetMapping("/user/change-pass")
    public ModelAndView formChangePass(){
        ModelAndView modelAndView=  new ModelAndView("change-pass");
        Account account=accountService.getAccountByUserName(getPrincipal());
        modelAndView.addObject("account",account);
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }
    @PostMapping("/user/change-pass")
    public ModelAndView changePass(@Valid @ModelAttribute("account") Account account,BindingResult bindingResult){
        ModelAndView mav=new ModelAndView("change-pass");
        if (!bindingResult.hasFieldErrors()){
            account.setRole(role);
            accountService.save(account);
            mav.addObject("message","sua thanh cong");
        }
        return mav;
    }

     @GetMapping("/product-detail/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("product-detail");
        Product product=iProductService.findOne(id);
        modelAndView.addObject("products",product);
        return modelAndView;
     }
    @GetMapping("/user/product-detail/{id}")
    public ModelAndView showProductDetailUser(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("product-detail");
        Product product=iProductService.findOne(id);
        modelAndView.addObject("products",product);
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }

    @GetMapping("/admin/product-detail/{id}")
    public ModelAndView showProductDetailAdmin(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("product-detail");
        Product product=iProductService.findOne(id);
        modelAndView.addObject("products",product);
        modelAndView.addObject("user",getAccount_role());
        return modelAndView;
    }

}


