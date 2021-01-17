package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	//EntityManeger eh responsavel pelas operacoes nas entidades, eh uma interface do JakartaPersistence
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	//O Autowired indica que quer uma instancia da interface ClienteRepository 
	@Autowired
	private ClienteRepository clienteRepository;
	
	//@GetMapping("/clientes")
	@GetMapping
	public List<Cliente> listar() {
		//Cliente cliente = new Cliente();
		/*var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("João");
		cliente1.setTelefone("34 99999-1111");
		cliente1.setEmail("joaodascouves@algaworks.com");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setTelefone("11 77777-1111");
		cliente2.setEmail("mariadasilva@algaworks.com");
		return Arrays.asList(cliente1, cliente2);*/
		
		/*
		 * Criou uma consulta, utilizando o JPQL que eh semelhante ao SQL, porem
		 * eh do JPA (Jakarta Persistence), tambem tipou a consulta para retornar
		 * uma lista do tipo Cliente, automaticamente no metodo getResultList().
		 */
		//return manager.createQuery("from Cliente", Cliente.class).getResultList();
		
		/*
		 * Apos ter criado uma interface ClienteRepository, ele nao precisou mais
		 * de fazer iteracao direta com o Banco de Dados
		 */
		return clienteRepository.findAll();
		//return clienteRepository.findByNome("João da Silva");
	}
	
	//@GetMapping("/clientes/{clienteId}")
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		//return cliente.orElse(null); --Nao usa mais esse porque quando houver erro o codigo permaneceria 200 ao inves de 404
		if (cliente.isPresent()) {
			//Aqui indica que retorna 200 no Postman com os dados do cliente
			return ResponseEntity.ok(cliente.get());
		}
		
		//Aqui retorna o erro, caso nao tenha.
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente);
		//no lugar de utilizar uma classe nativa do JAVA, foi implementado uma classe que eh especialista em representar regras de negocio
		return cadastroCliente.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable long clienteId,
			@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		/*
		 * Para atualizar precisamos de setId, porque se ele entender que o 
		 * id estah nulo, ele compreenderia que precisaria fazer uma nova intancia 
		 */
		cliente.setId(clienteId);
		//cliente = clienteRepository.save(cliente);
		cliente = cadastroCliente.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		//clienteRepository.deleteById(clienteId);
		cadastroCliente.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
}
