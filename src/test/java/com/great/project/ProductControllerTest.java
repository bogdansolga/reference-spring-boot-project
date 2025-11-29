package com.great.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractProductTest {

    private static final String PRODUCT_NAME = "The product with the ID 1";

    @Test
    public void givenTheContentTypeIsCorrect_WhenGettingAProduct_ThenAllGood() throws Exception {
        mockMvc.perform(get(PRODUCT_ENDPOINT + "/{id}", 1)
                        .header("Authorization", AUTH_HEADER)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(PRODUCT_NAME));
    }

    @Test
    public void givenTheContentTypeIsCorrect_WhenGettingAllProducts_ThenAllGood() throws Exception {
        mockMvc.perform(get(PRODUCT_ENDPOINT)
                        .header("Authorization", AUTH_HEADER)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10))
                .andExpect(jsonPath("$[0].name").value(PRODUCT_NAME));
    }

    // a sample of using a parameterized test
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void givenTheContentTypeIsCorrect_WhenUsingADataProvider_ThenAllGood(final String productId, final int statusCode) throws Exception {
        mockMvc.perform(get(PRODUCT_ENDPOINT + "/{id}", productId)
                        .header("Authorization", AUTH_HEADER)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(statusCode));
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("1", 200),   // productId and status code
                Arguments.of("13", 400)
        );
    }
}
