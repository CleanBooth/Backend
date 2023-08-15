package cleanBooth.cleanBooth.service;

import cleanBooth.cleanBooth.domain.OAuthInfoResponse;
import cleanBooth.cleanBooth.domain.OAuthLoginParams;
import cleanBooth.cleanBooth.domain.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
