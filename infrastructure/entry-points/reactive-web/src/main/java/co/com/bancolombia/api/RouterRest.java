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
    public RouterFunction<ServerResponse> routes(Handler handler) {
        return RouterFunctions
                // 1. Agregar una nueva franquicia
                .route(POST("/api/franchises").and(contentType(MediaType.APPLICATION_JSON)), handler::createFranchise)

                // 2. Agregar una nueva sucursal a una franquicia
                .andRoute(POST("/api/franchises/{franchiseId}/branches").and(contentType(MediaType.APPLICATION_JSON)), handler::createBranch)

                // 3. Agregar un nuevo producto a una sucursal
                .andRoute(POST("/api/branches/{branchId}/products").and(contentType(MediaType.APPLICATION_JSON)), handler::createProduct)

                // 4. Eliminar un producto de una sucursal
                .andRoute(DELETE("/api/branches/{branchId}/products/{productId}"), handler::deleteProduct)

                // 5. Modificar el stock de un producto
                .andRoute(PUT("/api/branches/{branchId}/products/{productId}/stock").and(contentType(MediaType.APPLICATION_JSON)), handler::updateProductStock)

                // 6. Producto con más stock por sucursal para una franquicia puntual
                .andRoute(GET("/api/franchises/{franchiseId}/branches/products/max-stock").and(accept(MediaType.APPLICATION_JSON)), handler::getMaxStockProducts);
    }
}
