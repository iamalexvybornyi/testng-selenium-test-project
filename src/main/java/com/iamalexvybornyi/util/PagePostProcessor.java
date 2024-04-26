package com.iamalexvybornyi.util;

import com.iamalexvybornyi.core.page.Page;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PagePostProcessor implements BeanPostProcessor {

    private final ElementInitializationHelper helper;

    @Override
    @Nullable
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        return (bean instanceof Page) ? helper.initPageElements(bean) : bean;
    }
}
