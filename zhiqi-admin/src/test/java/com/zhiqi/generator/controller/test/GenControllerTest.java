package com.zhiqi.generator.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author RyuJung
 * @since 2023/4/18-17:24
 */
@SpringBootTest
@AutoConfigureMockMvc
public class GenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    void tableDataListTest() {
        assertThat(mockMvc).isNotNull();
        // TODO GenController test

    }

}
