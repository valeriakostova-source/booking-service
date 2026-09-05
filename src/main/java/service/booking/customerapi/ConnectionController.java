package service.booking.customerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import service.booking.dto.CreateCustomerRequest;

@RestController
@RequestMapping("/connect")
public class ConnectionController {
    private final RestClient restClient;

    public ConnectionController() {
        this.restClient = RestClient.builder()
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
}
