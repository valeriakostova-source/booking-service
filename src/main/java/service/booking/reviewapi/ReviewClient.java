package service.booking.reviewapi;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import service.booking.customerapi.dto.ReviewResponseDto;

import java.util.List;

@Component
public class ReviewClient {

    private final RestClient restClient;

    public ReviewClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://review-service:8083")
                .build();
    }

    public String test() {
        return restClient.get()
                .uri("/test")
                .header("Authorization", "Bearer " + )
                .retrieve()
                .body(String.class);
    }

    public List<ReviewResponseDto> getAllReviews(Authentication  auth) {
        return restClient.get()
                .uri("/reviews")
                .header("Authorize", "Bearer "+auth.)
                .retrieve()
                .body(List.class);
    }
}
