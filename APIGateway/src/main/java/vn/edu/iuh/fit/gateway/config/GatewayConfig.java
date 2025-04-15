package vn.edu.iuh.fit.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class named {@link GatewayConfig} for setting up API Gateway routes.
 */
@Configuration
public class GatewayConfig {


    /**
     * Configures the route locator to define the routing rules for the gateway.
     *
     * @param builder The RouteLocatorBuilder used to build the RouteLocator.
     * @return A RouteLocator with the defined routes.
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("PaymentService", r -> r.path("/api/payments/**")
                        .uri("lb://PaymentService"))
                .route("InventoryService", r -> r.path("/api/inventory/**")
                        .uri("lb://InventoryService"))
                .route("ShippingService", r -> r.path("/api/shipping/**")
                        .uri("lb://ShippingService"))
                .build();
    }
}