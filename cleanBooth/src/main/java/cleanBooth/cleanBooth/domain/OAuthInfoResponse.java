package cleanBooth.cleanBooth.domain;

public interface OAuthInfoResponse {
    String getName();
    String getEmail();
    String getBirthDay();
    String getMobile();

    OAuthProvider getOAuthProvider();
}
