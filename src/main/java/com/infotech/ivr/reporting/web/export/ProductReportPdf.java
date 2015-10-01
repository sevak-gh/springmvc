package com.infotech.ivr.reporting.web.export;

import com.infotech.ivr.reporting.domain.Product;

import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotech.ivr.reporting.web.export.AbstractPdfView;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * pdf export for product report.
 *
 * @author Sevak Gharibian
 *
 */
@SuppressWarnings("unchecked")
public class ProductReportPdf extends AbstractPdfView {
    
    @Override
    public void buildPdfDocument(Map<String, Object> model, Document document, 
                                 PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);

        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(new Phrase("name"));
        table.addCell(new Phrase("price"));
        table.addCell(new Phrase("dt"));
        table.getDefaultCell().setBackgroundColor(null);
        table.setHeaderRows(1);

        List<Product> products = (List<Product>)model.get("products");
        for (Product product : products) {
            table.addCell(new Phrase(product.getName()));
            table.addCell(new Phrase(String.valueOf(product.getPrice())));
            table.addCell(new Phrase(String.valueOf(product.getDateTime())));
        }

        document.add(table);
    }
}
