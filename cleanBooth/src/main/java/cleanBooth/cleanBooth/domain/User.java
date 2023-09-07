package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String age;

    @Column
    private String birthday;

    @Column
    private String birthyear;

    @Column
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column
    private OAuthProvider oAuthProvider;

    @Builder
    public User(String name, String email, String age, String birthday, String birthyear, String mobile, OAuthProvider oAuthProvider) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.birthyear = birthyear;
        this.mobile = mobile;
        this.oAuthProvider = oAuthProvider;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

}
