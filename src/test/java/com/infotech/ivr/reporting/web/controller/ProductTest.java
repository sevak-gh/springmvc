package com.infotech.ivr.reporting.web.controller;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.service.ProductService;

import java.util.Arrays;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * unit test for product web.
 *
 * @author Sevak Gahribian
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/applicationContext-mvc.xml", 
                                    "classpath:spring/testContext.xml",  })
public class ProductTest extends AbstractTestNGSpringContextTests  {

    private static final Logger LOG = LoggerFactory.getLogger(ProductTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductService productService;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        Mockito.reset(productService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getProductsList() throws Exception {
        Product p1 = new Product();
        p1.setName("book");
        p1.setPrice(new BigDecimal(215.33));
        Product p2 = new Product();
        p2.setName("guitar");
        p2.setPrice(new BigDecimal(1500.00));
        when(productService.findAllPageable(anyInt(), anyInt())).thenReturn(Arrays.asList(p1, p2));
        long count = 2;
        when(productService.getCount()).thenReturn(count);
        mockMvc.perform(get("/products"))
            .andExpect(status().isOk())
            .andDo(log());
   }
}

