package com.great.project;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class ProductRESTControllerTest extends AbstractProductTest {

    @Test
    void given_thereAreTabletsInTheDatabase_andANewTabletIsCreated_whenRetrievingTablets_thenTheirNumberIsCorrect()
            throws Exception {

        // create a product
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post(PRODUCT_ENDPOINT)
                                      .header("Authorization", AUTH_HEADER)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(createProduct("Tablet"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // get all products
        builder = MockMvcRequestBuilders.get(PRODUCT_ENDPOINT)
                                        .header("Authorization", AUTH_HEADER)
                                        .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.length()", greaterThanOrEqualTo(1)));

    }

    private String createProduct(final String productName) {
        return "{ \"id\": 0, \"name\": \"" + productName + "\", \"price\": 30.5}";
    }
}
