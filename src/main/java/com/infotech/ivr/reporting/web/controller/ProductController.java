package com.infotech.ivr.reporting.web.controller;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.SortExpression;
import com.infotech.ivr.reporting.domain.ProductReportFilter;
import com.infotech.ivr.reporting.service.ProductService;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.servlet.http.HttpSession;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for product form.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    private static final int DEFAULT_PAGE_SIZE = 10;


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    //@PreAuthorize("hasAnyAuthority('product_list_view')")
    public String list(@RequestParam(value="page", defaultValue="1") int page,
                       @RequestParam(value="pageSize", defaultValue="10") int pageSize,
                       Model model) {
        LOG.debug("getting products list");
        List<Product> products = productService.findAllPageable(page, pageSize);
        long count = productService.getCount();
        model.addAttribute("products", products);
        model.addAttribute("count", count);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        return "product/productList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String initCreateForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/productCreateUpdate";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String processCreateForm(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product/productCreateUpdate";
        } else {
            productService.save(product);
            return "redirect:/products";
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/productCreateUpdate";
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String processUpdateForm(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product/productCreateUpdate";
        } else {
            productService.save(product);
            return "redirect:/products";
        }
    }

    @RequestMapping(value="/report", method = RequestMethod.GET)
    public String report(Model model) {
        ProductReportFilter filter = new ProductReportFilter();
        List<Product> products = productService.reportPageable(filter, "dateTime", false, 1, DEFAULT_PAGE_SIZE);
        long count = productService.reportCount(filter);
        model.addAttribute("productReportFilter", filter);
        model.addAttribute("products", products);
        model.addAttribute("count", count);
        model.addAttribute("page", 1);
        model.addAttribute("pageSize", DEFAULT_PAGE_SIZE);
        model.addAttribute("sortField", "dateTime");
        model.addAttribute("isSortDirectionAsc", true);
       return "product/productReport";
    }

    @RequestMapping(value="/report", method = RequestMethod.POST)
    public String report(ProductReportFilter filter, BindingResult result, 
                         @RequestParam(value="page", defaultValue="1") int page,
                         @RequestParam(value="pageSize", defaultValue="10") int pageSize,
                         @RequestParam(value="sortField", required=false, defaultValue="dateTime") String sortField,
                         @RequestParam(value="isSortDirectionAsc", defaultValue="true") boolean isSortDirectionAsc,
                         Model model) {
        List<Product> products = productService.reportPageable(filter, sortField, isSortDirectionAsc, page, pageSize);
        long count = productService.reportCount(filter);
        model.addAttribute("products", products);
        model.addAttribute("count", count);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortField", sortField);
        model.addAttribute("isSortDirectionAsc", isSortDirectionAsc);
        return "product/productReport";
    }

    @RequestMapping(value="/reportExport", method = RequestMethod.POST)
    public String reportExport(ProductReportFilter filter, BindingResult result, 
                         @RequestParam(value="sortField", required=false, defaultValue="dateTime") String sortField,
                         @RequestParam(value="isSortDirectionAsc", defaultValue="true") boolean isSortDirectionAsc,
                         Model model) {
        List<Product> products = productService.report(filter, sortField, isSortDirectionAsc);
        model.addAttribute("products", products);
        return "product/productReportExport";
    }

}
