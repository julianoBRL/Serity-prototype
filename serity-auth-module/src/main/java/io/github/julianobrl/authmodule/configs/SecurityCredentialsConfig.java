package io.github.julianobrl.authmodule.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.julianobrl.authmodule.filter.JwtUsernameAndPasswordAuthenticationFilter;
import io.github.julianobrl.seritycommon.security.config.JwtConfiguration;
import io.github.julianobrl.seritycommon.security.config.SecurityTokenConfig;
import io.github.julianobrl.seritycommon.security.filters.JwtTokenAuthorizationFilter;
import io.github.julianobrl.seritycommon.security.token.TokenConverter;
import io.github.julianobrl.seritycommon.security.token.TokenConverterAlt;
import io.github.julianobrl.seritycommon.security.token.TokenCreatorAlt;

/**
 * @author William Suane
 */
@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
    private final UserDetailsService userDetailsService;
    private final TokenCreatorAlt tokenCreator;
    private final TokenConverterAlt tokenConverter;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration,
                                     @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                     TokenCreatorAlt tokenCreator, TokenConverterAlt tokenConverter) {
        super(jwtConfiguration);
        this.userDetailsService = userDetailsService;
        this.tokenCreator = tokenCreator;
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfiguration, tokenCreator))
                .addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}