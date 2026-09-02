package service.booking.customerapi;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomerClient {

    private final RestClient restClient;

    public CustomerClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://customer-service:8081/customer")
                .build();
    }

        public boolean customerExists(Long customerId) {

        //ToDo After customer service will be done check if url is correct

        Boolean exists = restClient.get()
                .uri("/api/customers/{customerId}/exists", customerId)
                .retrieve()
                .body(Boolean.class);

        return Boolean.TRUE.equals(exists);
    }
}