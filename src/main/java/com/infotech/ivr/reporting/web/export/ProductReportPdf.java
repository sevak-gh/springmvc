package com.infotech.ivr.reporting.web.export;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.util.LocalDateTimeConverterFormatter;


import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import java.util.Locale;
import java.io.File;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pdf export for product report.
 *
 * @author Sevak Gharibian
 *
 */
@SuppressWarnings("unchecked")
public class ProductReportPdf extends AbstractPdfView {

    private static final Logger LOG = LoggerFactory.getLogger(ProductReportPdf.class);

    @Resource
    private MessageSource messageSource;

    private org.springframework.core.io.Resource font;

    public void setFont(org.springframework.core.io.Resource font) {
        this.font = font;
    }
   
    @Override
    public void buildPdfDocument(Map<String, Object> model, Document document, 
                                 PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOG.debug("PDF generator...");
        Locale locale = LocaleContextHolder.getLocale();
        LOG.debug("PDF generator: locale:{}", locale.getLanguage());
        LOG.debug("PDF generator: font:{}", font.getFile().getAbsolutePath());
        BaseFont bf = BaseFont.createFont(font.getFile().getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);        
 
        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        if (locale.getLanguage().equalsIgnoreCase("fa")) {
            table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        }
        table.setWidthPercentage(100);

        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(new Phrase(messageSource.getMessage("product.name", null, locale), new Font(bf, 12)));
        table.addCell(new Phrase(messageSource.getMessage("product.price", null, locale), new Font(bf, 12)));
        table.addCell(new Phrase(messageSource.getMessage("product.dateTime", null, locale), new Font(bf, 12)));
        table.getDefaultCell().setBackgroundColor(null);
        table.setHeaderRows(1);
        List<Product> products = (List<Product>)model.get("products");
        for (Product product : products) {
            table.addCell(new Phrase(product.getName(), new Font(bf, 12)));
            table.addCell(new Phrase(String.valueOf(product.getPrice()), new Font(bf, 12)));
            table.addCell(new Phrase(LocalDateTimeConverterFormatter.print(product.getDateTime(), locale, "yyyy/MM/dd HH:mm:ss"), new Font(bf, 12)));
        }

        document.add(table);
    }
}
