package com.zhant.entregasGestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhant.entregasGestor.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
