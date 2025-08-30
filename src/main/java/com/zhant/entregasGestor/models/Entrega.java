package com.zhant.entregasGestor.models;

import java.time.LocalDateTime;

import com.zhant.entregasGestor.enums.StatusEntrega;

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
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime data;
	
	private String nomeCliente;
	
	private String bairro;
	
	private String valor;
	
	private String troco;
	
	private boolean fragil;
	
	private int nota;
	
	@ManyToOne
	@JoinColumn(name = "entregador_id")
	private Entregador entregador;
	
	@ManyToOne
	@JoinColumn(name = "veiculo_id") 
	private Veiculo veiculo;
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	public Entrega(LocalDateTime data, String nomeCliente, String bairro, String valor, String troco, boolean fragil,
			int nota, Entregador entregador, Veiculo veiculo, StatusEntrega status) {
		this.data = data;
		this.nomeCliente = nomeCliente;
		this.bairro = bairro;
		this.valor = valor;
		this.troco = troco;
		this.fragil = fragil;
		this.nota = nota;
		this.entregador = entregador;
		this.veiculo = veiculo;
		this.status = status;
	}

	public Entrega() {
	}
	
	
	
	
}
