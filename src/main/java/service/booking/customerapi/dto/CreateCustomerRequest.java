package service.booking.customerapi.dto;

public record CreateCustomerRequest(
        String firstname,
        String lastname,
        String identificationNumber,
        String email,
        String password,
        String phoneNumber
) {
}
