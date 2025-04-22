package vn.edu.iuh.fit.paymentservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "ShippingService", url = "http://localhost:9003")
public interface ShippingClient {

    @CircuitBreaker(name = "shipping", fallbackMethod = "fallback")
    @Retry(name = "shipping")
    @RateLimiter(name = "shipping")
    @TimeLimiter(name = "shipping")
    @PostMapping("/api/shipping/create")
    CompletableFuture<String> createShipping(@RequestParam String orderId);

    default CompletableFuture<String> fallback(String orderId, Throwable ex) {
        return CompletableFuture.completedFuture("Shipping service fallback: " + ex.getMessage());
    }
}
