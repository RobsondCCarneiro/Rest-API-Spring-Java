package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.OrdemServicoInput;
import com.algaworks.osworks.api.model.OrdemServicoModel;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		//return gestaoOrdemServico.criar(ordemServico);
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		
		return toModel(gestaoOrdemServico.criar(ordemServico));
	}
	
	@GetMapping
	public List<OrdemServico> lista(){
		return ordemServicoRepository.findAll();
	}
	
	/*
	 * Aqui serve para escolher no endereco http, o id da ordem de servico
	 */
	@GetMapping("/{ordemServicoId}")
	/*
	 * Antes de criar um Representation Model na API, o Domain Model representava simultaneamente
	 * tanto o Domain (dentro do pacote domain) tanto o representation (que aqui está 
	 * dentro do pacote api), isto não é uma boa prática de programação, quando compartilha
	 * a mesma classe entre o domain model e o representation model, tem alguns problemas para
	 * resolver, por exemplo, pode existir alguma propriedade na entidade no domain que 
	 * não queremos expor na api. Qualquer alteração em alguma entidade, automaticamente no
	 * representation que é o recurso da api é alterado, assim fica melhor utilizar separados.
	 */
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if(ordemServico.isPresent()) {
			//return ResponseEntity.ok(ordemServico.get());
			/*
			 * Depois que eu baixei a dependência de modelMapper, ele faz todas a conversão
			 * de todas as entidades de OrdemServico para a OrderServicoModel, sem eu
			 * precisa instancia um fazer um get de uma em uma entidade.
			 */
			//OrdemServicoModel model = modelMapper.map(ordemServico.get(), OrdemServicoModel.class);
			OrdemServicoModel model = toModel(ordemServico.get());
			return ResponseEntity.ok(model);
		}
		return ResponseEntity.notFound().build();
	}
	//Método criado para reaproveitamente de código, caso eu precise, uma boa prática de programação
	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}
	
	//O metodo listar() é uma coleção (lista) de ordem de serviço, de modo que é necessário esta converção
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico){
		/*
		 * stream() é um fluxo (sequência de elementos)
		 * map() aplica a função um a um, ou seja ele insere no toModel que retornará o tipo OrdemServicoModel
		 * collect() reduz o stream para uma coleção que no nosso caso é uma lista
		 */
		return ordensServico.stream()
				.map(OrdemServico -> toModel(OrdemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
}
