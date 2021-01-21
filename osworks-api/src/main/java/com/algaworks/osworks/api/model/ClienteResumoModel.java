/*
 * Classe criada única e exclusivamente para mostrar apenas os atributos que são escolhidos,
 * enquanto deixa os outros atributos omissos. É aqui que coloca, isso é uma boa prática de
 * programação quando usa objetos aninhados.
 */

package com.algaworks.osworks.api.model;

public class ClienteResumoModel {

	private Long id;
	private String nome;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
