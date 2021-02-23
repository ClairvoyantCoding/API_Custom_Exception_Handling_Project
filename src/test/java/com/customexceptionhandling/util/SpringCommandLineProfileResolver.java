package com.customexceptionhandling.util;

import org.springframework.test.context.ActiveProfilesResolver;
import org.springframework.test.context.support.DefaultActiveProfilesResolver;

public class SpringCommandLineProfileResolver implements ActiveProfilesResolver {
    private DefaultActiveProfilesResolver defaultActiveProfilesResolver = new DefaultActiveProfilesResolver();

    @Override
    public String[] resolve(final Class<?> testClass) {
        final String activeProfile = System.getProperty("spring.profiles.active");
        return new String[] { activeProfile == null ? "integration" : activeProfile };
    }
}
