package com.ibm.fullstack.config;

import com.ibm.fullstack.entrypoint.JwtAuthenticationEntryPoint;
import com.ibm.fullstack.filter.JwtRequestFilter;
import com.ibm.fullstack.handler.RestfulAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
//        for(String url: ignoreUrlsConfig().getUrls()){
//            web.ignoring().antMatchers(url);
//        }
    }

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //configure AuthenticationManager so that it knows from where to load
        //user for matching credentials
        //Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("method configure in class WebSecurityConfigurerAdapter triggered...");

//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
//                httpSecurity.authorizeRequests();
//        for(String url: ignoreUrlsConfig().getUrls()){
//            registry.antMatchers(url).permitAll();
//        }
//
//        //allow cross origin options request
//        registry.antMatchers(HttpMethod.OPTIONS)
//                .permitAll();
//
//        //role authentication required for every request
//        registry.and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler())
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //we don't need CSRF for this
        httpSecurity.csrf().disable()
                //don't authenticate this particular request
        .authorizeRequests()
//                .antMatchers("/security/authenticate")
//                .permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//                .permitAll()
                //all other request need to be authenticated
        .anyRequest().authenticated().and()
                //make sure we use stateless session; session won't be userd to store user's state
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Add a filter to validate the tokens with every request
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    public IgnoreUrlsConfig ignoreUrlsConfig(){
//        return new IgnoreUrlsConfig();
//    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler(){
        return new RestfulAccessDeniedHandler();
    }
}
