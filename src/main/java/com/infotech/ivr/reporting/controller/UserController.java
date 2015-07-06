package com.infotech.ivr.reporting.controller;

import com.infotech.ivr.reporting.domain.User;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

/**
 * controller for user form.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        //TODO: find users list
        List<User> users = new ArrayList<User>();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String initCreateForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String processCreateForm(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user";
        } else {
            //TODO: save new user
            return "redirect:/users";
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("id") long id, Model model) {
        //TODO: find user by id
        User user = null;
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String processUpdateForm(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user";
        } else {
            //TODO: update user
            return "redirect:/users";
        }
    }
}
