package com.infotech.springmvc.web.export;

import com.infotech.springmvc.domain.Product;
import com.infotech.springmvc.util.LocalDateTimeConverterFormatter;


import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import java.util.Locale;
import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * csv export for product report.
 *
 * @author Sevak Gharibian
 *
 */
@Component("product/productReportExport.csv")
public class ProductReportCsv extends AbstractCsvView {

    private static final Logger LOG = LoggerFactory.getLogger(ProductReportCsv.class);

    @Resource
    private MessageSource messageSource;

    @Override
    public void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOG.debug("CSV generator...");

        Locale locale = LocaleContextHolder.getLocale();

        // add header
        response.getWriter().printf("\"%s\",\"%s\",\"%s\"\n", 
                                    messageSource.getMessage("product.name", null, locale),
                                    messageSource.getMessage("product.price", null, locale),
                                    messageSource.getMessage("product.dateTime", null, locale));

        // fill rows
        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>)model.get("products");
        for (Product product : products) {
            response.getWriter().printf("\"%s\",\"%s\",\"%s\"\n", 
                                        product.getName(),
                                        String.valueOf(product.getPrice()),
                                        LocalDateTimeConverterFormatter.print(product.getDateTime(), locale, "yyyy/MM/dd HH:mm:ss"));
        }
    }
}
