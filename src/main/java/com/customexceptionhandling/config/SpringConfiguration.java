package com.customexceptionhandling.config;

import com.customexceptionhandling.ProjectTrackerApplication;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class SpringConfiguration {
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(ProjectTrackerApplication.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean //NOTE: Used to render swagger-ui
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
