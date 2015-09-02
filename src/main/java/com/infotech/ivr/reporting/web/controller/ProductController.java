package com.infotech.ivr.reporting.web.controller;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.service.ProductService;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * controller for product form.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String initCreateForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String processCreateForm(@ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product";
        } else {
            productService.save(product);
            return "redirect:/products";
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String processUpdateForm(@ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product";
        } else {
            productService.save(product);
            return "redirect:/products";
        }
    }
}
