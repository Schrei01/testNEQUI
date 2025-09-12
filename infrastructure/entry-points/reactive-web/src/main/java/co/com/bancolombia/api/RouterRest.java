package co.com.bancolombia.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return RouterFunctions
                .route(GET("/api/products").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(GET("/api/products/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getById)
                .andRoute(POST("/api/products").and(contentType(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(PUT("/api/products/{id}").and(contentType(MediaType.APPLICATION_JSON)), handler::update)
                .andRoute(DELETE("/api/products/{id}"), handler::delete);
    }
}
