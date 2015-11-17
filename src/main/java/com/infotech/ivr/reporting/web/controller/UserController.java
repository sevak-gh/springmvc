package com.infotech.ivr.reporting.web.controller;

import com.infotech.ivr.reporting.domain.User;
import com.infotech.ivr.reporting.domain.Role;
import com.infotech.ivr.reporting.service.UserService;
import com.infotech.ivr.reporting.service.RoleService;
import com.infotech.ivr.reporting.web.conversion.CustomRoleEditor;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.BindingResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for user form.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, new CustomRoleEditor());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value="page", defaultValue="1") int page,
                       @RequestParam(value="pageSize", defaultValue="10") int pageSize,
                       Model model) {
        LOG.debug("getting users list");
        List<User> users = userService.findAllPageable(page, pageSize);
        long count = userService.getCount();
        model.addAttribute("users", users);
        model.addAttribute("count", count);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        return "user/userList";
    }

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String initCreateForm(Model model) {
        User user = new User();
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user/userCreateUpdate";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String processCreateForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/userCreateUpdate";
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("id") long id, Model model) {
        List<Role> roles = roleService.findAll();
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user/userCreateUpdate";
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String processUpdateForm(User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/userCreateUpdate";
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }
}
