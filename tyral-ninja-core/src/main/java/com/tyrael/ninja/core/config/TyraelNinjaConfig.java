package com.tyrael.ninja.core.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mbmartinez
 */
@Configuration
public class TyraelNinjaConfig {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }

}
