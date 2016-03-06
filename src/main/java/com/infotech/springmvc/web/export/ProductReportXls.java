package com.infotech.springmvc.web.export;

import com.infotech.springmvc.domain.Product;
import com.infotech.springmvc.util.LocalDateTimeConverterFormatter;

import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import javax.annotation.Resource;

import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel 2003 export for product report.
 *
 * @author Sevak Gharibian
 *
 */
@Component("product/productReportExport.xls")
public class ProductReportXls extends AbstractXlsView {
    
    private static final Logger LOG = LoggerFactory.getLogger(ProductReportXls.class);

    @Resource
    private MessageSource messageSource;

    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook, 
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOG.debug("PDF generator...");

        Locale locale = LocaleContextHolder.getLocale();

        // init font

        ProductReportExcelWriter writer = new ProductReportExcelWriter();
        writer.writeExcel(model, workbook, locale, messageSource);
    }
}

