package org.raghaji.street_paw_network.config;

import org.raghaji.street_paw_network.services.Impl.UserserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig   {

    private final UserserviceImpl userServiceImpl;
    @Autowired
    private AppPass appPass;
    public SecurityConfig(UserserviceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userServiceImpl).passwordEncoder(appPass.passwordEncoder());

        http.csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/users/**").hasRole("Admin")
                .requestMatchers("/api/roles/**").hasAnyRole("User", "Admin")
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(org.springframework.security.config.Customizer.withDefaults())
            .sessionManagement( sessmgmt -> sessmgmt
                .sessionConcurrency(sessCC -> sessCC
                    .maximumSessions(1)
                    .expiredUrl("/login?expired"))
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }



    // @Bean
    // public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    //     AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
    //     auth.userDetailsService(userServiceImpl).passwordEncoder(appPass.passwordEncoder());
    //     return auth.build();
    // }



//No Authetication
    // @Bean
	// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	// 	http.csrf(customizer->customizer.disable());
	// 	http.authorizeHttpRequests(request->request.anyRequest().anonymous());   //.anyRequest().authenticated());
	// 	http.httpBasic(Customizer.withDefaults());
	// 	http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	// 	return http.build();
    // }
}
