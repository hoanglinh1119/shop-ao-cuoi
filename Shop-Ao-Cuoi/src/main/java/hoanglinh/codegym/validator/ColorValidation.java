//import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//public class ColorValidation  implements Validator {
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return ProductColor.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//     ProductColor productColor=(ProductColor) target;
//     String color=productColor.getColor();
//        ValidationUtils.rejectIfEmpty(errors,"color","value.empty");
//    }
//}
