package org.bulletin_board.mail.notice;

import lombok.Builder;
import lombok.Value;
import org.bulletin_board.service.util.MapperUtil;

@Value
public class NoticeAttachment {
    private String name;
    private String originalFileName;
    private NoticeAttachmentType attachmentType;
    private String content;
    private String contentType;

    @Builder
    public NoticeAttachment(String name, String originalFileName, NoticeAttachmentType attachmentType, String content, String contentType) {
        this.name = name;
        this.originalFileName = originalFileName;
        this.attachmentType = MapperUtil.getValueOrDefault(attachmentType, NoticeAttachmentType.ATTACHMENT);
        this.content = content;
        this.contentType = contentType;
    }
}
