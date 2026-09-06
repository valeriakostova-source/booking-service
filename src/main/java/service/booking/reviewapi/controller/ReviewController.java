package service.booking.reviewapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.booking.reviewapi.client.ReviewClient;
import service.booking.reviewapi.dto.NewReviewDto;
import service.booking.reviewapi.dto.ReviewResponseDto;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewClient reviewClient;

    public ReviewController(ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
    }

    @GetMapping("/reviews")
    public List<ReviewResponseDto> getAllReviews(@RequestHeader("Authorization") String authHeader) {
        return reviewClient.getAllReviews(authHeader);
    }

    @GetMapping("/reviews/user")
    public List<ReviewResponseDto> getReviewsFromUser(@RequestHeader("Authorization") String token) {
        return reviewClient.getReviewsFromUserId(token);
    }

    @PostMapping("/reviews")
    public ReviewResponseDto createNewReview(@RequestHeader("Authorization") String token, @RequestBody NewReviewDto newReviewDto) {
        return reviewClient.createNewReview(token, newReviewDto);
    }

    @GetMapping("/reviews/{id}")
    public ReviewResponseDto getReviewById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return reviewClient.getReviewById(token, id);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String>  deleteReviewById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        boolean result = reviewClient.deleteReviewById(token, id);
        if (result) {
            return ResponseEntity.ok("Review has been deleted");
        }else  {
            return ResponseEntity.notFound().build();
        }

    }

}
