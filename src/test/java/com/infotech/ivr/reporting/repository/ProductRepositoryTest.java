package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;    
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * product reository integration test
 *
 * @author Sevak Gahribian
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class ProductRepositoryTest extends AbstractTransactionalTestNGSpringContextTests  {

    private static final Logger LOG = LoggerFactory.getLogger(ProductRepositoryTest.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DataSource ds;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
    }

    @Test
    public void findAllShouldReturnAllEntries() {
        // arrange
        List<Product> products = null;
        
        // act
        products = productRepository.findAll();
    
        // assert
        assertThat(products, is(notNullValue()));
        assertThat(products.size(), is(3));
        assertThat(products.get(0).getName(), is("book"));
    }

    @Test
    public void saveShouldInsertTheProduct() {
        // arrange
        Product product = new Product();
        product.setName("pen");
        product.setPrice(new BigDecimal(15.20));
        product.setDateTime(LocalDateTime.now());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        
        // act
        productRepository.save(product);
    
        // assert
        assertThat(product, is(notNullValue()));
        assertThat(product.getId(), is(notNullValue()));
        assertThat(product.getName(), is(jdbcTemplate.queryForObject("select name from product where id = ?", String.class, product.getId())));
    }
}
