package com.infotech.ivr.reporting.web.controller;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.mockito.Mockito;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
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
 * unit test for product controller.
 *
 * @author Sevak Gahribian
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/applicationContext-mvc.xml", 
                                    "classpath:spring/testContext.xml",  })
public class ProductControllerTest extends AbstractTestNGSpringContextTests  {

    private static final Logger LOG = LoggerFactory.getLogger(ProductControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    // mocked product service
    @Autowired
    private ProductService productService;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        Mockito.reset(productService);
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .build();
        // to bypass thymeleaf security dialect expression processing, no spring security
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void shouldReturnProductsListAndStatusOK() throws Exception {
        // arrange
        Product p1 = new Product();
        p1.setName("book");
        p1.setPrice(new BigDecimal(215.33));
        Product p2 = new Product();
        p2.setName("guitar");
        p2.setPrice(new BigDecimal(1500.00));
        when(productService.findAllPageable(anyInt(), anyInt())).thenReturn(Arrays.asList(p1, p2));
        long count = 2;
        when(productService.getCount()).thenReturn(count);
        MockHttpServletRequestBuilder request = get("/products");

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(status().isOk())
                .andExpect(view().name("product/productList"))
                .andExpect(model().attribute("products", any(List.class)))
                .andExpect(model().attribute("products", hasSize((int)count)))
                .andExpect(model().attribute("count", count))
                .andExpect(model().attribute("page", 1))
                .andExpect(model().attribute("pageSize", 10))
                .andDo(log());
    }

    @Test
    public void shouldReturnProductByIdAndStatusOK() throws Exception {
        // arrange
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("book");
        p1.setPrice(new BigDecimal(215.33));
        when(productService.findById(anyLong())).thenReturn(p1);
        MockHttpServletRequestBuilder request = get("/products/1");

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(status().isOk())
                .andExpect(view().name("product/productCreateUpdate"))
                .andExpect(model().attribute("product", p1))
                .andDo(log());
    }

    @Test
    public void shouldCreateProductAndRedirect() throws Exception {
        // arrange
        String name = "book";
        String price = "215.33";
        String dateTime = "2015/08/27 17:05:05";
        MockHttpServletRequestBuilder request = post("/products/create")
                                                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                    .param("name", name)
                                                    .param("price", price)
                                                    .param("dateTime", dateTime);

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"))
                .andDo(log());
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productService, times(1)).save(productCaptor.capture());
        verifyNoMoreInteractions(productService);
        Product product = productCaptor.getValue();
        assertThat(product.getName(), is(name));
        assertThat(product.getPrice(), is(new BigDecimal(price)));
    }

    @Test
    public void shouldUpdateProductAndRedirect() throws Exception {
        // arrange        
        String id = "1";
        String name = "book";
        String price = "215.33";
        String dateTime = "2015/08/27 17:05:05";
        MockHttpServletRequestBuilder request = post("/products/" + id)
                                                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                    .param("name", name)
                                                    .param("price", price)
                                                    .param("dateTime", dateTime);

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"))
                .andDo(log());
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productService, times(1)).save(productCaptor.capture());
        verifyNoMoreInteractions(productService);
        Product product = productCaptor.getValue();
        assertThat(product.getName(), is(name));
        assertThat(product.getId(), is(Long.valueOf(id)));
        assertThat(product.getPrice(), is(new BigDecimal(price)));
    }
}

