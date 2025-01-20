package org.example.api_gateway_service.configuration.security.jwt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");
        if(realmAccess != null && realmAccess.containsKey("roles")){
            return ((Collection<String>) realmAccess.get("roles")).stream().filter(r -> r.startsWith("ROLE_")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        throw new RuntimeException("Jwt doesn't contains realm access or realm access doesn't contains roles.");
    }
}
