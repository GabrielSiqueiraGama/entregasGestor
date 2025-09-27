package com.zhant.entregasGestor.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhant.entregasGestor.dto.CourierDTO;
import com.zhant.entregasGestor.dto.mapper.CourierMapper;
import com.zhant.entregasGestor.exceptions.RecordNotFoundException;
import com.zhant.entregasGestor.repositories.CourierRepository;

@Service
public class CourierService {

	@Autowired
	private CourierRepository courierRepository;
	private CourierMapper courierMapper;
	
	public CourierService(CourierRepository courierRepository, CourierMapper courierMapper) {
		this.courierRepository = courierRepository;
		this.courierMapper = courierMapper;
	}
	
	public List<CourierDTO> findAll() {
		return courierRepository.findAll().stream().map(courierMapper::toDto).toList();
	}
	
	public CourierDTO create(CourierDTO courier) {
		return courierMapper.toDto(courierRepository.save(courierMapper.toEntity(courier)));
	}
	
	public CourierDTO findById(int id) throws BadRequestException {
		return courierRepository.findById(id).map(courierMapper::toDto).orElseThrow(()-> new RecordNotFoundException(id));
	}
	
	public CourierDTO update(int id, CourierDTO courier) throws BadRequestException {
		return courierRepository.findById(id).map(courierFunction->{
			courierFunction.setName(courier.name());
			return courierRepository.save(courierFunction);
		}).map(courierMapper::toDto).orElseThrow(()-> new BadRequestException("Entregador não encontrado"));
	}
	
	public void delete(int id) throws BadRequestException {
		courierRepository.delete(courierRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id)));
	}
}
