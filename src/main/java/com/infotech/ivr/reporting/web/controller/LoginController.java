package com.infotech.ivr.reporting.web.controller;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for access control.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(@RequestParam(value="error", required=false) String error, Model model) {
        return "login/signin";
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signout(Model model) {
        return "login/signout";
    }

}
