package service.booking.customerapi;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import service.booking.dto.CreateCustomerRequest;
import service.booking.dto.LoginDto;
import service.booking.dto.UpdateDto;

@RestController
@RequestMapping("/connect")
public class ConnectionController {
    private final RestClient restClient;

    public ConnectionController() {
        this.restClient = RestClient
                .builder()
                .baseUrl("http://customer-service:8081")
                .build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCustomerRequest request) {
        try {
            return (restClient
                    .post()
                    .uri("/api/customers/create")
                    .body(request)
                    .retrieve()
                    .toEntity(Object.class)
            );
        } catch (HttpClientErrorException e) {
            return (ResponseEntity
                    .status(e.getStatusCode())
                    .body(e.getResponseBodyAsString())
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        try {
            return (restClient
                    .post()
                    .uri("/auth/login")
                    .body(dto)
                    .retrieve()
                    .toEntity(String.class)
            );
        } catch (HttpClientErrorException e) {
            System.err.println("catch login");
            return (ResponseEntity
                    .status(e.getStatusCode())
                    .body(e.getResponseBodyAsString())
            );
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> myPageData(@RequestHeader("Authorization") String jwt) {
        try {
            return restClient
                    .get()
                    .uri("/api/customers/info")
                    .header("Authorization", jwt)
                    .retrieve()
                    .toEntity(service.booking.dto.CustomerInfo.class);
        } catch (HttpClientErrorException e) {
            return (ResponseEntity
                    .status(e.getStatusCode())
                    .body(e.getResponseBodyAsString())
            );
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCustomerInfo(@RequestHeader("Authorization") String jwt,
                                                @RequestBody UpdateDto update) {
        try {
            return restClient
                    .post()
                    .uri("/api/customers/update")
                    .header("Authorization", jwt)
                    .body(update)
                    .retrieve()
                    .toEntity(Object.class);
        } catch (HttpClientErrorException e) {
            System.err.println("catch update");
            return (ResponseEntity
                    .status(e.getStatusCode())
                    .body(e.getResponseBodyAsString())
            );
        }
    }
}
