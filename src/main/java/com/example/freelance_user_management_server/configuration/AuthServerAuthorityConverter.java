package com.example.freelance_user_management_server.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthServerAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {
		ArrayList<String> scopes = (ArrayList<String>) source.getClaims().get("scope");
		if (scopes == null || scopes.isEmpty()) {
			return new ArrayList<>();
		}
		Collection<GrantedAuthority> grantedAuthorities = scopes.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return grantedAuthorities;
	}
}
