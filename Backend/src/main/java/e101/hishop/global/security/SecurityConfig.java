package e101.hishop.global.security;

import e101.hishop.global.security.filter.CustomAuthenticationFilter;
import e101.hishop.global.security.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String LOGIN_URL = "/api/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        //url로 들어왔을떄 해당필터를 실행
        customAuthenticationFilter.setFilterProcessesUrl(LOGIN_URL);
        CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter();
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable();
        http.httpBasic().disable();
        http.headers().frameOptions().disable();
        http.rememberMe().disable();

//        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**/*").permitAll();
        http.authorizeRequests().antMatchers("/api/login/**", "/api/refresh_token/**", "/api/logout/**", "/api/sign-up").permitAll();
        http.authorizeRequests().antMatchers("*").permitAll();
        http.authorizeRequests().antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(List.of("http://localhost"));
//        configuration.setAllowedMethods(List.of("GET","POST", "PATCH", "DELETE", "PUT"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var configuration = new CorsConfiguration();

        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true); // 내 서버가 응답을 할 때 응답해준 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        //TODO HTTPS React, IOT Origin 변경
//        configuration.setAllowedOrigins(List.of("http://localhost"));
        configuration.setAllowedOriginPatterns(List.of("http://localhost*", "http://192.168.*"));
        configuration.setAllowedMethods(List.of("GET","POST", "PATCH", "DELETE", "PUT", "OPTIONS"));
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(List.of("*"));
        source.registerCorsConfiguration("/**", configuration);  // /** 로 들어오는 모든 요청들은 config를 따르도록 등록
        return new CorsFilter(source);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}