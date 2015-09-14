package com.infotech.ivr.reporting.web.controller;

import com.infotech.ivr.reporting.domain.User;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * controller for user form.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private static List<User> users = new ArrayList<User>() {{        
        add(new User() {{
            setUsername("jordan");
            setPassword("123456");
            setExpireDate(LocalDateTime.now());
        }});        
    }};

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        //TODO: find users list from DB
        //List<User> users = new ArrayList<User>();
        //User user = new User();
        //user.setUsername("jordan");
        //user.setPassword("123456");
        //user.setExpireDate(LocalDateTime.now());
        //users.add(user);
        model.addAttribute("users", users);
        return "user/userList";
    }

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String initCreateForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/userCreateUpdate";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String processCreateForm(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/userCreateUpdate";
        } else {
            //TODO: save new user
            users.add(user);
            return "redirect:/users";
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("id") long id, Model model) {
        //TODO: find user by id
        User user = null;
        user = new User();
        user.setUsername("jordan");
        user.setPassword("123456");
        user.setExpireDate(LocalDateTime.now());
        model.addAttribute("user", user);
        return "user/userCreateUpdate";
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String processUpdateForm(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/userCreateUpdate";
        } else {
            //TODO: update user
            return "redirect:/users";
        }
    }
}
