package indi.icear.telegramframework.service.disquemq;

import com.github.xetorthio.jedisque.Jedisque;
import indi.icear.telegramframework.SpringTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class DisqueMQBeanFactoryTest extends SpringTestBase {

    @Autowired
    private Jedisque jedisque;

    @Test
    public void testDisqueMQ() {
        assertNotNull(jedisque);
    }
}