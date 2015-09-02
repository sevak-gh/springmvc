package com.infotech.ivr.reporting.domain;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty; 

/**
 * simple business object representing a product.
 *
 * @author Sevak Gharibian
 *
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @Max(10000)    
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "dt")
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("Product[id:%d, name:%s, price:%d]", id, name, price);
    }
}
