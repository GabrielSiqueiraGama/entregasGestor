package com.zhant.entregasGestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Courier;
import com.zhant.entregasGestor.models.Vehicle;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer>{
	List<Entrega> findByVehicle(Vehicle vehicle);
	List<Entrega> findByCourier(Courier courier);
	List<Entrega> findByBairro(String bairro);
	List<Entrega> findByNomeCliente(String nomeCliente);
	List<Entrega> findByNota(int nota);
}
