package com.zhant.entregasGestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Entregador;
import com.zhant.entregasGestor.models.Veiculo;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer>{
	List<Entrega> findByVeiculo(Veiculo veiculo);
	List<Entrega> findByEntregador(Entregador entregador);
	List<Entrega> findByBairro(String bairro);
	List<Entrega> findByNomeCliente(String nomeCliente);
	List<Entrega> findByNota(int nota);
}
