package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String name;
    private String email;
    private String birthday;
    private String phoneNum;

    private OAuthProvider oAuthProvider;

    @Builder
    public User(String name, String email, String birthday, OAuthProvider oAuthProvider) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.oAuthProvider = oAuthProvider;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
}
