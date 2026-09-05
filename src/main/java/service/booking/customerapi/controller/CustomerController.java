package service.booking.customerapi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import service.booking.customerapi.client.CustomerClient;

@RestController
public class CustomerController {

    private CustomerClient customerClient;
    public CustomerController(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @GetMapping("/customer/exists")
    public Boolean existsCustomer(@RequestHeader("Authorization") String token, @AuthenticationPrincipal Long userId) {
        return customerClient.customerExists(token, userId);
    }

}
