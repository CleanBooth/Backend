package cleanBooth.cleanBooth.service;

import cleanBooth.cleanBooth.domain.AuthTokens;
import cleanBooth.cleanBooth.domain.OAuthInfoResponse;
import cleanBooth.cleanBooth.domain.OAuthLoginParams;
import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthLoginService {
    private static final Logger logger = LoggerFactory.getLogger(OAuthLoginService.class);
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        logger.info("Login process started with params: {}", params);
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {
        logger.info("Finding or creating user with email: {}", oAuthInfoResponse.getEmail());
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private Long newUser(OAuthInfoResponse oAuthInfoResponse) {
        logger.info("Creating new user with email: {}", oAuthInfoResponse.getEmail()); // 로그 추가

        User user = User.builder()
                .name(oAuthInfoResponse.getName())
                .email(oAuthInfoResponse.getEmail())
                .age(oAuthInfoResponse.getAge())
                .birthday(oAuthInfoResponse.getBirthDay())
                .birthyear(oAuthInfoResponse.getBirthYear())
                .mobile(oAuthInfoResponse.getMobile())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return userRepository.save(user).getId();

    }
}