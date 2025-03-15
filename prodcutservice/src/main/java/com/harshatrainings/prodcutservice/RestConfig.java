package com.harshatrainings.prodcutservice;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class RestConfig {


    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .filter((request, next) -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    String token;
                    if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
                        token = jwt.getTokenValue();
                    } else {
                        token = null;
                    }
                    return next.exchange(
                            ClientRequest.from(request)
                                    .headers(headers -> headers.setBearerAuth(token))
                                    .build()
                    );
                })
                .build();
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setInterceptors(List.of(new JwtRequestInterceptor()));
        return restTemplate;
    }

}
