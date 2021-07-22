package hello.springmvc.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    public void helloMessage() {
        String message = ms.getMessage("hello", null, Locale.ENGLISH);
        Assertions.assertThat(message).isEqualTo("hello");
    }

    @Test
    void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, Locale.ENGLISH);
        Assertions.assertThat(message).isEqualTo("hello Spring");
    }
}
