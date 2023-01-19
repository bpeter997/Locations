package com.learn.locations.configuration;

import com.c4_soft.springaddons.security.oauth2.OAuthentication;
import com.c4_soft.springaddons.security.oauth2.OpenidClaimSet;
import com.c4_soft.springaddons.security.oauth2.config.synchronised.OAuth2AuthenticationFactory;
import java.util.Collection;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.GrantedAuthority;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  OAuth2AuthenticationFactory authenticationFactory(
      Converter<Map<String, Object>, Collection<? extends GrantedAuthority>> authoritiesConverter) {
    return (bearerString, claims) -> new OAuthentication<>(new OpenidClaimSet(claims),
        authoritiesConverter.convert(claims), bearerString);
  }

}
