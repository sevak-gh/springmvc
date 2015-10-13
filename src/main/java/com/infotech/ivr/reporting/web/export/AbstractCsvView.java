package com.infotech.ivr.reporting.web.export;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import org.springframework.web.servlet.view.AbstractView;

/**
 * csv abstract view for spring mvc
 * based on OpenCsv.
 *
 * @author Sevak Gharibian
 *      
 */    
public abstract class AbstractCsvView extends AbstractView {

    /**
     * This constructor sets the appropriate content type "text/xml".
     * Note that IE won't take much notice of this, but there's not a lot we
     * can do about this. Generated documents should have a ".pdf" extension.
     */ 
    public AbstractCsvView() {
        setContentType("text/csv");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception  {
        
        response.setContentType("text/csv");                        // set content type
        response.setHeader("Content-Disposition", "attachment");    // make browser to ask for download/display

        // Build CSV document.
        buildCsvDocument(model, request, response);
    }

    /**
     * Subclasses must implement this method to build an CSV document.
     * 
     * @param model the model Map
     * @param request in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     * @throws Exception any exception that occurred during document building
     */
    protected abstract void buildCsvDocument(Map<String, Object> model, 
                                             HttpServletRequest request, HttpServletResponse response) throws Exception;
}
