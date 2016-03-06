package com.infotech.springmvc.web.controller;

import com.infotech.springmvc.domain.Product;
import com.infotech.springmvc.domain.SortExpression;
import com.infotech.springmvc.domain.ProductReportFilter;
import com.infotech.springmvc.service.ProductService;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.servlet.http.HttpSession;
import java.security.Principal;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ProductController(ProductService productService, SimpMessagingTemplate messagingTemplate) {
        this.productService = productService;
        this.messagingTemplate = messagingTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('product_list_view')")
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
    @PreAuthorize("hasAnyAuthority('product_create_view')")
    public String initCreateForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/productCreateUpdate";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('product_create_do')")
    public String processCreateForm(@Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "product/productCreateUpdate";
        } else {
            productService.save(product);
            redirectAttributes.addFlashAttribute("message", String.format("product created: %s", product.toString()));
            sendForAllClients(String.format("websockettt,product created: %s", product.toString()));
            return "redirect:/products";
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('product_update_view')")
    public String initUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/productCreateUpdate";
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    @PreAuthorize("hasAnyAuthority('product_update_do')")
    public String processUpdateForm(@Valid Product product, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
        if (result.hasErrors()) {
            return "product/productCreateUpdate";
        } else {
            productService.save(product);
            redirectAttributes.addFlashAttribute("message", String.format("product updated: %s", product.toString()));
            if (principal != null) {
                sendForAClient(principal.getName(), String.format("%s, you updated the product[%d]", principal.getName(), product.getId()));
            }
            return "redirect:/products";
        }
    }

    @RequestMapping(value="/report", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('product_report_view')")
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
    @PreAuthorize("hasAnyAuthority('product_report_view')")
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
    @PreAuthorize("hasAnyAuthority('product_report_export')")
    public String reportExport(ProductReportFilter filter, BindingResult result, 
                         @RequestParam(value="sortField", required=false, defaultValue="dateTime") String sortField,
                         @RequestParam(value="isSortDirectionAsc", defaultValue="true") boolean isSortDirectionAsc,
                         Model model) {
        List<Product> products = productService.report(filter, sortField, isSortDirectionAsc);
        model.addAttribute("products", products);
        return "product/productReportExport";
    }

    @MessageMapping("/msg")
    public void info() {
        LOG.debug("websocket-message msg received!!!");
    }

    @MessageMapping("/echo")
    @SendTo("/topic/data")
    public String echo(String message) {
        LOG.debug("websocket-echo received: {}", message);
        return String.format("hi there: %s", message);
    }

    private void sendForAllClients(Object object) {
            messagingTemplate.convertAndSend("/topic/product", object);
    }        

    private void sendForAClient(String username, Object object) {
            messagingTemplate.convertAndSendToUser(username, "/queue/product", object);
    }        
}
