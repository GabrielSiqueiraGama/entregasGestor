package com.zhant.entregasGestor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.services.EntregaService;

public class EntregaController {

	@Autowired
	EntregaService entregaService;
	
	public List<Entrega> findEntregas(){
		return entregaService.findAll();
	}
	
}
