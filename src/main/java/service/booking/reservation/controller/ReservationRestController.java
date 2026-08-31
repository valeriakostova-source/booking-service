package service.booking.reservation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import org.springframework.security.core.Authentication;
import service.booking.reservation.model.CreateReservationRequest;
import service.booking.reservation.model.Reservation;
import service.booking.reservation.model.UpdateReservationRequest;
import service.booking.reservation.model.dto.GetAllCustomerReservationsDto;
import service.booking.reservation.service.ReservationService;
import service.booking.roomapi.entity.Room;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
public class ReservationRestController {
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody CreateReservationRequest request, Authentication auth) {
        Long customerId = getId(auth);
        request.setCustomerId(customerId);
        Reservation createReservation = reservationService.createReservation(request);

        return ResponseEntity
                .status(
                        HttpStatus.CREATED
                ).body(
                        createReservation
                );
    }

    @GetMapping("/getAllCustomerReservation")
    public ResponseEntity<List<GetAllCustomerReservationsDto>> getAllCustomerReservation(Authentication auth) {
        Long id = getId(auth);
        if (id == null) {
            System.out.println("id is null");

            return ResponseEntity.status(302)
                    .header(
                            "Location",
                            "/login"
                    ).build();
        }

        List<GetAllCustomerReservationsDto> listDto = reservationService.getAllReservationByCustomerId(id);
        System.out.println("RESERVATIONS FOUND = " + listDto.size());
        return ResponseEntity.ok(listDto);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long reservationId, Authentication auth) {
        Long customerId = getId(auth);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        reservationService.cancelReservation(reservationId, customerId)
                );
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId, @Valid @RequestBody UpdateReservationRequest request, Authentication auth) {
        Long customerId = getId(auth);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        reservationService.updateReservation(
                                reservationId,
                                customerId,
                                request.getCheckIn(),
                                request.getCheckOut())
                );
    }

    //Customer don't need authorization to check available rooms
    @GetMapping()
    public List<Room> getAvailableRooms(
            @RequestParam @NotNull LocalDate checkIn,
            @RequestParam @NotNull LocalDate checkOut,
            @RequestParam @Min(1) int guests) {

        return reservationService
                .getAvailableRooms(
                        checkIn,
                        checkOut,
                        guests
                );
    }

    @GetMapping("/has-active-booking")
    public boolean hasActiveReservation(Authentication auth) {
        return reservationService.hasActiveReservation(getId(auth));
    }

     private Long getId(Authentication authentication) {
        return Long.parseLong(authentication.getName());
    }
}
