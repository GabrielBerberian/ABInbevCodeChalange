package org.abinbev.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BrandController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BrandControllerTest {

    private static final String URL_TEMPLATE = "/v1/brand";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetBrands() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE)
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }
}
