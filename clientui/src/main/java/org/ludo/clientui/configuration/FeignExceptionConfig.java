package org.ludo.clientui.configuration;

import org.ludo.clientui.exceptions.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignExceptionConfig {

    @Bean
    public CustomErrorDecoder custumErrorDecoder(){
        return new CustomErrorDecoder();
    }

}
