package com.zhant.entregasGestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zhant.entregasGestor.models.Delivery;
import com.zhant.entregasGestor.models.Courier;
import com.zhant.entregasGestor.models.Vehicle;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{
	List<Delivery> findByVehicle(Vehicle vehicle);
	List<Delivery> findByCourier(Courier courier);
	List<Delivery> findByNeighborhood(String neighborhood);
	List<Delivery> findByCustomerName(String customerName);
	List<Delivery> findByNoteCode(int noteCode);
}
