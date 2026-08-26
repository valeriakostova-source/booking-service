package service.booking.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("customerId");
        if (id == null) {
            return "login";
        }
//        try {
//            CustomerInfoRequest customer = pageService.getCustomer(id);
//            model.addAttribute("customer", customer);
//            return "my_page";
//        } catch (NotFoundException e) {
//            session.invalidate();
            return "login";
//        }
    }

    @GetMapping("/reservation")
    public String reservation(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("customerId");
        if (id == null) {
            return "login";
        }
        model.addAttribute("message", "Here you can make reservation");
        return "reservation";
    }

    @GetMapping("/updateCustomer")
    public String editCustomerPage() {
        return "update_customer";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount() {
        return "delete_account";
    }

    @GetMapping("/myReservations")
    public String myReservations(HttpSession session) {
        Long id = (Long) session.getAttribute("customerId");

        if (id == null) {
            return "login";
        }
        return "my_reservation";

    }
}
