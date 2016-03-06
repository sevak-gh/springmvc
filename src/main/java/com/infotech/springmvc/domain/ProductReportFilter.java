package com.infotech.springmvc.domain;

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

    public LocalDateTime getFromDate() {
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

    @Override
    public String toString() {
        return String.format("ProductReportFilter[name=%s,price=%s,fromDate=%s,toDate=%s]", 
                                name, 
                                String.valueOf(price), 
                                String.valueOf(fromDate), 
                                String.valueOf(toDate));
    }
}
