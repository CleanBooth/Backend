package cleanBooth.cleanBooth.Notice.Response;

import cleanBooth.cleanBooth.Notice.Dto.NoticeMainDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class NoticeMainResponse {
    private int totalCount;
    private List<NoticeMainDto> noticeMainDtoList;
}
