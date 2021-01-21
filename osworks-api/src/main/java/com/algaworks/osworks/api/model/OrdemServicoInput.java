/*
 * Criado uma nova classe para ser mais fácil a personalização da entrada da api,
 * já que nem sempre tem os mesmos atributos na entrada e na saída da api.
 * Para evitar que deixe aberto propriedades que não deveriam estar.
 */
package com.algaworks.osworks.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrdemServicoInput {

	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@Valid
	@NotNull
	private ClienteIdInput cliente;
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
	public ClienteIdInput getCliente() {
		return cliente;
	}
	public void setCliente(ClienteIdInput cliente) {
		this.cliente = cliente;
	}
	
	
}
