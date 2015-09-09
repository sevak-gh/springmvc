package com.infotech.ivr.reporting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * representing product report/search filter.
 *
 * @author Sevak Gharibian
 * 
 */
public class ProductReportFilter {

    private String name;
    private BigDecimal price;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getFfromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }
}
