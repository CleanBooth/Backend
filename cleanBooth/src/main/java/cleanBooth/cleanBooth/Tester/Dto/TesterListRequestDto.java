package cleanBooth.cleanBooth.Tester.Dto;

import java.time.LocalDate;

public class TesterListRequestDto {
    private String itemName;
    private String itemImage;
    private LocalDate endDate;
    private boolean isTesting;
    // 생성자

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean getIsTesing() {
        return isTesting;
    }

    public void setIsTesting(boolean isTesting) {
        this.isTesting = isTesting;
    }
}
