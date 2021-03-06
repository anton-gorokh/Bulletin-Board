package org.bulletin_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bulletin_board.dto.author.AuthorDto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementFilterDto {
    private Long id;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private LocalDate from;
    private LocalDate to;
    private String title;

    @NotNull
    private SimpleValue category;

    @NotNull
    private AuthorDto author;
}
