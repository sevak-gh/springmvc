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

import org.springframework.stereotype.Component;
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
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pdf export for product report.
 *
 * @author Sevak Gharibian
 *
 */
@Component("product/productReportExport.pdf")
public class ProductReportPdf extends AbstractPdfView {

    private static final Logger LOG = LoggerFactory.getLogger(ProductReportPdf.class);

    @Resource
    private MessageSource messageSource;

    @Override
    @SuppressWarnings("unchecked")
    public void buildPdfDocument(Map<String, Object> model, Document document, 
                                 PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOG.debug("PDF generator...");

        // set page event handler
        writer.setPageEvent(new PageHandler());
        
        Locale locale = LocaleContextHolder.getLocale();

        // create font
        LOG.debug("PDF generator: font path:{}...", font.getFile().getAbsolutePath());
        BaseFont bf = BaseFont.createFont(font.getFile().getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);        
        Font pdfFont = new Font(bf, 12);
        Font pdfFontRed = new Font(bf, 12, Font.NORMAL, BaseColor.RED);
 
        // init table
        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        if (locale.getLanguage().equalsIgnoreCase("fa")) {
            table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        }
        table.setWidthPercentage(100);

        // table header
        table.getDefaultCell().setBackgroundColor(BaseColor.MAGENTA);
        LOG.debug("PDF generator: header:{},{},{}...", 
                    messageSource.getMessage("product.name", null, locale), 
                    messageSource.getMessage("product.price", null, locale), 
                    messageSource.getMessage("product.dateTime", null, locale));
        table.addCell(new Phrase(messageSource.getMessage("product.name", null, locale), pdfFont));
        table.addCell(new Phrase(messageSource.getMessage("product.price", null, locale), pdfFont));
        table.addCell(new Phrase(messageSource.getMessage("product.dateTime", null, locale), pdfFont));
        table.getDefaultCell().setBackgroundColor(null);
        table.setHeaderRows(1);

        // fill table rows
        List<Product> products = (List<Product>)model.get("products");
        for (Product product : products) {
            table.addCell(new Phrase(product.getName(), pdfFont));
            if (product.getPrice() != null) {
                table.addCell(new Phrase(String.valueOf(product.getPrice()), pdfFontRed));
            } else {
                table.addCell(new Phrase("", pdfFontRed));
            }
            table.addCell(new Phrase(LocalDateTimeConverterFormatter.print(product.getDateTime(), locale, "yyyy/MM/dd HH:mm:ss"), pdfFont));
        }

        document.add(table);
    }

    class PageHandler extends PdfPageEventHelper {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable tbl = new PdfPTable(1);
            tbl.setTotalWidth(document.getPageSize().getWidth());
            tbl.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tbl.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tbl.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            tbl.addCell(new Phrase(String.valueOf(writer.getPageNumber())));
            tbl.writeSelectedRows(0, -1, 0, 30, writer.getDirectContent());            
        }

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
        }
    }
}
