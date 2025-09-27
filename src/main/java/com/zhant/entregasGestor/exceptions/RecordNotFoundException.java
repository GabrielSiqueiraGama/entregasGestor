package com.zhant.entregasGestor.exceptions;

public class RecordNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(int id) {
		super("Registro não encontrado com o id: " + id);
	}
}