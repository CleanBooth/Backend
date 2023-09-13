package cleanBooth.cleanBooth.Tester;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TesterApplyPostDto {
    private String name;
    private String address;
    private String phoneNum;
    private String message;
    private String options;
    private Long testerId;
}
