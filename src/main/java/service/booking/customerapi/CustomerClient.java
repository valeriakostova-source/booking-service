package service.booking.customerapi;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomerClient {

    private final RestClient restClient;

    public CustomerClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public boolean customerExists(String jwt) {
        //ToDo After customer service will be done check if url is correct

        Boolean exists = restClient.get()
                .uri("/api/customers/exists")
                .header("Authorization", jwt)
                .retrieve()
                .body(Boolean.class);

        return Boolean.TRUE.equals(exists);
    }
}