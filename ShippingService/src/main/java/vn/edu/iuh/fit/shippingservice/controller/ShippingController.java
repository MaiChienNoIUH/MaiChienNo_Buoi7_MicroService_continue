package vn.edu.iuh.fit.shippingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    @PostMapping("/create")
    public ResponseEntity<String> createShipping(@RequestParam String orderId) {
        // Trả về ResponseEntity trực tiếp, không cần CompletableFuture
        return ResponseEntity.ok("Shipping created for order: " + orderId);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateStatus(@PathVariable String orderId, @RequestParam String status) {
        // Trả về ResponseEntity trực tiếp, không cần CompletableFuture
        return ResponseEntity.ok("Updated shipping status for order " + orderId + " to " + status);
    }
}

