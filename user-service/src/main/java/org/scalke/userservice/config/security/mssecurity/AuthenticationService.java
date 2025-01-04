package org.scalke.userservice.config.security.mssecurity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

//@Service
@AllArgsConstructor @Getter @Setter
public class AuthenticationService {
    private static RsakeysConfig rsakeysConfig;
    private static final String API_KEY_HEADER = "API-KEY";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(API_KEY_HEADER);
        String apiSecret = request.getHeader(AUTHORIZATION_HEADER);

        if(
                (apiKey == null) || (!Objects.equals(rsakeysConfig.publicKey().toString(),apiKey))  ||
                        (apiSecret == null) || (!Objects.equals(rsakeysConfig.privateKey().toString(),apiSecret))
        ) {
            throw new BadCredentialsException("Invalid API Key");
        }
        return new ApiKeyAuthenticationToken(apiKey,null, AuthorityUtils.NO_AUTHORITIES);
    }

}
