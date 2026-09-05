package service.booking.customerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import service.booking.customerapi.dto.CreateCustomerRequest;
import service.booking.customerapi.dto.CustomerResponseDto;

@Component
public class CustomerClient {

    private final RestClient restClient;

    public CustomerClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://customer-service:8081")
                .build();
    }

//    public boolean customerExists(String jwt) {
//        //ToDo After customer service will be done check if url is correct
//
//        Boolean exists = restClient.get()
//                .uri("/api/customers/exists")
//                .header("Authorization", jwt)
//                .retrieve()
//                .body(Boolean.class);
//
//        return Boolean.TRUE.equals(exists);
//    }

    public ResponseEntity<?> create(CreateCustomerRequest request) {
        System.err.println("\nnår CustomerClient\n");
        CustomerResponseDto response = restClient.post()
                .uri("/api/customers/create")
                .body(request)
                .retrieve()
                .body(CustomerResponseDto.class);
        return ResponseEntity.ok(response);
    }
}
