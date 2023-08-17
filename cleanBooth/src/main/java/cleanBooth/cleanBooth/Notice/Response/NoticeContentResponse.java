package cleanBooth.cleanBooth.Notice.Response;

import cleanBooth.cleanBooth.Notice.Dto.SideNoticeDto;
import cleanBooth.cleanBooth.domain.Notice;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeContentResponse {
    Notice notice;
    SideNoticeDto beforeNotice;
    SideNoticeDto nextNotice;
}
