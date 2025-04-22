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
        // Bắt đầu 2 cuộc gọi bất đồng bộ đến Inventory và Shipping service
        CompletableFuture<String> inventoryResponse = inventoryClient.decreaseStock("product123", 1);
        String paymentId = UUID.randomUUID().toString();
        CompletableFuture<String> shippingResponse = shippingClient.createShipping(paymentId);

        // Khi cả 2 cuộc gọi đều hoàn thành, trả về kết quả
        return CompletableFuture.allOf(inventoryResponse, shippingResponse)
                .thenApplyAsync(v -> {
                    try {
                        // Chờ kết quả từ cả Inventory và Shipping
                        String inventoryResult = inventoryResponse.get();
                        String shippingResult = shippingResponse.get();

                        // Trả về ResponseEntity khi tất cả hoàn thành
                        return ResponseEntity.ok("Payment successful with ID: " + paymentId + ". " + inventoryResult + ". " + shippingResult);
                    } catch (Exception e) {
                        return ResponseEntity.status(500).body("Error during payment processing: " + e.getMessage());
                    }
                });
    }

    @PutMapping("/{id}/refund")
    public CompletableFuture<ResponseEntity<String>> refundPayment(@PathVariable String id) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok("Payment " + id + " refunded."));
    }
}