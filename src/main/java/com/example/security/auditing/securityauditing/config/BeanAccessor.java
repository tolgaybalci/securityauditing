package com.example.security.auditing.securityauditing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanAccessor implements ApplicationContextAware{

    private static ApplicationContext context;

    public static ObjectMapper getObjectMapper(){return getBean(ObjectMapper.class);}

    public static <T> T getBean(Class<T> beanClass, Object... args) {return context.getBean(beanClass, args);}

    public static <T> T getBean(Class<T> beanClass) {return context.getBean(beanClass);}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
