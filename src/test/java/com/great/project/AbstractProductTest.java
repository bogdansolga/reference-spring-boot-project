package com.great.project;

import com.great.project.service.SectionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

import static com.great.project.controller.ProductController.API_PREFIX;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(Profiles.IN_MEMORY)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public abstract class AbstractProductTest {

    protected static final String PRODUCT_ENDPOINT = API_PREFIX + "/product";
    protected static final String AUTH_HEADER = "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes());

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private SectionService sectionService;

    @BeforeAll
    public void initializeProducts() {
        // the saved section will contain 10 products
        sectionService.createGoodiesSectionAndProducts();
    }
}
