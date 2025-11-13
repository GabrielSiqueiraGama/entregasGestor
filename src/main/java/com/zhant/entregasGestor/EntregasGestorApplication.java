package com.zhant.entregasGestor;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(title = "Entregas Gestor API", description = "Delivery management system that allows users to register, authenticate, and manage logistics operations. "
                + "Users must log in to obtain a token in order to perform actions. "
                + "The system manages vehicles, couriers (delivery personnel), and deliveries, ensuring that each delivery is associated with a vehicle and a courier."
        ),
        tags = {
                @Tag(name = "Register & Login", description = "Auth's Endpoints"),
                @Tag(name = "Vehicle Module", description = "Vehicle's Endpoints"),
                @Tag(name = "Courier Module", description = "Courier's Endpoints"),
                @Tag(name = "Delivery Module", description = "Delivery's Endpoints"),
        }
)

@SpringBootApplication
public class EntregasGestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregasGestorApplication.class, args);
	}

}
