package com.infotech.ivr.reporting.web.controller;

import com.infotech.ivr.reporting.domain.Role;
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
 * controller for role form.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
@RequestMapping("/roles")
public class RoleController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /*
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, new CustomRoleEditor());
    }
    */

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value="page", defaultValue="1") int page,
                       @RequestParam(value="pageSize", defaultValue="10") int pageSize,
                       Model model) {
        LOG.debug("getting roles list");
        List<Role> roles = roleService.findAllPageable(page, pageSize);
        long count = roleService.getCount();
        model.addAttribute("roles", roles);
        model.addAttribute("count", count);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        return "role/roleList";
    }

    /*
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String initCreateForm(Model model) {
        Role role = new Role();
        List<Role> roles = roleService.findAll();
        model.addAttribute("role", role);
        model.addAttribute("roles", roles);
        return "role/roleCreateUpdate";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String processCreateForm(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            return "role/roleCreateUpdate";
        } else {
            roleService.save(role);
            return "redirect:/roles";
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("id") long id, Model model) {
        List<Role> roles = roleService.findAll();
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        model.addAttribute("roles", roles);
        return "role/roleCreateUpdate";
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String processUpdateForm(Role role, BindingResult result) {
        if (result.hasErrors()) {
            return "role/roleCreateUpdate";
        } else {
            roleService.save(role);
            return "redirect:/roles";
        }
    }
    */
}
