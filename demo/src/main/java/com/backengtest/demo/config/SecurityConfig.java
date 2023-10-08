package com.backengtest.demo.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${jwt.public.key}")
    RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    RSAPrivateKey privateKey;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors().and()
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/apply/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/apply/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/apply/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/work/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/work/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/projectsrec/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/projectsrec/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/apply")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/profile/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/profile/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/profile/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/profile")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/apply/")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/apply/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/profile/")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/profile/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/comments/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/projects/")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/projects/")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, "/projects/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/projects/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/projects/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                ).build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
