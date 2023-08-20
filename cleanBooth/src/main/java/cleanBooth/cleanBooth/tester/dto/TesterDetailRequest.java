package cleanBooth.cleanBooth.tester.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TesterDetailRequest {
    private boolean isTesting;
    private String itemName;
    private String itemImage;
    private String content;
    private LocalDate endDate;
    private String detailImage;


    public void setIsTesting(boolean testing) {
    }
}

