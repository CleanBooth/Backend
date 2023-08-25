package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.*;

import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String name;
    private String email;
    private String birthday;
    private String birthyear;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    @Builder
    public User(String name, String email, String birthday, String birthyear, String mobile, OAuthProvider oAuthProvider) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.birthyear = birthyear;
        this.mobile = mobile;
        this.oAuthProvider = oAuthProvider;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

}
