package com.zhant.entregasGestor.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zhant.entregasGestor.dto.VehicleDTO;
import com.zhant.entregasGestor.services.VehicleService;

@Validated
@RestController
@Tag(name = "Vehicle Module")
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "403", description = "User does not have permission"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@RequestMapping("/api/vehicles")
public class VehicleController {

	private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }
	
	@GetMapping
    @Operation(summary = "Listing All Vehicles")
    @ApiResponse(responseCode = "200", description = "Success")
	public List<VehicleDTO> findVehicles() {
		return vehicleService.findAll();
	}
	
	@GetMapping("/{id}")
    @Operation(summary = "Bring Vehicles by the id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public VehicleDTO findVehicleById(@PathVariable int id) throws BadRequestException {
		return vehicleService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Create a new Vehicle")
    @ApiResponse(responseCode = "201", description = "Created")
	public VehicleDTO create( @Valid @RequestBody VehicleDTO vehicle) {
		return vehicleService.create(vehicle);
	}
	
	@PutMapping("/{id}")
    @Operation(summary = "Update Vehicle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public VehicleDTO update(@PathVariable int id,@Valid @RequestBody VehicleDTO vehicle) throws BadRequestException {
		return vehicleService.update(id, vehicle);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Vehicle by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public void delete(@PathVariable int id) throws BadRequestException {
		vehicleService.delete(id);
	}
}
