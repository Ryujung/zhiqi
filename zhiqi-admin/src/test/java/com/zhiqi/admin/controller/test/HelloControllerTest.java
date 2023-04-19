package com.zhiqi.admin.controller.test;

import com.zhiqi.web.controller.HelloController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 使用MockMvc尝试单元测试controller请求
 * FIXME 测试失败，status：401 Unauthorized （应该是spring security拦截的问题，需要修复）
 *
 * @author RyuJung
 * @since 2023/4/18-17:58
 */
@SpringBootTest(properties = {"server.port=9495"})
@AutoConfigureMockMvc
public class HelloControllerTest {

    private final int port = 9495;

    private String baseUrl = "http://localhost:" + port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HelloController helloController;

    @Test
    void contextLoadsTest() {
        Assertions.assertThat(helloController).isNotNull();
    }

    @Test
    void helloTest() throws Exception {
        mockMvc.perform(get(baseUrl + "/hello")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("Hello World")));
    }
}
