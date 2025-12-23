package com.zhant.entregasGestor.models;

import java.time.LocalDateTime;

import com.zhant.entregasGestor.dto.DeliveryTestDTO;
import com.zhant.entregasGestor.enums.DeliveryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime orderDate;
	
	private String customerName;
	
	private String neighborhood;
	
	private String amount;
	
	private String cashChange ;
	
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

	public Delivery(LocalDateTime orderDate, String customerName, String neighborhood, String amount, String cashChange , boolean fragile,
			int noteCode, Courier courier, Vehicle vehicle, DeliveryStatus status) {
		this.orderDate = orderDate;
		this.customerName = customerName;
		this.neighborhood = neighborhood;
		this.amount = amount;
		this.cashChange  = cashChange ;
		this.fragile = fragile;
		this.noteCode = noteCode;
		this.courier = courier;
		this.vehicle = vehicle;
		this.status = status;
	}
	
	public Delivery(DeliveryTestDTO data) {
		this.orderDate = data.orderDate();
		this.customerName = data.customerName();
		this.neighborhood = data.neighborhood();
		this.amount = data.amount();
		this.cashChange  = data.cashChange();
		this.fragile = data.fragile();
		this.noteCode = data.noteCode();
		this.courier = data.courierId();
		this.vehicle = data.vehicleId();
		this.status = data.status();
	}

	public Delivery() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCashChange() {
        return cashChange;
    }

    public void setCashChange(String cashChange) {
        this.cashChange = cashChange;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public int getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(int noteCode) {
        this.noteCode = noteCode;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
