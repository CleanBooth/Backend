package cleanBooth.cleanBooth.Mypage.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MyTesterDto {
    private Long testerId;
    private Boolean isWin;
    private String itemName;
    private String itemImage;
    private LocalDate endDate;
    private Boolean isTesting;
    private Boolean isReviewed;


}
