package org.scalke.gatewayservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.support.RouterFunctionMapping;

import java.util.function.Function;


@Slf4j
@Configuration
public class JobRoutingConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, RouterFunctionMapping routerFunctionMapping) {

        return builder.routes()
                .route("test", p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyValue")
                                .addRequestParameter("MyParam", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route("store_service", p -> p.path("/api/store/**")
                        .uri("lb://store-service"))
                .route("user_service", p -> p.path("/api/users/**")
                        .uri("lb://user-service"))
                .route("product_service", p -> p.path("/api/products/**")
                        .uri("lb://products-service"))
                .build();
    }
}
