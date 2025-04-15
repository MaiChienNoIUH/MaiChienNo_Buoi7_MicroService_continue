package vn.edu.iuh.fit.paymentservice.controller;

import vn.edu.iuh.fit.paymentservice.client.InventoryClient;
import vn.edu.iuh.fit.paymentservice.client.ShippingClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final InventoryClient inventoryClient;
    private final ShippingClient shippingClient;

    public PaymentController(InventoryClient inventoryClient, ShippingClient shippingClient) {
        this.inventoryClient = inventoryClient;
        this.shippingClient = shippingClient;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<String>> makePayment() {
        return CompletableFuture.supplyAsync(() -> {
            String inventoryResponse = inventoryClient.decreaseStock("product123", 1);
            String paymentId = UUID.randomUUID().toString();
            String shippingResponse = shippingClient.createShipping(paymentId);
            return ResponseEntity.ok("Payment successful with ID: " + paymentId + ". " + inventoryResponse + ". " + shippingResponse);
        });
    }

    @PutMapping("/{id}/refund")
    public CompletableFuture<ResponseEntity<String>> refundPayment(@PathVariable String id) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok("Payment " + id + " refunded."));
    }
}