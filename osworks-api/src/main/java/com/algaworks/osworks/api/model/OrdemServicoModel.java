package com.algaworks.osworks.api.model;

/*
 * Representation model, é a classe com definição dos recursos.
 * Quando compartilha a mesma classe tanto no domain modo como em
 * Representation model (api), tem uns problemas na hora da manutenção,
 * como houver alguma propriedade no domain model que não queremos expor
 * na api. 
 * 
 * Por exemplo: caso eu queira que uma entidade não seja exibida (exemplo o preco)
 * no Json, assim como qualquer alteração no domain, vai causar impacto na api.
 * 
 * Esta Classe é analoga a OrdemServico no model. 
 * 
 * O ideal é isolar o domain do api, por isso que esta classe foi criada.
 */

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.osworks.domain.model.StatusOrdemServico;

public class OrdemServicoModel {

//	private Long id;
//	private String nomeCliente;
	private ClienteResumoModel cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;

//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getNomeCliente() {
//		return nomeCliente;
//	}
//	public void setNomeCliente(String nomeCliente) {
//		this.nomeCliente = nomeCliente;
//	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOrdemServico getStatus() {
		return status;
	}
	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	
	public ClienteResumoModel getCliente() {
		return cliente;
	}
	public void setCliente(ClienteResumoModel cliente) {
		this.cliente = cliente;
	}
	
	
}
