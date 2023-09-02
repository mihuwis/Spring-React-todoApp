package com.progresspoint.demofirstWebApp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    // Response jest z jsp - wydaje sie ze return musi sie zgadzac z nazwą pliku w WEB-INF/jsp
    // Pay attention to app properties - suffix i prefix
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String goToWelcomePage(ModelMap modelMap){
        modelMap.put("name", getLoggedInUsername());
        return "welcome";
    }

    private String getLoggedInUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
