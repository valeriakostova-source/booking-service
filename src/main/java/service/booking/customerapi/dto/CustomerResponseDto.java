package service.booking.customerapi.dto;

import jakarta.validation.constraints.NotNull;
public record CustomerResponseDto(
        @NotNull
        String firstname,
        @NotNull
        String email,
        @NotNull
        String password,
        @NotNull
        Long id
) {
}
