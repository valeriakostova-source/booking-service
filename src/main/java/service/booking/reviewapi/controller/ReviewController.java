package service.booking.reviewapi.controller;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(reviewClient.getAllReviews(token));
    }

    @GetMapping("/reviews/user")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsFromUserId(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(reviewClient.getReviewsFromUserId(token));
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDto> createNewReview(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody NewReviewDto newReviewDto) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(reviewClient.createNewReview(token, newReviewDto));

    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") Long roomNumber) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(reviewClient.getReviewById(token, roomNumber));
    }

    @GetMapping("/reviews/room/avgRating/{id}")
    public ResponseEntity<Double> getAvgRating(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int roomNumber) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(reviewClient.getAverageRating(token, roomNumber));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String>  deleteReviewById(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ResponseEntity<Void> response =
                reviewClient.deleteReviewById(token, id);

        return ResponseEntity
                .status(response.getStatusCode())
                .build();

    }

}
