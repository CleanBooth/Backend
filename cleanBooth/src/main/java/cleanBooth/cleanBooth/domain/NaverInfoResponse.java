package cleanBooth.cleanBooth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverInfoResponse implements OAuthInfoResponse {

    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {
        private String name;
        private String mobile;
        private String email;
        private String birthday;
    }

    @Override
    public String getName() {
        return response.name;
    }

    @Override
    public String getEmail() {
        return response.email;
    }

    @Override
    public String getMobile() {
        return response.mobile;
    }

    @Override
    public String getBirthDay() {
        return response.birthday;
    }


    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.NAVER;
    }
}