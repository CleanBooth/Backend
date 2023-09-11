package cleanBooth.cleanBooth.Tester.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TesterApplyGetDto {
    private boolean isTesting;
    private String itemName;
    private String itemImage;
    private String content;
    private LocalDate endDate;
    private String options;


    public void setIsTesting(boolean testing) {
        this.isTesting = true;
    }
}

