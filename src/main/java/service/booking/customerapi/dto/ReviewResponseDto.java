package service.booking.customerapi.dto;

import java.time.LocalDateTime;

public record ReviewResponseDto(
        Long id,
        String reviewContent,
        Integer reviewScore,
        LocalDateTime creationDate,
        LocalDateTime updateDate
) {
}
