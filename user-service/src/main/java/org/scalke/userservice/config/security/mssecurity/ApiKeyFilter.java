package org.scalke.userservice.config.security.mssecurity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;

@Slf4j
public class ApiKeyFilter extends AbstractAuthenticationProcessingFilter {

    public ApiKeyFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
        log.info("Initializing ApiKeyFilter");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Authentication authentication = AuthenticationService.getAuthentication(request);
        return getAuthenticationManager().authenticate(authentication);
    }
}
