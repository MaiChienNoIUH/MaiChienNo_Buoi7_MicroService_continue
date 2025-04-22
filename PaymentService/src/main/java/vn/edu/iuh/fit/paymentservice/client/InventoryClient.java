package vn.edu.iuh.fit.paymentservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "InventoryService", url = "http://localhost:9002")
public interface InventoryClient {

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallback")
    @Retry(name = "inventory")
    @RateLimiter(name = "inventory")
    @TimeLimiter(name = "inventory")
    @PostMapping("/api/inventory/decrease")
    CompletableFuture<String> decreaseStock(@RequestParam String productId, @RequestParam int quantity);

    default CompletableFuture<String> fallback(String productId, int quantity, Throwable ex) {
        return CompletableFuture.completedFuture("Inventory service fallback: " + ex.getMessage());
    }
}
