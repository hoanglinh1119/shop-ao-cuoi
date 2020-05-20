package hoanglinh.codegym.controllers.customer;

import hoanglinh.codegym.model.product.Product;
import hoanglinh.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
 @Autowired
 private IProductService iProductService;


}
