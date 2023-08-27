package cleanBooth.cleanBooth.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import cleanBooth.cleanBooth.repository.UserRepository;
import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.domain.AuthTokens;
import cleanBooth.cleanBooth.domain.OAuthLoginParams;
import cleanBooth.cleanBooth.domain.OAuthInfoResponse;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
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