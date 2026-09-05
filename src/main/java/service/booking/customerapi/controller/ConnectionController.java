package service.booking.customerapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.booking.customerapi.CustomerClient;
import service.booking.customerapi.dto.CreateCustomerRequest;
@RestController
@RequestMapping("/connect")
public class ConnectionController {
    private final CustomerClient customerClient;

    public ConnectionController(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCustomerRequest request)
    {
        System.err.println("\nNår ConnectionController\n");
        return customerClient.create(request);

    }
}
