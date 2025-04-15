package vn.edu.iuh.fit.inventoryservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @PostMapping("/decrease")
    public CompletableFuture<ResponseEntity<String>> decreaseStock(@RequestParam String productId, @RequestParam int quantity) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok("Decreased stock for product " + productId + " by " + quantity));
    }

    @PostMapping("/increase")
    public CompletableFuture<ResponseEntity<String>> increaseStock(@RequestParam String productId, @RequestParam int quantity) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok("Increased stock for product " + productId + " by " + quantity));
    }
}