package service.booking.reviewapi.dto;

import java.time.LocalDateTime;

public record ReviewResponseDto(
        Long id,
        Long userId,
        Long roomId,
        String reviewContent,
        Integer reviewScore,
        LocalDateTime creationDate,
        LocalDateTime updateDate
) {
}
