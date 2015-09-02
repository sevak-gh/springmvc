package com.infotech.ivr.reporting.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * controller for home page.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
public class HomeController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("message", "ivr reporting home page message");
        return "index";
    }
}
