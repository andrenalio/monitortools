package com.monitortools.api.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CognitoJwtConverter  implements Converter<Jwt, AbstractAuthenticationToken>{

	  @Override
	    public AbstractAuthenticationToken convert(Jwt jwt) {
	        Map<String, Object> claims = jwt.getClaims();
	        List<SimpleGrantedAuthority> authorities = Collections.emptyList();

	        if (claims.containsKey("cognito:groups")) {
	            List<String> groups = (List<String>) claims.get("cognito:groups");
	            authorities = groups.stream()
	                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
	                    .collect(Collectors.toList());
	        }

	        return new JwtAuthenticationToken(jwt, authorities);
	    }
}
