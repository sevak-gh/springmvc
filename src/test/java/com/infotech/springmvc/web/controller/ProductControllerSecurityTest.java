package com.infotech.springmvc.web.controller;

import com.infotech.springmvc.domain.Product;
import com.infotech.springmvc.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
 * unit test for product controller security.
 *
 * @author Sevak Gahribian
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/applicationContext-mvc.xml", 
                                    "classpath:spring/applicationContext-security.xml",
                                    "classpath:spring/testContext.xml",  })
public class ProductControllerSecurityTest extends AbstractTestNGSpringContextTests  {

    private static final Logger LOG = LoggerFactory.getLogger(ProductControllerSecurityTest.class);

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
                    .apply(springSecurity())
                    .build();
    }

    @Test
    public void shouldBeAccessibleForAll() throws Exception {
        // arrange
        MockHttpServletRequestBuilder request = get("/").secure(true);

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(status().isOk())
                .andDo(log());
    }

    @Test
    public void shouldBeAnauthenticatedWhenNoUser() throws Exception {
        // arrange
        MockHttpServletRequestBuilder request = get("/products").secure(true);

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(unauthenticated())
                .andDo(log());
    }

    @Test
    //@WithMockUser(username = "testuser", authorities = { "product_list_view" })
    public void shouldBeAuthorizedWhenUserHasAuthority() throws Exception {
        // arrange
        MockHttpServletRequestBuilder request = get("/products")
                                                    .secure(true)
                                                    .with(
                                                        user("testuser")
                                                            .authorities(new SimpleGrantedAuthority("product_list_view")));

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(log());
    }

    @Test
    public void shouldBeNotAuthorizedWhenUserHasNoAuthority() throws Exception {
        // arrange
        MockHttpServletRequestBuilder request = get("/users")
                                                    .secure(true)
                                                    .with(
                                                        user("testuser")
                                                            .authorities(new SimpleGrantedAuthority("product_list_view")));

        // act
        ResultActions response = mockMvc.perform(request);

        // assert
        response.andExpect(status().isForbidden())
                .andDo(log());
    }
}
