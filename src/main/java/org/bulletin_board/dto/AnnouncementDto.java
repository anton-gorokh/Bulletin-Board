package org.bulletin_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bulletin_board.dto.author.AuthorDto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private String text;
    private BigDecimal pay;
    private Boolean active;

    @NotNull
    private SimpleValue category;

    @NotNull
    private AuthorDto author;
}
