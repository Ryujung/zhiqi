package com.zhiqi;

import com.zhiqi.generator.mapper.GenTableMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author RyuJung
 * @since 2023/4/20-16:20
 */
@SpringBootTest(properties = {"spring.profiles.active=test"})
public class BaseTest {

    @Autowired
    private GenTableMapper genTableMapper;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(genTableMapper);
    }

}
