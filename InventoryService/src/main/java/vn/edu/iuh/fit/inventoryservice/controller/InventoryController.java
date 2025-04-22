package vn.edu.iuh.fit.inventoryservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @PostMapping("/decrease")
    public ResponseEntity<String> decreaseStock(@RequestParam String productId, @RequestParam int quantity) {
        // Trả về ResponseEntity trực tiếp, không cần CompletableFuture
        return ResponseEntity.ok("Decreased stock for product " + productId + " by " + quantity);
    }

    @PostMapping("/increase")
    public ResponseEntity<String> increaseStock(@RequestParam String productId, @RequestParam int quantity) {
        // Trả về ResponseEntity trực tiếp, không cần CompletableFuture
        return ResponseEntity.ok("Increased stock for product " + productId + " by " + quantity);
    }
}