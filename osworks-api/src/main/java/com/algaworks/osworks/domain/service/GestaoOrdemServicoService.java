package com.algaworks.osworks.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		/*
		 * Assim que colocar este metodo, o Postman retornara o codigo
		 * 404 e mostrará a mensagem ao usuário sobre: "Cliente não encontrado",
		 * caso dê certo há um cliente com o id correto, então será possível
		 * mostrar dados do cliente como o nome, antes desse método não mostrava
		 * o nome e nem outros dados além ao id e dos dados que recém colocou no
		 * ordens-servico 
		 */
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		//Aqui que é para colocar os dados do cliente impresso na tela de ordens-servico
		ordemServico.setCliente(cliente);
		
		//Estes metodos sao para setar padrão cada inserção.
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
}
