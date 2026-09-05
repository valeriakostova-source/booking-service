package service.booking.reviewapi.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestClient;
import service.booking.reviewapi.dto.ReviewResponseDto;

import java.util.List;

@Component
public class ReviewClient {

    private final RestClient restClient;

    public ReviewClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://review-service:8083")
                .build();
    }

    public String test(String token) {
        return restClient.get()
                .uri("/test")
                .header("Authorization", formatBearerToken(token))
                .retrieve()
                .body(String.class);
    }

    public List<ReviewResponseDto> getAllReviews(String token) {

        return restClient.get()
                .uri("/reviews")
                .header("Authorization", formatBearerToken(token))
                .retrieve()
                .body(new ParameterizedTypeReference<List<ReviewResponseDto>>() {});
    }

    public List<ReviewResponseDto> getUserReviewsById(String authHeader) {
        return restClient.get()
                .uri("/reviews/user")
                .header("Authorization", formatBearerToken(authHeader))
                .retrieve()
                .body(new ParameterizedTypeReference<List<ReviewResponseDto>>() {});
    }




    //Filter Token so it doesnt become "Bearer Bearer <token>"
    private String formatBearerToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token;
        }
        return "Bearer " + token;
    }
}
