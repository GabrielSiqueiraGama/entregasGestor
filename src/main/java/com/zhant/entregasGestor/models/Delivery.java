package com.zhant.entregasGestor.models;

import java.time.LocalDateTime;

import com.zhant.entregasGestor.enums.DeliveryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime orderDate;
	
	private String customerName;
	
	private String neighborhood;
	
	private String amount;
	
	private String change;
	
	private boolean fragile;
	
	private int noteCode;
	
	@ManyToOne
	@JoinColumn(name = "courier_id")
	private Courier courier;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id") 
	private Vehicle vehicle;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status = DeliveryStatus.EM_ANDAMENTO;

	public Delivery(LocalDateTime orderDate, String customerName, String neighborhood, String amount, String change, boolean fragile,
			int noteCode, Courier courier, Vehicle vehicle, DeliveryStatus status) {
		this.orderDate = orderDate;
		this.customerName = customerName;
		this.neighborhood = neighborhood;
		this.amount = amount;
		this.change = change;
		this.fragile = fragile;
		this.noteCode = noteCode;
		this.courier = courier;
		this.vehicle = vehicle;
		this.status = status;
	}

	public Delivery() {
	}
	
	
	
	
}
