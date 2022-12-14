package me.washcar.wcnc.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.util.JwtManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtManager jwtManager;
    @Override
    @SuppressWarnings("unchecked")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorityCollection = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();

        Optional<SimpleGrantedAuthority> simpleGrantedAuthority = simpleGrantedAuthorityCollection.stream().findFirst();

        String roleString = simpleGrantedAuthority.orElseThrow().getAuthority();
        String regexRole = "ROLE_[A-Z]*";

        Pattern p = Pattern.compile(regexRole);
        Matcher m = p.matcher(roleString);

        List<String> roleClaimList = new ArrayList<>();
        while (m.find()) {
            roleClaimList.add(m.group());
        }

        String access_token = JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 180 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", roleClaimList)
                .sign(jwtManager.getAlgorithm());

        String refresh_token = JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 360 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(jwtManager.getAlgorithm());

        String url = UriComponentsBuilder.fromUriString("https://www.washcar.me").path("/oauth2/redirect").queryParam("token", access_token).queryParam("refresh", refresh_token).build().toUriString();
        // String url = UriComponentsBuilder.fromUriString("http://localhost:3000").path("/oauth2/redirect").queryParam("token", access_token).queryParam("refresh", refresh_token).build().toUriString();
        response.sendRedirect(url);
    }
}
