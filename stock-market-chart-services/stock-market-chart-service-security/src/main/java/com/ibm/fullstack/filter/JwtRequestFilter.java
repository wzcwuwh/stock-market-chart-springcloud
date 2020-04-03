package com.ibm.fullstack.filter;

import com.ibm.fullstack.Util.JwtTokenUtil;
import com.ibm.fullstack.config.IgnoreUrlsConfig;
import com.ibm.fullstack.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig(){
        return new IgnoreUrlsConfig();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        log.info("should not filter in class OncePerRequestFilter has been triggered...");
        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        return ignoreUrlsConfig().getUrls().stream().anyMatch(p -> antPathMatcher.match(p, request.getServletPath()));
        log.info(String.valueOf(ignoreUrlsConfig().getUrls().size()));
//        for(String url: ignoreUrlsConfig().getUrls()){
//            log.info(url);
//            log.info(request.getServletPath());
//            if(antPathMatcher.match(url, request.getServletPath())){
//                return true;
//            } else {
//                return false;
//            }
//        }
        if(antPathMatcher.match("/authenticate", request.getServletPath())){
            return true;
        } else {
            return false;
        }
//        return super.shouldNotFilter(request);
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("method doFilterInternal in class OncePerRequestFilter triggered...");
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        //jwt token is in the form "Bearer token". remove the Bearer word and get only the token
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e){
                logger.error("unable to get jwt token");
            } catch (ExpiredJwtException e){
                logger.error("JWT token has expired");
            }
        } else {
            logger.warn("JWT token does not begin with Bearer string");
        }

        //once we get the token validate it
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //if token is valid, config spring security to manually set authentication
            if(jwtTokenUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //after setting the authentication in the context, we specify that the current user is authenticated. So it passed the spring security configurations successfully
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
