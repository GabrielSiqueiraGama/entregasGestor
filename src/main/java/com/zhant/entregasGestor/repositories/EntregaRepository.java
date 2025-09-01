package com.zhant.entregasGestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zhant.entregasGestor.dto.EntregaDTO;
import com.zhant.entregasGestor.models.Entrega;
import com.zhant.entregasGestor.models.Entregador;
import com.zhant.entregasGestor.models.Veiculo;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer>{
	List<EntregaDTO> findByVeiculo(Veiculo veiculo);
	List<EntregaDTO> findByEntregador(Entregador entregador);
	List<EntregaDTO> findByBairro(String bairro);
	List<EntregaDTO> findByNomeCliente(String nomeCliente);
	List<EntregaDTO> findByNota(int nota);
}
