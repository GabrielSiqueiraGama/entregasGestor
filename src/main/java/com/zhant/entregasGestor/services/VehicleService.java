package com.zhant.entregasGestor.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.VehicleDTO;
import com.zhant.entregasGestor.dto.mapper.VehicleMapper;
import com.zhant.entregasGestor.exceptions.RecordNotFoundException;
import com.zhant.entregasGestor.repositories.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	private VehicleMapper vehicleMapper;
	
	public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper ) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleMapper = vehicleMapper;
	}
	
	
	public List<VehicleDTO> findAll(){
		return vehicleRepository.findAll().stream().map(vehicleMapper::toDto).toList();
	}
	
	public VehicleDTO findById(int id) throws BadRequestException {
		return vehicleRepository.findById(id).map(vehicleMapper::toDto).orElseThrow(()-> new BadRequestException("Veiculo não encontrado"));
	}
	
	public VehicleDTO update(int id, VehicleDTO vehicle) throws BadRequestException {
		return vehicleRepository.findById(id).map(vehicleFunction ->{
			vehicleFunction.setName(vehicle.name());
			return vehicleRepository.save(vehicleFunction);
		}).map(vehicleMapper::toDto).orElseThrow(()-> new RecordNotFoundException(id));
	}
	
	public VehicleDTO create(VehicleDTO vehicle) {
		return vehicleMapper.toDto(vehicleRepository.save(vehicleMapper.toEntity(vehicle)));
	}
	
	
	public void delete(int id) throws BadRequestException {
		vehicleRepository.delete(vehicleRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id)));
	}
}
