package cleanBooth.cleanBooth.domain;

public interface OAuthInfoResponse {
    String getName();
    String getEmail();
    String getBirthDay();
    String getBirthYear();
    String getMobile();

    OAuthProvider getOAuthProvider();
}
