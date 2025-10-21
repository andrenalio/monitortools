package com.monitortools.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
		.csrf(csrf -> csrf.disable()) // desativa CSRF (para facilitar testes via Postman)
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/**").permitAll() // permite o login sem autenticação
				.anyRequest().authenticated() // o restante exigirá token (futuro)
				)
		//.formLogin(form -> form.disable()) // desativa o form padrão
		//.httpBasic(basic -> basic.disable()); // desativa autenticação básica
		.oauth2ResourceServer(oauth2 -> oauth2
				.jwt(jwt -> jwt.jwtAuthenticationConverter(new CognitoJwtConverter())) // converter claims do token
				);

		return http.build();
	}
}
