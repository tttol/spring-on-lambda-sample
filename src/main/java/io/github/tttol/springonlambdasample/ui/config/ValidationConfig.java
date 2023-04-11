package io.github.tttol.springonlambdasample.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        var localValidatorFactoryBean = new LocalValidatorFactoryBean();

        var rMessageSource = new ResourceBundleMessageSource();
        rMessageSource.setBasename("messages");
        rMessageSource.setDefaultEncoding("UTF-8");
        localValidatorFactoryBean.setValidationMessageSource(rMessageSource);

        return localValidatorFactoryBean;
    }
}
