package com.zhant.entregasGestor.controllers;

import java.util.List;
import java.util.Map;

import com.zhant.entregasGestor.services.DeliveryAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhant.entregasGestor.dto.AnalyticsDTO;
import com.zhant.entregasGestor.dto.DeliveryDTO;
import com.zhant.entregasGestor.services.DeliveryService;

import jakarta.validation.Valid;

@Validated
@RestController
@Tag(name = "Delivery Module")
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "403", description = "User does not have permission"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@RequestMapping("/api/deliveries")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;
    @Autowired
    DeliveryAnalyticsService deliveryAnalyticsService;
	
	@GetMapping
    @Operation(summary = "Bring All Deliveries")
    @ApiResponse(responseCode = "200", description = "Success")
	public List<DeliveryDTO> findDeliveries(){
		return deliveryService.findAll();
	}

	@GetMapping("/{id}")
    @Operation(summary = "Bring Deliveries by the id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public DeliveryDTO findDeliveriesById(@PathVariable int id) throws BadRequestException{
		return deliveryService.findById(id);
	}
	
	@GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Bring Deliveries by the Vehicle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public List<DeliveryDTO> findDeliveriesByVehicle(@PathVariable int vehicleId) throws BadRequestException{
		return deliveryService.findByVehicle(vehicleId);
	}
	
	@GetMapping("/courier/{courierId}")
    @Operation(summary = "Bring Deliveries by the Courier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public List<DeliveryDTO> findDeliveriesByCourier(@PathVariable int courierId) throws BadRequestException{
		return deliveryService.findByCourier(courierId);
	}
	
	@GetMapping("/neighborhood/{neighborhood}")
    @Operation(summary = "Bring Deliveries by the neighborhood")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public List<DeliveryDTO> findDeliveriesByNeighborhood(@PathVariable String neighborhood){
		return deliveryService.findDeliveriesByNeighborhood(neighborhood);
	}
	
	@GetMapping("/customerName/{customerName}")
    @Operation(summary = "Bring Deliveries by the Name of the Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public List<DeliveryDTO> findByCustomerName(@PathVariable String customerName){
		return deliveryService.findDeliveriesByCustomerName(customerName);
	}
	
	@GetMapping("/noteCode/{noteCode}")
    @Operation(summary = "Bring Deliveries by the Note Code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public List<DeliveryDTO> findDeliveriesByNoteCode(@PathVariable int noteCode){
		return deliveryService.findDeliveriesByNoteCode(noteCode);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new Delivery")
    @ApiResponse(responseCode = "201", description = "Created")
	public DeliveryDTO create(@Valid @RequestBody DeliveryDTO delivery)  {
	    return deliveryService.create(delivery);
	}
	
	@PutMapping("/{id}")
    @Operation(summary = "Update Delivery")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public DeliveryDTO update(@PathVariable int id, @Valid @RequestBody DeliveryDTO delivery) throws BadRequestException {
		return deliveryService.update(id, delivery);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Delivery by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
	public void delete(@PathVariable int id) throws BadRequestException {
		deliveryService.delete(id);
	}

    @PatchMapping("/{id}/finishDelivery")
    @Operation(summary = "Finish a delivery")
    @ApiResponse(responseCode = "200", description = "Success")
    public DeliveryDTO finishDelivery(@PathVariable int id) throws BadRequestException {
        return deliveryService.finishDelivery(id);
    }

    @GetMapping("/analytics/countByNeighborhood")
    @Operation(summary = "Get delivery count grouped by Neighborhood")
    @ApiResponse(responseCode = "200", description = "Success")
    public Map<String, Integer> getDeliveriesCountByNeighborhood(){
        return deliveryAnalyticsService.getDeliveryCountByNeighborhood();
    }

    @GetMapping("/analytics/percentageByNeighborhood")
    @Operation(summary = "Get delivery percentage grouped by Neighborhood")
    @ApiResponse(responseCode = "200", description = "Success")
    public Map<String, AnalyticsDTO> getPercentageDeliveryCountByNeighborhood(){
        return deliveryAnalyticsService.getPercentageDeliveryCountByNeighborhood();
    }

    
    @GetMapping("/analytics/countByCourier")
    @Operation(summary = "Get delivery count grouped by Couriers")
    @ApiResponse(responseCode = "200", description = "Success")
    public Map<String, Integer> getDeliveryCountByCourier(){
        return deliveryAnalyticsService.getDeliveryCountByCourier();
    }

    @GetMapping("/analytics/countByVehicle")
    @Operation(summary = "Get delivery count grouped by Vehicle")
    @ApiResponse(responseCode = "200", description = "Success")
    public Map<String, Integer> getDeliveryCountByVehicle(){
        return deliveryAnalyticsService.getDeliveryCountByVehicle();
    }
}
