package org.bulletin_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bulletin_board.dto.author.AuthorDto;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {
    private Long id;
    private String name;
    private String text;
    private BigDecimal pay;
    private Boolean active;

    private AuthorDto author;
    private SimpleValue category;
}
