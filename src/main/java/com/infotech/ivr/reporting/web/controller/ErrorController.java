package com.infotech.ivr.reporting.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * generic error handler.
 *
 * @author Sevak Gharibian
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String error(Model model, HttpServletRequest request) {
        //model.addAttribute("count", count);
        String status = String.valueOf(request.getAttribute("javax.servlet.error.status_code"));
        String message = String.valueOf(request.getAttribute("javax.servlet.error.message"));
        String exception = String.valueOf(request.getAttribute("javax.servlet.error.exception"));
        LOG.debug("error, status:{}\nmessage:{}\nexception:{}", status, message, exception);
        return String.format("error handler:\nstatus: %s\nmessage: %s\n,exception:%s\n", status, message, exception);
    }
}
