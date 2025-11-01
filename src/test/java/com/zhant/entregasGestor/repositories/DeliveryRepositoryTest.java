package com.zhant.entregasGestor.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import com.zhant.entregasGestor.dto.DeliveryTestDTO;
import com.zhant.entregasGestor.enums.DeliveryStatus;
import com.zhant.entregasGestor.models.Courier;
import com.zhant.entregasGestor.models.Delivery;
import com.zhant.entregasGestor.models.Vehicle;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DeliveryRepositoryTest {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	DeliveryRepository deliveryRepository;

	@Test
	void testFindById() {
	}
	
	@Test
	@DisplayName("Should return success when finding by vehicle")
	void findByVehicle() {
		Vehicle vehicle = new Vehicle(0, "Truck");
		Courier courier = new Courier(0, "Pedro");
		DeliveryTestDTO delivery = new DeliveryTestDTO(0, LocalDateTime.now(), "Ribeirinho", "Downtown", "50.00", "0", false, 123, DeliveryStatus.EM_ANDAMENTO, courier, vehicle);
		
		this.createCourier(courier);
		this.createVehicle(vehicle);
		this.createDelivery(delivery);
		
		entityManager.flush();
		entityManager.clear();
		
		List<Delivery> foundedDelivery = this.deliveryRepository.findByVehicle(vehicle);
		assertThat(foundedDelivery).isNotEmpty();
		assertThat(foundedDelivery.get(0).getVehicle().getName()).isEqualTo(vehicle.getName());
	}
	
	@Test
	@DisplayName("Should return sucess to find by courier")
	void findByCourier() {
	}
	
	@Test
	@DisplayName("Should return sucess to find by neighborhood")
	void findByNeighborhood() {
	}
	
	@Test
	@DisplayName("Should return sucess to find by costumerName")
	void findByCustomerName() {
	}
	
	@Test
	@DisplayName("Should return sucess to find by noteCode")
	void findByNoteCode() {
	}	
	
	
	private Delivery createDelivery(DeliveryTestDTO data){
		Delivery newDelivery = new Delivery(data);
		this.entityManager.persist(newDelivery);
		return newDelivery;
	}
	
	private Courier createCourier(Courier data){
		this.entityManager.persist(data);
		return data;
	}
	
	private Vehicle createVehicle(Vehicle data){
		this.entityManager.persist(data);
		return data;
	}
	
	//List<Delivery> findByVehicle(Vehicle vehicle);
	//List<Delivery> findByCourier(Courier courier);
	//List<Delivery> findByNeighborhood(String neighborhood);
	//List<Delivery> findByCustomerName(String customerName);
	//List<Delivery> findByNoteCode(int noteCode);
}
