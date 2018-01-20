package com.example;

import java.io.ByteArrayInputStream;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade.Request;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter
{
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
    	keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/user").hasRole("user")
                .antMatchers("/admin").hasRole("admin")
                .anyRequest().permitAll();
    }
    
    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
    	return new KeycloakConfigResolver() {
			
			@Override
			public KeycloakDeployment resolve(Request req) {
				System.out.println("keycloakConfigResolver() call.");
				
				final String json = "{ \"realm\": \"Test\", \"bearer-only\": true, \"auth-server-url\": \"http://localhost:8080/auth\", \"ssl-required\": \"external\", \"resource\": \"Test\", \"use-resource-role-mappings\": false, \"public-client\": true }";
				
				return KeycloakDeploymentBuilder.build(new ByteArrayInputStream(json.getBytes()));
			}
		};
    }
}
