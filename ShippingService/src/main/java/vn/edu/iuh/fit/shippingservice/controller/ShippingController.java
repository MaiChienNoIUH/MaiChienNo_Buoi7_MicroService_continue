package vn.edu.iuh.fit.shippingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> createShipping(@RequestParam String orderId) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok("Shipping created for order: " + orderId));
    }

    @PutMapping("/{orderId}/status")
    public CompletableFuture<ResponseEntity<String>> updateStatus(@PathVariable String orderId, @RequestParam String status) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok("Updated shipping status for order " + orderId + " to " + status));
    }
}

