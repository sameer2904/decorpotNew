package com.decorpot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class LoginController extends DefaultController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String navigateToDashboard(final Model m) {
        System.out.println("decorpot-web/IntroPageController:navigateToDashboard");
        m.addAttribute("title", "Test Funda");
        System.out.println("decorpot-web/IntroPageController:navigateToDashboard Calling navigatewithModels");
        return navigateWithModels(m, "login", "introPage", "introPage", "introPage");
    }

}
