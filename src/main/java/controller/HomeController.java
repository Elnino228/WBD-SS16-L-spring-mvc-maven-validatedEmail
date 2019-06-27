package controller;

import org.graalvm.compiler.replacements.aarch64.AArch64StringUTF16Substitutions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HomeController {
    private static Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_REGEX="^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public HomeController(){
        pattern=Pattern.compile(EMAIL_REGEX);
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model){
        return "home";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String user(@RequestParam("email") String email, ModelMap modelMap){
        boolean isvalid=this.validate(email);
        if (!isvalid){
            modelMap.addAttribute("message","Email is invalid");
            return "home";
        }
        modelMap.addAttribute("email",email);
        return "success";
    }

    private boolean validate(String email) {
        matcher=pattern.matcher(email);
        return matcher.matches();
    }
}
