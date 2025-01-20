package org.example.api_gateway_service.configuration.security;

import org.example.api_gateway_service.configuration.security.jwt.converter.JwtAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(exchange ->
                        exchange.pathMatchers("/auth/**").permitAll()
                                .anyExchange().authenticated())
                .oauth2ResourceServer(oa2 ->
                        oa2.jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakJwtConverter())))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable).build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> keycloakJwtConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new JwtAuthoritiesConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}
