package indi.icear.telegramframework.service.disquemq.BeanFactory;

import com.github.xetorthio.jedisque.Jedisque;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DisqueMQBeanFactory implements FactoryBean<Jedisque> {

    private Set<String> address;

    @NotNull
    @Override
    public Jedisque getObject() throws Exception {

        List<URI> list = new ArrayList<>();
        for (String a : address) {
            String join = String.join("", "disque://", a);
            URI uri = new URI(join);
            list.add(uri);
        }
        return new Jedisque(
                list.toArray(new URI[0])
        );
    }

    @NotNull
    @Override
    public Class<?> getObjectType() {
        return Jedisque.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setAddress(Set<String> address) {
        this.address = address;
    }
}
