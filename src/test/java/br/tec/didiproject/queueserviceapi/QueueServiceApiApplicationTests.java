package br.tec.didiproject.queueserviceapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("tests")
class QueueServiceApiApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
    }

}
