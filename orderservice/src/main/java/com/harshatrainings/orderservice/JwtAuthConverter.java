package com.harshatrainings.orderservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken>{
	
	@Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
	String clientId;

	private final JwtGrantedAuthoritiesConverter jwtGrantAuthConvertor = new JwtGrantedAuthoritiesConverter();
	
	@Override
	public AbstractAuthenticationToken convert(Jwt jwtToken) {
		Collection<GrantedAuthority> authorities = 
				Stream.concat(jwtGrantAuthConvertor.convert(jwtToken).stream(), extractRoles(jwtToken).stream())
				.collect(Collectors.toSet());
		return new JwtAuthenticationToken(jwtToken,authorities);
	}

	private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt){
		Set<String> roles = new HashSet<>();
		Map<String, Object> realmAcces = jwt.getClaim("realm_access");
		
		//System.out.println("realmAcces :::: "+realmAcces);
		
		if(realmAcces!=null && realmAcces.containsKey("roles")) {
			roles.addAll((Collection<? extends String>) realmAcces.get("roles"));
		}
		
		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
		
		//System.out.println("resourceAccess ::: " + resourceAccess);
		
		if(resourceAccess!=null && resourceAccess.containsKey(clientId)) {
			Map<String,Object> accesclient = (Map<String, Object>) resourceAccess.get(clientId);
			if(accesclient!=null && accesclient.containsKey("roles")) {
				roles.addAll((Collection<? extends String>) accesclient.get("roles"));
			}
		}
		
		//System.out.println("Extracted Roles ::: " + roles);
		
		return roles.stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.toUpperCase())).collect(Collectors.toSet());
	}
	
}
