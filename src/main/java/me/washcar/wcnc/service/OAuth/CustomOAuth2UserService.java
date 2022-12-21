package me.washcar.wcnc.service.OAuth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.repository.RoleRepo;
import me.washcar.wcnc.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    final UserRepository userRepository;

    final HttpSession httpSession;

    final RoleRepo roleRepo;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest); // Oath2 정보를 가져옴

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        String email, nickname;
        Map<String, Object> attributes = oAuth2User.getAttributes();

        if(registrationId.equals("kakao")){
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            log.info(String.valueOf(kakaoAccount));
            email = (String) kakaoAccount.get("email");
            nickname = (String) ((HashMap) kakaoAccount.get("profile")).get("nickname");
        } else {
            throw new OAuth2AuthenticationException("허용되지 않은 OAuth 인증");
        }

        User user = userRepository.findByEmail(email);

        if(user == null){
            user = new User(null, null, null, null,
                    new HashSet<>(), new HashSet<>(), new ArrayList<>());
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPassword("OAuth_NOPASSWORD");

            Role role = roleRepo.findByName("ROLE_USER");
            user.getRoles().add(role);

            userRepository.save(user);
        }

        httpSession.setAttribute("user", user);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString())),
            oAuth2User.getAttributes(),
            userNameAttributeName);
    }
}
