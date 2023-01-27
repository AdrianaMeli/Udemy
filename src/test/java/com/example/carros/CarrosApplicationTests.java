package com.example.carros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarrosApplicationTests {

    @Autowired
    private void test1(){



        service.insert();
    }

    @Test
    void contextLoads() {
    }

}
