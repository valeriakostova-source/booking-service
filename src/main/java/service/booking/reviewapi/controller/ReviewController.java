package service.booking.reviewapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import service.booking.reviewapi.client.ReviewClient;
import service.booking.reviewapi.dto.ReviewResponseDto;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewClient reviewClient;
    public ReviewController(ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
    }

    @GetMapping("/reviews/user")
    public List<ReviewResponseDto> getReviewsFromUser(@RequestHeader("Authorization") String token) {
        return reviewClient.getReviewsFromUserId(token);
    }

}
