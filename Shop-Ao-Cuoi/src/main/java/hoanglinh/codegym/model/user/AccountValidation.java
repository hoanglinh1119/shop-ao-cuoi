package hoanglinh.codegym.model.user;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class AccountValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
          Account account= (Account) target;
           String name= account.getName();
           String username=account.getUsername();
           String pass=account.getPassword();
           String number=account.getNumberPhone();
           String email=account.getEmail();
           String address=account.getAddress();

        ValidationUtils.rejectIfEmpty(errors,name,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,username,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,email,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,pass,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,number,"value.empty");
        ValidationUtils.rejectIfEmpty(errors,address,"value.empty");

        Pattern patternNumber=Pattern.compile("(09|03|07|08|05)+([0-9]{8})");
        Pattern patternCharacter=Pattern.compile("([A-Z]{1,5}[a-z]{1,10}[0-9]{1,5})");
        Pattern patternEmail=Pattern.compile("(^[A-Za-z0-9+_.-]+@(.+))$");

        if (name.length()<5||name.length()>30){
            errors.rejectValue("name","name.length");
        }
        if (username.length()<6||username.length()>15){
            errors.rejectValue("username","username.length");
        }
//        if (!patternCharacter.matcher(username).find()){
//            errors.rejectValue("username","username.regex");
//        }
        if (!patternEmail.matcher(email).find()){
            errors.rejectValue("email","email.regex");
        }
        if (!patternNumber.matcher(pass).find()){
            errors.rejectValue("numberPhone","numberPhone.regex");
        }
        if (!patternCharacter.matcher(pass).find()){
            errors.rejectValue("password","password.regex");
        }
        if (address.length()<6||address.length()>100){
            errors.rejectValue("address","address.length");
        }

    }
}
