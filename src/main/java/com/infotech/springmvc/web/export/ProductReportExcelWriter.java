package com.infotech.springmvc.web.export;

import com.infotech.springmvc.domain.Product;
import com.infotech.springmvc.util.LocalDateTimeConverterFormatter;

import java.util.Map;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

/**
 * generic Excel writer for product report.
 *
 * @author Sevak Gharibian
 *
 */
public class ProductReportExcelWriter {

    public void writeExcel(Map<String, Object> model, Workbook workbook, 
                           Locale locale, MessageSource messsageSource) throws Exception {

        Sheet sheet = workbook.createSheet();

        // fill rows
        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>)model.get("products");
        int rowCount = 0;
        for (Product product : products) {
            Row row = sheet.createRow(++rowCount);
            writeRow(product, row, locale);
        }
    }

    private void writeRow(Product product, Row row, Locale locale) {
        row.createCell(1).setCellValue(product.getName());
        row.createCell(2).setCellValue(String.valueOf(product.getPrice()));
        row.createCell(3).setCellValue(LocalDateTimeConverterFormatter.print(product.getDateTime(), locale, "yyyy/MM/dd HH:mm:ss"));
   }        
}
